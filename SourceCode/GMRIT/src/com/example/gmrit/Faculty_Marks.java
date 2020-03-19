package com.example.gmrit;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Faculty_Marks extends Activity {
	Spinner s;
	String[] dept_names={"CSE","ECE","EEE","CIV","MECH","POW","CHE","IT"};
	Button b1;
	EditText t1,t2,t3,t4,t5,t6,t7;
	SQLiteDatabase db;
	String s8;
	int s1,s2,s3,s4,s5,s6,s7,s10;
	float s9;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_faculty__marks);
	s=(Spinner)findViewById(R.id.spinner1);
	b1=(Button)findViewById(R.id.button1);
	t1=(EditText)findViewById(R.id.editText1);
	t2=(EditText)findViewById(R.id.editText2);
	t3=(EditText)findViewById(R.id.editText3);
	t4=(EditText)findViewById(R.id.editText4);
	t5=(EditText)findViewById(R.id.editText5);
	t6=(EditText)findViewById(R.id.editText6);
	t7=(EditText)findViewById(R.id.editText7);
	ArrayAdapter a=new ArrayAdapter(this,android.R.layout.simple_spinner_item,dept_names);
	a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	s.setAdapter(a);
	
	db=openOrCreateDatabase("Student",Context.MODE_PRIVATE,null);
	db.execSQL("create table if not exists marks(dept varchar(20),sid varchar(10) primary key,sub1 varchar(10),sub2 varchar(10),sub3 varchar(10),sub4 varchar(10),sub5 varchar(10),sub6 varchar(10),percentage varchar(10))");
	b1.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			s8= s.getSelectedItem().toString();
			s1=Integer.parseInt(t1.getText().toString());
			s2=Integer.parseInt(t2.getText().toString());
			s3=Integer.parseInt(t3.getText().toString());
			s4=Integer.parseInt(t4.getText().toString());
			s5=Integer.parseInt(t5.getText().toString());
			s6=Integer.parseInt(t6.getText().toString());
			s7=Integer.parseInt(t7.getText().toString());
			s9=((s2+s3+s4+s5+s6+s7)/6);
			try{
			
			db.execSQL("insert into marks values('"+s8+"','"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s9+"')");
			Toast.makeText(Faculty_Marks.this,"sucess",Toast.LENGTH_LONG).show();
			}
			catch(Exception e)
			{
				db.execSQL("update marks set sub1='"+s2+"',sub2='"+s3+"',sub3='"+s4+"',sub4='"+s5+"',sub5='"+s6+"',sub6='"+s7+"',dept='"+s8+"',percentage='"+s9+"' where sid='s1'");
				Toast.makeText(Faculty_Marks.this,"Updated",Toast.LENGTH_LONG).show();
			}
		}
			
	});
	}
	}



