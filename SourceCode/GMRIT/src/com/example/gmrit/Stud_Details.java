package com.example.gmrit;

import android.os.Bundle;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Stud_Details extends Activity {

	Spinner dept,id;
	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	TextView t1;
	String tv="";
	SQLiteDatabase db;
	ArrayAdapter aaa;
	String sid[];
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
	
	public void getProfile(int f)
	{
		c=db.rawQuery("select name,age,sid,dept,phone from student where sid='"+f+"'",null);
		if(c.getCount()==0)
		{
			showMessage("error","nothing found");
			return;
		}
		StringBuffer buffer=new StringBuffer();
		while(c.moveToNext())
		{
			buffer.append("Name:"+c.getString(0)+"\n");
			buffer.append("Age:"+c.getString(1)+"\n");
			buffer.append("Studentid:"+c.getString(2)+"\n");
			buffer.append("Branch:"+dept_names[Integer.parseInt(c.getString(3))-1]+"\n");
			buffer.append("Phno:"+c.getString(4)+"\n");
		}
		showMessage("Profile",buffer.toString());	
	}
	
	public void showMessage(String tittle,String Message)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(tittle);
		builder.setMessage(Message);
		builder.show();
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_stud__details);
	
		dept=(Spinner) findViewById(R.id.dept);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dept.setAdapter(aa);
		
		id=(Spinner)findViewById(R.id.id);
		
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
		id.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				getProfile(Integer.parseInt(id.getSelectedItem().toString()));
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {}
		});
	}
}
