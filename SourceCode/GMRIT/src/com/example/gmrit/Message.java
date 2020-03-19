package com.example.gmrit;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
public class Message extends Activity {
	
	Spinner dept,id;
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	TextView t1;
	String tv="";
	String sent="SMS Sent";
	String deliver="SMS Delivered";
	SQLiteDatabase db;
	ArrayAdapter aaa;
	String sid[];
	EditText e1;
	Button b1;
	Cursor c;
	
	public void getStudents()
	{
		int i=0;
		while(c.moveToNext())
		{
			sid[i]=c.getString(0);
			i++;
		}

		 aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sid);
		aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		id.setAdapter(aaa);
		
	}
	
	
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		
		

		dept=(Spinner) findViewById(R.id.dept);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dept.setAdapter(aa);
		
		id=(Spinner)findViewById(R.id.id);
		
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		
		t1=(TextView)findViewById(R.id.textView1);
	
		db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
		dept.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			//	t1.setText(dept.getSelectedItem().toString());
				c=db.rawQuery("select sid from student where dept='"+(dept.getSelectedItemId()+1)+"'",null);
				 sid=new String[c.getCount()];
				getStudents();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
		
		
b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				c=db.rawQuery("select name,phone from student where sid='"+(id.getSelectedItem().toString())+"'",null);
				String msg;
				while(c.moveToNext())
				{
					msg=e1.getText().toString();
					msg="Hello "+c.getString(0).toString()+"\n\n"+msg;
					sendSms(c.getString(1).toString(), msg);
					
				}
			}
		});
		
		
	}
	
	public void sendSms(String phone,String msg)
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
		sms.sendTextMessage(phone,null,msg,p1,p2);
		Toast.makeText(getApplicationContext(),"sent",Toast.LENGTH_LONG).show();
	
	}
	
	
}

