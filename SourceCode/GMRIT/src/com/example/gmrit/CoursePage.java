package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CoursePage extends Activity {
	Spinner spin1,spin2;
	
	SQLiteDatabase db1;
	String s1,s2;
	String tv="";
	TextView t1;
	SQLiteDatabase db;
	String []sem_num={"1","2","3","4","5","6","7","8"};
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	public void getCourse()
	{
		s1=String.valueOf(spin2.getSelectedItem());
		s2=String.valueOf(spin1.getSelectedItemPosition()+1);
		String s3=String.valueOf(spin1.getSelectedItem());
		//Toast.makeText(Coursepage.this,"fdgfh"+s1+s2,Toast.LENGTH_LONG).show();
		Cursor c1=db.rawQuery("select c_name from course where did='"+s2+"' and sem='"+s1+"'",null);
		tv="<b>Semester No:</b>"+s1+"<br>"+"<b>Department Name:</b>"+s3+"<br>"+"<ul>";
		while(c1.moveToNext())
		{
			tv=tv+"<li>"+c1.getString(0)+"</li><br>";
		}
		tv=tv+"</ul>";
		t1.setText(Html.fromHtml(tv));	
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_page);
		//b1=(Button)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
		db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
		spin1 = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(aa);
		spin2 = (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sem_num);
		ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin2.setAdapter(ab);
		spin1.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				getCourse();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		spin2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				getCourse();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	
	}
	}

