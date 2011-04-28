package alan.smspop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
public class smspop extends Activity {
    /** Called when the activity is first created. */
	String tel;
	 EditText et = null;
	 public static String passwd;
	 public static String sendermail;
	 public static String sendermailpasswd;
	 public static String receivermail;
	 public static String receivernum;
	 public static String msgsrc="Alan:start";
	 public static String phonenum;
	 public  CheckBox cb1;
	 public  CheckBox cb2;
	 public static SharedPreferencesHelper sp;
	 public static String cb1ckd;
	 public static String cb2ckd;
	 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);   
	    // String imei = tm.getDeviceId();   
	    tel = tm.getSubscriberId();  
        et = (EditText)findViewById(R.id.EditText01);
        et.setText(tel);
        sp = new SharedPreferencesHelper(getBaseContext(), "smspopup");
        et = (EditText)findViewById(R.id.EditText02);
	    sendermail= sp.getValue("sendermail");
	    et.setText(sendermail);
	    et = (EditText)findViewById(R.id.EditText03);
	    sendermailpasswd =
	    sp.getValue("sendermailpasswd");
	    et.setText(sendermailpasswd);
	    et = (EditText)findViewById(R.id.EditText04);
	    receivermail = 
	    sp.getValue("receivermail");
	    et.setText(receivermail);
	    et = (EditText)findViewById(R.id.EditText05);
	    msgsrc= 
	    sp.getValue("msgsrc");
	    et.setText(msgsrc);
	    et = (EditText)findViewById(R.id.EditText06);
	    receivernum = 
	    sp.getValue("receivernum");	
	    et.setText(receivernum);
	    et = (EditText)findViewById(R.id.EditText07);
	    passwd=sp.getValue("passwd");
	    et.setText(passwd);
	  
    	
	    
	    CheckBox.OnCheckedChangeListener cbListener=
			  new CheckBox.OnCheckedChangeListener(){

			//	@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if(cb1.isChecked()==true)
					{
						((EditText)findViewById(R.id.EditText02)).setEnabled(true);
						((EditText)findViewById(R.id.EditText03)).setEnabled(true);
						((EditText)findViewById(R.id.EditText04)).setEnabled(true);
					}
					else
					{
						((EditText)findViewById(R.id.EditText02)).setEnabled(false);
						((EditText)findViewById(R.id.EditText03)).setEnabled(false);
						((EditText)findViewById(R.id.EditText04)).setEnabled(false);
					}
					if(cb2.isChecked()==true)
					{
						((EditText)findViewById(R.id.EditText06)).setEnabled(true);
					}
					else
					{
						((EditText)findViewById(R.id.EditText06)).setEnabled(false);
					}									
				}			 
		 };
		 
		 cb1 = (CheckBox)findViewById(R.id.CheckBox01);
	     cb2 = (CheckBox)findViewById(R.id.CheckBox02);
	     cb1ckd=sp.getValue("cb1checked");
	     if(cb1ckd.equals(""))cb1ckd="false";
	     cb2ckd=sp.getValue("cb2checked");
	     if(cb2ckd.equals(""))cb2ckd="false";
	     
	     if(cb1ckd.equals("true"))
	     {
	    	 cb1.setChecked(true);
	    	 ((EditText)findViewById(R.id.EditText02)).setEnabled(true);
	    	 ((EditText)findViewById(R.id.EditText03)).setEnabled(true);
	    	 ((EditText)findViewById(R.id.EditText04)).setEnabled(true);
	     }else 
	     { 
	    	 cb1.setChecked(false);
	    	 ((EditText)findViewById(R.id.EditText02)).setEnabled(false);
	    	 ((EditText)findViewById(R.id.EditText03)).setEnabled(false);
	    	 ((EditText)findViewById(R.id.EditText04)).setEnabled(false);
	     }
	     if(cb2ckd.equals("true"))
	     {   
	    	 cb2.setChecked(true);
	    	 ((EditText)findViewById(R.id.EditText06)).setEnabled(true);
	     }
		 else {
			 cb2.setChecked(false);
		     ((EditText)findViewById(R.id.EditText06)).setEnabled(false);
		 }
	     cb1.setOnCheckedChangeListener(cbListener);
	     cb2.setOnCheckedChangeListener(cbListener);
	     
    	Button btn = (Button)findViewById(R.id.Button01);
        btn.setOnClickListener(new Button.OnClickListener()
        {
		 	
			public void onClick(View v) {
			
			 if(cb1.isChecked()||cb2.isChecked())
			 {
				boolean allfill=true;
				et = (EditText)findViewById(R.id.EditText01);
			    String phonenum = et.getText().toString().trim();
			    sp.putValue("phonenum",phonenum);
			   // Toast.makeText(getBaseContext(), "Your phone number "+etnum+" has been recorded ^_^", Toast.LENGTH_LONG).show();
			    et = (EditText)findViewById(R.id.EditText02);
			    sendermail = et.getText().toString().trim();
			    //if(sendermail.equals(""))
			    //	allfill=false;
			    //sendermail+="@gmail.com";
			    sp.putValue("sendermail", sendermail);
			    et = (EditText)findViewById(R.id.EditText03);
			    sendermailpasswd = et.getText().toString().trim();
			    //if(sendermailpasswd.equals(""))
			    //	allfill=false;
			    sp.putValue("sendermailpasswd", sendermailpasswd);
			    et = (EditText)findViewById(R.id.EditText04);
			    receivermail = et.getText().toString().trim();
			    sp.putValue("receivermail",receivermail);
			    et = (EditText)findViewById(R.id.EditText05);
			    msgsrc= et.getText().toString().trim();
			    sp.putValue("msgsrc", msgsrc);
			    if(msgsrc.equals(""))
			    	allfill=false;
			    
			    et = (EditText)findViewById(R.id.EditText06);
			    receivernum = et.getText().toString().trim();
			    sp.putValue("receivernum", receivernum);	
			    et = (EditText)findViewById(R.id.EditText07);
			    passwd =  et.getText().toString().trim();
			    sp.putValue("passwd", passwd);	
			    if(cb1.isChecked()==true)
			    {
			    	sp.putValue("cb1checked","true");
			    	if(receivermail.equals(""))
			    		allfill=false;
			    	if(sendermail.equals(""))
			    		allfill=false;
			    	if(sendermailpasswd.equals(""))
			    		allfill=false;
			    }
			    else
			    {
			    	sp.putValue("cb1checked","false");	
			    }
			    if(cb2.isChecked()==true)
			    {
			    	sp.putValue("cb2checked","true");		
			    	if(receivernum.equals(""))
			    		allfill=false;
			    }
			    else
			    {
			    	sp.putValue("cb2checked","false");	
			    }
			    if(allfill==true){
			    	Toast.makeText(getBaseContext(), "Your setting has been recorded ^_^", Toast.LENGTH_LONG).show();
			    	finish();
			    }
			    else
			    {
			    	Toast.makeText(getBaseContext(),"您还没有填写需要的内容", Toast.LENGTH_LONG).show();
			    	
			    }
			   }
			   else
			   {
				   Toast.makeText(getBaseContext(),"两种通知方式中至少选一个", Toast.LENGTH_LONG).show();				  
			   }
			}
        });
    }
}