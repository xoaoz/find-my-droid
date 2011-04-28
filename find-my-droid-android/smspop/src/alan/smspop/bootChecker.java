package alan.smspop;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class bootChecker extends BroadcastReceiver{
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    SharedPreferencesHelper sp ;
    Context contxt;
    String simTel="";
    String telnum="";
    String mid="";
   
    //@Override
    public void onReceive(Context context, Intent intent) {
	// TODO Auto-generated method stub
	//if (intent.getAction().equals(ACTION)) 
        
	contxt=context;
	TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);   
	// String imei = tm.getDeviceId();   
	simTel = tm.getSubscriberId();
	if(simTel==null)
		return;
	telnum = tm.getLine1Number();
	Log.i("smsceck","simTel :"+simTel+" temnum: "+telnum);
	//Toast.makeText(context, imei+":"+tel, Toast.LENGTH_LONG).show();
	sp = new SharedPreferencesHelper(context, "smspopup");
	String tel = sp.getValue("phonenum");
	
	if(simTel.equals(tel)){
	    //Intent i  = new Intent(context,smspop.class);
	    //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	   // context.startActivity(i);
	    Toast.makeText(context, "phone number validates success ^_^", Toast.LENGTH_LONG);
	}
	else{
		Log.i("smsceck","sim card changed");
		final String msg="your sim card changed!!! imsi: "+simTel+", tel number: "+telnum;
	    new Thread( new Runnable() {  
		    public void run() {  
			String cb1 = sp.getValue("cb1checked");
	    		
			if(cb1.equals("true")){
			    
			    String sender = sp.getValue("sendermail");
			    String senderpasswd=sp.getValue("sendermailpasswd");
			    String recvemail=sp.getValue("receivermail");
			    
			 
			   // sender+="@gmail.com";
			    //Toast.makeText(contxt, "complete.fdsafdsafsda..", Toast.LENGTH_LONG).show();
			    try {
					Thread.sleep(20000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					GmailSender gsender = new GmailSender(sender,senderpasswd);
					gsender.sendMail("sim card changed!!!",msg,sender,recvemail);
					
					
				}catch (Exception e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				}   
				 Log.i("smsceck","sim card changed mail sent :"+msg);
			   
			}
			String cb2 = sp.getValue("cb2checked");
			if(cb2.equals("true")){
			    String recvnum  =sp.getValue("receivernum");
			    try {
					Thread.sleep(20000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    smssender msgsender = new smssender(contxt);
				msgsender.sendmsg(recvnum, msg);
				
				Log.i("smsceck","sim card changed msg sent :"+msg);
			}
			//    Toast.makeText(context, "complete...", Toast.LENGTH_LONG).show();
		    }         
		}).start(); 
	}
    }
}

