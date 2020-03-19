package com.example.gmrit;

import android.os.Bundle;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity {
	Button b1;
	EditText t1;
	SQLiteDatabase db;
	Cursor c,c1;
	String s1,s2;
	String username;
	String sent="SMS_SENT";
	String deliver="SMS_DELIVERED";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		b1=(Button)findViewById(R.id.button1);
		t1=(EditText)findViewById(R.id.editText1);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			//	Toast.makeText(Login.this,"Success",Toast.LENGTH_LONG).show();
				 username=String.valueOf(t1.getText());
				db=openOrCreateDatabase("Student",Context.MODE_PRIVATE,null);
				c1=db.rawQuery("select phone,password from student where sid='"+username+"'",null); 
				if(c1.moveToFirst()){
				s1=c1.getString(c1.getColumnIndex("phone")).toString();
				s2=c1.getString(c1.getColumnIndex("password")).toString();
				send(); 
				Toast.makeText(getApplicationContext(),"Password sent to *******"+s1.substring(7), Toast.LENGTH_LONG).show();
				Intent a=new Intent(ForgotPassword.this,Login.class);
				startActivity(a);
				}
				else{
					Toast.makeText(getApplicationContext(),"Incorrect", Toast.LENGTH_LONG).show();
				}
			}
		});
		}
	public void send()
	{
		PendingIntent p1=PendingIntent.getBroadcast(this,0,new Intent(sent),0);
		PendingIntent p2=PendingIntent.getBroadcast(this,0,new Intent(deliver),0);
		
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context,Intent intent) {
				switch(getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(),"sent",Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(getApplicationContext(),"failure error",Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(getApplicationContext(),"no_service",Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(getApplicationContext(),"null_error",Toast.LENGTH_LONG).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(getApplicationContext(),"radio_error",Toast.LENGTH_LONG).show();
					break;
					
				}
				
			}
		},new IntentFilter(sent));
		
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context,Intent intent) {
				switch(getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(getApplicationContext(),"delivered",Toast.LENGTH_LONG).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(getApplicationContext(),"not delivered",Toast.LENGTH_LONG).show();
					break;
				}	
			}
		},new IntentFilter(deliver));
		SmsManager sms=SmsManager.getDefault();
		sms.sendTextMessage(s1,null,"your Password is"+ s2 ,p1,p2);
	    //Toast.makeText(getApplicationContext(),"sent"+s1, Toast.LENGTH_LONG).show();

		
	}
	}

	


