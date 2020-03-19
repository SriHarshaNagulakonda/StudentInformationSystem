package com.example.gmrit;

import java.util.regex.Pattern;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Faculty_reg extends Activity {
	Button b;
	EditText name,dob,exp,qual,addr,mail,phno;
	String s1,s2,s3,s4,s5,s6,s7,s8,pass,s9;
	TextView tv_name,tv_dob,tv_phone,tv_dept,tv_mail;
	int p=0,q=0,r=0,s=0;
	SQLiteDatabase db;
	Spinner spin;
	String sent="SMS_SENT";
	String deliver="SMS_DELIVERED";
	Cursor c;
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try{
		setContentView(R.layout.activity_faculty_reg);
		spin = (Spinner) findViewById(R.id.dept);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(aa);
		name=(EditText)findViewById(R.id.editText1);
		tv_name=(TextView)findViewById(R.id.tv1);
		
		
		name.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String s1=name.getText().toString();
				if(s1.length()<6&&s1.length()>0)
				{
					tv_name.setText("Atleast 6 characters");
					p=1;
				}
				else if(!Pattern.matches("[a-zA-Z0-9]{6,}",s1)){
					tv_name.setText("Name Should be AlphaNumeric");
					p=1;
				}
				else
				{
					tv_name.setText("");
					p=0;
				}
			}
			public void afterTextChanged1(Editable s) {}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		mail=(EditText)findViewById(R.id.editText2);
		tv_mail=(TextView)findViewById(R.id.tv2);
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
	
		phno=(EditText)findViewById(R.id.editText3);
		tv_phone=(TextView)findViewById(R.id.tv3);
		
		phno.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(phno.getText().length()!=0 && phno.getText().length()!=10){
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
		addr=(EditText)findViewById(R.id.editText4);
		exp=(EditText)findViewById(R.id.EditText5);
		dob=(EditText)findViewById(R.id.editText6);
		qual=(EditText)findViewById(R.id.EditText7);
		db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists faculty_registration(fname varchar,email varchar,phonenumber varchar,dob varchar ,gender varchar, did varchar,qual varchar,exper varchar,address varchar, fid varchar, password varchar)");
		b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				s1=name.getText().toString();
				s2=mail.getText().toString();
				s3=phno.getText().toString();
				s4=dob.getText().toString();
				s5=addr.getText().toString();
				s6=qual.getText().toString();
				s7=exp.getText().toString();
				s8=String.valueOf(spin.getSelectedItemPosition()+1);
				if(p==1||r==1||s==1||s1.trim().length()==0||s2.trim().length()==0||s3.trim().length()!=10||s4.trim().length()==0||s5.trim().length()==0||s6.trim().length()==0||s7.trim().length()==0||s9=="")
				{
					Toast.makeText(Faculty_reg.this,"Incorrect details",Toast.LENGTH_LONG).show();
				}
				else
				{
					c=db.rawQuery("select * from faculty_registration where fid>='"+1000*(spin.getSelectedItemPosition()+1)+"' and fid<'"+1000*(spin.getSelectedItemId()+2)+"'", null);
					pass=String.valueOf(1000*(spin.getSelectedItemPosition()+1)+c.getCount());
					//tv_phone.setText(pass);
					db.execSQL("insert into faculty_registration values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s9+"','"+s8+"','"+s6+"','"+s7+"','"+s5+"','"+pass+"','"+pass+"')");
					Toast.makeText(Faculty_reg.this, "Registered",Toast.LENGTH_LONG).show();
					send();
					Intent i=new Intent(Faculty_reg.this,Admin.class);
					startActivity(i);
					overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
					finish();
				}
				
			}
		});
		}
		catch(Exception ee)
		{
			Toast.makeText(getBaseContext(), ee+"", Toast.LENGTH_LONG).show();
		}
		
		
	}
	public void RadioButtonClicked(View view) {

		//This variable will store whether the user was male or female
		s9=""; 
		// Check that the button is  now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
		    case R.id.radioButton2:
		        if (checked)
		            s9 = "female";
		        break;
		    case R.id.radioButton1:
		        if (checked)
		           s9 = "male";
		        break;
		}

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
		sms.sendTextMessage(s3,null,"Hello "+s1+"You have been successfully registered as Faculty in GMRIT.Congratulations!!!.Your FacultyId:"+pass+"and password is:"+pass ,p1,p2);
		Toast.makeText(getApplicationContext(),"sent",Toast.LENGTH_LONG).show();
		
	}
	
}
