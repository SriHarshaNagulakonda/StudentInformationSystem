package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class Faculty extends Activity {
	Button b1,b2,b3,b4,b5,b6;
	ImageButton im1;
	SQLiteDatabase db;
	Cursor c;
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass g=(GlobalClass)getApplicationContext();
		final String f=g.GetUsername();
		setContentView(R.layout.activity_faculty);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button) findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button7);
		im1=(ImageButton)findViewById(R.id.imageButton1);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a=new Intent(Faculty.this,Attendence.class);
				startActivity(a);
			}
		});
		
		
		im1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				db=openOrCreateDatabase("Student",Context.MODE_PRIVATE,null);
				c=db.rawQuery("select fname,dob,phonenumber,did,email from faculty_registration where fid='"+f+"'",null);
				if(c.getCount()==0)
				{
					showMessage("error","nothing found");
					return;
				}
				StringBuffer buffer=new StringBuffer();
				while(c.moveToNext())
				{
					buffer.append("Name:"+c.getString(0)+"\n");
					buffer.append("Date Of Birth:"+c.getString(1)+"\n");
					buffer.append("phone Number:"+c.getString(2)+"\n");
					buffer.append("Branch:"+dept_names[Integer.parseInt(c.getString(3))-1]+"\n");
					buffer.append("Email:"+c.getString(4)+"\n");
				}
				showMessage("Profile",buffer.toString());		
			}
		});
		b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a=new Intent(Faculty.this,Login.class);
				startActivity(a);
				finish();
				
			}
		});
	b3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a3=new Intent(Faculty.this,Change_Password.class);
				startActivity(a3);
			}
		});
	b4.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent a=new Intent(Faculty.this,Faculty_Marks.class);
			startActivity(a);
		}
	});
	b5.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent a=new Intent(Faculty.this,Assignment.class);
			startActivity(a);
		}
	});
	b6.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent a=new Intent(Faculty.this,CoursePage.class);
			startActivity(a);
		}
	});
	}
	public void showMessage(String tittle,String Message)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(tittle);
		builder.setMessage(Message);
		builder.show();
	}

	
	}

