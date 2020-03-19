package com.example.gmrit;

import java.util.Random;



import java.util.regex.Pattern;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
//import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Student extends Activity {

	Button b1,check;
	EditText name,age,phone,mail,otp;
	String s1,s2,s3,s4,s5;
	TextView tv_name,tv_age,tv_phone,tv_otp,tv_dept,tv_mail;
	SQLiteDatabase db;
	String pass="GMRIT";
	Random random;
	Spinner spin;
	Cursor c;
	int rand;
	String sent="SMS Sent";
	String deliver="SMS Delivered";
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	int p=0,q=0,r=0,s=0,t=0;
	public void clear()
	{
		name.setText("");
		age.setText("");
		phone.setText("");
		mail.setText("");
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__student);
		
		 spin = (Spinner) findViewById(R.id.dept);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		
		
		b1=(Button)findViewById(R.id.button1);
		name=(EditText)findViewById(R.id.name);
		tv_name=(TextView)findViewById(R.id.tv_name);
		
		
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String s1=name.getText().toString();
				if(s1.length()<6&&s1.length()>0)
				{
					tv_name.setText("Atleast 6 characters");
					p=1;
				}
				else if(s1.length()>0&&(!Pattern.matches("[a-zA-Z ]{6,}",s1))){
					tv_name.setText("Name Should have only Alphabets");
					p=1;
				}
				else
				{
					tv_name.setText("");
					p=0;
				}
			}
			@Override
			public void afterTextChanged(Editable s) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
		});
		
		
		age=(EditText)findViewById(R.id.age);
		tv_age=(TextView)findViewById(R.id.tv_age);
		age.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(age.getText().length()!=0){
				if(Integer.parseInt(age.getText().toString())<17){
					tv_age.setText("Minimum 17");q=1;
				}
				else if(21<Integer.parseInt(age.getText().toString())){
					tv_age.setText("Maximum 21");q=1;
				}
				else{
					tv_age.setText("");q=0;
				}
			}
				else{
					tv_age.setText("");q=0;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		 random=new Random(4);
		
		phone=(EditText)findViewById(R.id.phone);
		tv_phone=(TextView)findViewById(R.id.tv_phone);
		
		phone.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(phone.getText().length()!=0 && phone.getText().length()!=10){
					tv_phone.setText("Phone Number - 10 Digits");r=1;
				}
				else{
					tv_phone.setText("");r=0;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		tv_dept=(TextView)findViewById(R.id.tv_dept);
		
		mail=(EditText)findViewById(R.id.mail);
		tv_mail=(TextView)findViewById(R.id.tv_mail);
		mail.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence ss, int start, int before, int count) {
				if(mail.getText().toString().length()!=0){
				if(!Pattern.matches("[a-zA-Z0-9]{5,}@[a-zA-Z]{3,8}.[a-zA-Z]{2,4}",mail.getText().toString()))
				{
					tv_mail.setText("Incorrect Mail");
					s=1;
				}
				else{
					tv_mail.setText("");
					s=0;
				}
				}
				else{
					tv_mail.setText("");
				}
	
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		db=	openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
		//db.execSQL("drop table student");
		db.execSQL("create table if not exists student(name varchar(30),age number,password varchar(20),phone varchar(10),dept varchar(5),mail varchar(20),sid number primary key)");
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				s1=name.getText().toString();
				s2=age.getText().toString();
				s3=phone.getText().toString();
				s4=String.valueOf(spin.getSelectedItemPosition()+1);
				s5=mail.getText().toString();
				//tv_dept.setText(s1+","+s2+","+s3+","+s4+","+s5);
				if(p==1||q==1||s==1||t==1||s1.trim().length()==0||s2.trim().length()==0||s3.trim().length()!=10||s5.trim().length()==0)
				{
					Toast.makeText(Add_Student.this, "Incorrect Details",Toast.LENGTH_LONG).show();
				}
				else
				{
					c=db.rawQuery("select * from student where sid>='"+10000*(spin.getSelectedItemPosition()+1)+"' and sid<'"+10000*(spin.getSelectedItemId()+2)+"'", null);
					pass=String.valueOf(10000*(spin.getSelectedItemPosition()+1)+c.getCount());
					//tv_phone.setText(pass);
					db.execSQL("insert into student values('"+s1+"','"+s2+"','"+pass+"','"+s3+"','"+s4+"','"+s5+"','"+pass+"')");
					Toast.makeText(Add_Student.this, "Registered",Toast.LENGTH_LONG).show();
					s1="Hello "+s1+"\n You are successfully registered in GMRIT app with \n\n Phone Number: "+s3+"\nEmail id: "+s5;
					sendSms(s3,s1 );
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {}
					s1="You can login into the app with following credentials \nUsername and password :"+pass+"\n\nNOTE: Don't disclose your username and password";
					sendSms(s3,s1 );
					clear();
				}
			}
		});
	}
}