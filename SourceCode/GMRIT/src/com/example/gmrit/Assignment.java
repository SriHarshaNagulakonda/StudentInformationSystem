package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Assignment extends Activity {
	Spinner spin1,spin2,spin3;
	String []dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	String []sem_num={"1","2","3","4","5","6","7","8"};
	SQLiteDatabase db,db1;
	Button b1;
	String s1,s2,s3,s4,s5;
	EditText t1;
	public void getCourse()
	{
		 s1=String.valueOf(spin1.getSelectedItemPosition()+1);
		 s2=String.valueOf(spin2.getSelectedItem());
		db=openOrCreateDatabase("Student",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		Cursor cc = db.rawQuery("select c_name from course where sem='"+s2+"'and did='"+s1+"'", null);
		int n=cc.getCount();
		//Toast.makeText(Assignment.this,""+n,Toast.LENGTH_LONG).show();
		String []c_name=new String[n];
		int i=0;
		while(cc.moveToNext())
		{
			c_name[i]=cc.getString(0);
			i++;
		}
		spin3= (Spinner) findViewById(R.id.spinner3);
		ArrayAdapter ac = new ArrayAdapter(this,android.R.layout.simple_spinner_item,c_name);
		ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin3.setAdapter(ac);
		
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assignment);
		t1=(EditText)findViewById(R.id.editText1);
		
		spin1= (Spinner) findViewById(R.id.spinner1);
		spin2= (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(aa);
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
		b1=(Button)findViewById(R.id.button1); 	
		db1=openOrCreateDatabase("Student", SQLiteDatabase.CREATE_IF_NECESSARY,null);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
			s3=String.valueOf(spin3.getSelectedItem());
			//Toast.makeText(Assignment.this,"fdgfh"+s3,Toast.LENGTH_LONG).show();	
			Cursor c=db.rawQuery("select cid from course where c_name='"+s3+"'",null);
			c.moveToFirst();
			s4=c.getString(0);
			//Toast.makeText(Assignment.this,""+s4,Toast.LENGTH_LONG).show();
			db1.execSQL("create table if not exists assignments(asid varchar ,question varchar,cid varchar,did varchar,sem varchar)");
				Cursor c2=db1.rawQuery("select asid from assignments where cid='"+s4+"'", null);
				int n1=c2.getCount();
				String n2=String.valueOf(n1+1);
				s5=t1.getText().toString();
				db1.execSQL("insert into assignments values('"+n2+"','"+s5+"','"+s4+"','"+s1+"','"+s2+"')");
				t1.setText("");
				Toast.makeText(getBaseContext(), "Assignment Submitted", Toast.LENGTH_LONG).show();
				
			}
		});
	}
	
}
