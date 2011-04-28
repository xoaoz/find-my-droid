package alan.smspop;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


public class locationsvs extends Service{
	Context contxt;
	LocationManager lm;
	LocationListener loclistener;
	Location loc;
	String cb1;
	String cb2;
	String tel;
	String sender;
	String senderpasswd;
	String recvemail;
	String recvnum;
	String msgsrc;
	SharedPreferencesHelper sp;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	private Handler hdler = new Handler();
	private Runnable test= new Runnable()
	{
		public void run()
		{
			Log.i("smscheck","in test loop");
			Log.i("smscheck",lm.toString());
			hdler.postDelayed(test, 10000);			
		}
	};
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		contxt=getBaseContext();
		Log.i("smscheck","in service create");
    	sp = new SharedPreferencesHelper(contxt, "smspopup");
    	tel = sp.getValue("phonenum");
    	cb1 = sp.getValue("cb1checked");
    	cb2 = sp.getValue("cb2checked");
    	sender = sp.getValue("sendermail");
    	senderpasswd=sp.getValue("sendermailpasswd");
    	recvemail=sp.getValue("receivermail");
    	recvnum  =sp.getValue("receivernum");
    	msgsrc = sp.getValue("msgsrc");
		
		lm   = (LocationManager)contxt.getSystemService(contxt.LOCATION_SERVICE);
		loclistener =  new LocationListener()
		{ 
			public void onLocationChanged(Location location) 
			{ 
				// TODO Auto-generated method stub 
				Toast.makeText(contxt, location.getLongitude()+","+location.getLatitude(), Toast.LENGTH_LONG).show();
				Log.i("smscheck","in linstener :"+cb1);
				if(cb1.equals("true")){
					try {
							GmailSender gsender = new GmailSender(sender, senderpasswd);
							gsender.sendMail("location changed ","longitude,latitude: "+location.getLongitude()+","+location.getLatitude(),sender,recvemail);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   
					Log.i("smschecker","mail sent "+sender+senderpasswd+","+location.getLongitude()+","+location.getLatitude());
				}
				if(cb2.equals("true")){
					smssender msgsender = new smssender(contxt);
					msgsender.sendmsg(recvnum,"longitude,latitude: "+ location.getLongitude()+","+location.getLatitude());
					Log.i("smscheck","msg sent :"+"longitude,latitude: "+location.getLongitude()+","+location.getLatitude());
				}
			} 
			public void onProviderDisabled(String provider) 
			{ 
				// TODO Auto-generated method stub 
				//oclistener = null; 
			} 
			public void onProviderEnabled(String provider) 
			{ 
				// TODO Auto-generated method stub 
	    
			} 
			public void onStatusChanged(String provider, 
					int status, Bundle extras) 
			{ 
				// TODO Auto-generated method stub 
				
			} 
		}; 
    	Log.i("smscheck","loclistener is :"+loclistener.toString());
    	lm.requestLocationUpdates(lm.NETWORK_PROVIDER, 10000, 100, loclistener);
	}
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("smscheck","in service start");
		hdler.postDelayed(test, 10000);
	}
	
}
