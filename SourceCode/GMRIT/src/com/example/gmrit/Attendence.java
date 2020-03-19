package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Attendence extends Activity {
	Button b1;
	EditText t1,t2;
	SQLiteDatabase db;
	String s1,s2;
	TextView tv_id;
	Cursor c;
	int temp=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_attendence);
		b1=(Button)findViewById(R.id.button1);
		t1=(EditText)findViewById(R.id.editText1);
		t2=(EditText)findViewById(R.id.editText2);
		tv_id=(TextView)findViewById(R.id.tv_id);
		
		db=openOrCreateDatabase("Student",Context.MODE_PRIVATE,null);
		db.execSQL("create table if not exists attendance(studentid varchar(20) primary key,attendence varchar(10))");
		t1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				c=db.rawQuery("select * from student where sid='"+t1.getText().toString()+"'", null);
				if(c.getCount()==0)
				{
					tv_id.setText("Incorrect Id");temp=1;
				}
				else{
					tv_id.setText("");temp=0;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		b1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				s1=t1.getText().toString();
				s2=t2.getText().toString();
				if(temp==1||s1.length()==0||s2.length()==0)
				{
					Toast.makeText(Attendence.this, "Incorrect Details",Toast.LENGTH_LONG).show();	
				}
				else
				{
				try{
				db.execSQL("insert into attendance values('"+s1+"','"+s2+"')");
				Toast.makeText(Attendence.this,"sucess",Toast.LENGTH_LONG).show();
				}
				catch(Exception e)
				{
					db.execSQL("update attendance set attendence='"+s2+"' where studentid='s1'");
					Toast.makeText(Attendence.this,"Attendence Updated",Toast.LENGTH_LONG).show();
				}
				
				}	
			}
		});
		
		
	}


	
}
