package alan.smspop;
import android.telephony.gsm.SmsManager ;
import android.telephony.gsm.SmsMessage ;
import android.app.PendingIntent ;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast ;
public class smssender {
	
	SmsManager sms; 
	PendingIntent pi;
	Context contxt;
	public smssender(Context contxts )
	{	
		contxt=contxts;
		sms =  SmsManager.getDefault(); 
		
	}
	public void sendmsg(String num, String msg)
	{
		PendingIntent.getBroadcast(contxt,0,new Intent(),0); 
		sms.sendTextMessage(num,null,msg,pi,null); 
	}
}
