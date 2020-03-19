package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Assignmentpage extends Activity {
	SQLiteDatabase db;
	Cursor c1;
	int s=0,n;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_assignmentpage);
		Intent i=getIntent();
		final String s1=i.getStringExtra("abc");
		//Toast.makeText(Assignmentpage.this,""+s1,Toast.LENGTH_LONG).show();
		try{ TableLayout stk = (TableLayout) findViewById(R.id.table_main);
	       
        db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
        c1=db.rawQuery("select * from assignments where cid='"+s1+"'",null);
        
        //int n=c1.getCount();
        TableRow tr1=new TableRow(this);
        tr1.setPadding(10, 10, 10, 10);
    	TextView tv=new TextView(this);
    	tv.setTextSize(20);
    	tv.setTextColor(Color.GREEN);
    	tv.setText("S.No.");
    	tr1.addView(tv);
    	TextView tv1=new TextView(this);
    	tv1.setText("Question");
    	tr1.addView(tv1);
    	tv1.setTextSize(20);
    	tv1.setTextColor(Color.GREEN);
    	
    	stk.addView(tr1);
    	
        while(c1.moveToNext()){
        	TableRow tr=new TableRow(this);
        	tr.setPadding(10, 10, 10, 10);
        	 tv=new TextView(this);
        	tv.setTextSize(20);
        	tv.setTextColor(Color.WHITE);
        	tv.setText(c1.getString(0)+"                   ");
        	tr.addView(tv);
        	 tv1=new TextView(this);
        	tv1.setText(c1.getString(1)+"");
        	tr.addView(tv1);
        	tv1.setTextSize(20);
        	tv1.setTextColor(Color.WHITE);
        	
        	stk.addView(tr);
        	s++;
        }
       }
       catch(Exception e){
    	  // Toast.makeText(Assignmentpage.this, e+"", Toast.LENGTH_LONG).show();
       }
	
	}

	

}
