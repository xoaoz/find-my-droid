package alan.smspop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;
public class passwdchecker  extends Activity {
	
		
	public static SharedPreferencesHelper sp;
	String passwd;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	        //将我们定义的窗口设置为默认视图
	     
		
		sp  = new SharedPreferencesHelper(getBaseContext(), "smspopup");
		passwd=sp.getValue("passwd");
		Log.i("smscheker","passwd is :"+passwd);
		if(passwd.equals(""))
		{
			Intent i  = new Intent(getBaseContext(),smspop.class);
		    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		    startActivity(i);
			finish();
		}
		else
		{
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.passwdchecker);
			Button btn = (Button)findViewById(R.id.Button001);
	        btn.setOnClickListener(new Button.OnClickListener()
	        {
			//	@Override
				public void onClick(View v) 
				{
					EditText et = (EditText)findViewById(R.id.EditText001);
					String pass = et.getText().toString().trim();
					Log.i("smscheker","pass   is :"+passwd);
					if(pass.equals(passwd))
					{
						Intent i  = new Intent(getBaseContext(),smspop.class);
					    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					    startActivity(i);
						finish();
					}
					else
						finish();
				}
	        });
		}
	}
	
}
