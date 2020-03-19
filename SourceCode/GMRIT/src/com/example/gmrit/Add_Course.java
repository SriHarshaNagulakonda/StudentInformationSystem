package com.example.gmrit;

import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Add_Course extends Activity {

	String[] dept_names={"CSE","ECE","EEE","CIVIL","MECHANICAL","POWER","CHEMICAL","IT"};
	String[] semester={"1","2","3","4","5","6","7","8"};
	EditText c_name;
	TextView tv_cname;
	Button b1;
	SQLiteDatabase db;
	Spinner dept,sem;
	int semid,deptid,cid,temp;
	Cursor c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add__course);
		
		 dept=(Spinner) findViewById(R.id.spinner1);
		 sem=(Spinner) findViewById(R.id.spinner2);
		
		ArrayAdapter a_dept = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
		a_dept.setDropDownViewResource(android.R.layout.simple_spinner_item);
		dept.setAdapter(a_dept);
		
		ArrayAdapter a_sem=new ArrayAdapter(this, android.R.layout.simple_spinner_item,semester);
		a_sem.setDropDownViewResource(android.R.layout.simple_spinner_item);
		sem.setAdapter(a_sem);
		
		
		
		c_name=(EditText)findViewById(R.id.c_name);
		tv_cname=(TextView)findViewById(R.id.tv_cname);
		db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
		db.execSQL("create table if not exists course(c_name varchar(20) unique,sem number,cid number primary key,did number)");
		//db.execSQL("Drop table course");
		c_name.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!Pattern.matches("[a-zA-Z]{1,}",c_name.getText().toString())){
					tv_cname.setText("Only Alphabets");
				}
				else{
					tv_cname.setText("");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			@Override
			public void afterTextChanged(Editable s) {}
		});
		b1=(Button)findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				deptid=dept.getSelectedItemPosition()+1;
				semid=sem.getSelectedItemPosition()+1;
				temp=deptid*100+semid*10;
				c=db.rawQuery("select * from course where cid>='"+temp+"' and cid<'"+(temp+10)+"'",null);
				
				try{
				db.execSQL("insert into course values('"+c_name.getText().toString()+"','"+sem.getSelectedItem()+"','"+(temp+c.getCount())+"','"+(dept.getSelectedItemId()+1)+"')");
				Toast.makeText(Add_Course.this,"registered", Toast.LENGTH_LONG).show();
				}
				catch(Exception e)
				{
					Toast.makeText(Add_Course.this,"already added",Toast.LENGTH_LONG).show();
				}
		
			}

		});
		
	}

}
