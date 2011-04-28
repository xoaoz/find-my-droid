package alan.smspop;

import alan.smspop.locationsvs;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class smsreceiver extends BroadcastReceiver{
    private static final String mACTION ="android.provider.Telephony.SMS_RECEIVED"; 
    LocationListener loclistener=null;
    
    Context contxt=null;
    LocationManager lm =null;
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
    //  @Override 
    public void onReceive( final Context context, Intent intent) 
    { 
    	
    	Log.i("smscheck","get broadcast :");
    	// Toast.makeText(contxt, "rcv broadcast", Toast.LENGTH_LONG).show();
    	contxt=context;
    	lm   = (LocationManager)contxt.getSystemService(contxt.LOCATION_SERVICE);
    	StringBuilder sb = new StringBuilder(); 
    	Bundle bundle = intent.getExtras(); 
    	if (bundle != null){
    		Object[] myOBJpdus = (Object[]) bundle.get("pdus"); 
    		SmsMessage[] messages = new SmsMessage[myOBJpdus.length];  
    		for (int i = 0; i<myOBJpdus.length; i++)
    			messages[i] = SmsMessage.createFromPdu((byte[]) myOBJpdus[i]);
    		
    		for (SmsMessage currentMessage : messages)
    			sb.append(currentMessage.getDisplayMessageBody());  
    	}    
	
    	String sms = sb.toString();
    	sp = new SharedPreferencesHelper(contxt, "smspopup");
    	tel = sp.getValue("phonenum");
    	cb1 = sp.getValue("cb1checked");
    	cb2 = sp.getValue("cb2checked");
    	sender = sp.getValue("sendermail");
    	senderpasswd=sp.getValue("sendermailpasswd");
    	recvemail=sp.getValue("receivermail");
    	recvnum  =sp.getValue("receivernum");
    	msgsrc = sp.getValue("msgsrc");
    	//sender+="@gmail.com";
    	// Toast.makeText(context, sms, Toast.LENGTH_LONG).show();
    	Log.i("smscheck","before come to branch");
    	if(sms.equals(msgsrc)){
    		
    		Toast.makeText(contxt, "got signal", Toast.LENGTH_LONG).show();
    		Intent i = new Intent(contxt,locationsvs.class);  
    		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            contxt.startService(i);  
    		Log.i("smscheck","come to branch and start services");
    		/*
    		if((loclistener==null)){
    			Log.i("smscheck","loclistener null");
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
    		else{
    			Log.i("smschecker","loclistener not null");
    			loc = (Location) lm.getLastKnownLocation(lm.NETWORK_PROVIDER);
    			while(loc  == null){  
    				lm.requestLocationUpdates(lm.NETWORK_PROVIDER, 60000, 1, loclistener);  
    				loc = (Location) lm.getLastKnownLocation(lm.NETWORK_PROVIDER);
    				Log.i("smschecker","in getting location");
    			}  
    			Log.i("smschecker","get location");
    			double lon = loc.getLongitude();
    			double lat = loc.getLatitude();
    			String msg="longitude,latitude: "+lon+","+lat;
    			if(cb1.equals("true")){
    				try {
    					GmailSender gsender = new GmailSender(sender, senderpasswd);
    					gsender.sendMail("location",msg,sender,recvemail);
    				} catch (Exception e) {
			
    					e.printStackTrace();
    				}  
    				Log.i("smschecker","mail sent"+msg);
    			}
    			if(cb2.equals("true")){
    				smssender msgsender = new smssender(contxt);
    				msgsender.sendmsg(recvnum, msg);
    				Log.i("smschecker","msg sent"+msg);
    			}
    		
    		
    		}*/
    	}
    }
}