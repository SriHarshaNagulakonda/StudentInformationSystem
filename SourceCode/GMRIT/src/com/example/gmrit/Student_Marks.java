package com.example.gmrit;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Student_Marks extends Activity {
	EditText t1;
	Button b1;
	String s1;
	Cursor c1;
	TextView tv;
	SQLiteDatabase db;
	String subj[]={"English","DBMS","Linux","Python","DJango","Angular JS"};
	String x;
	int i=0,j=0;

		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student__marks);
		GlobalClass g=(GlobalClass)getApplicationContext();
		s1=g.GetUsername();
       try{ TableLayout stk = (TableLayout) findViewById(R.id.table_main);
       
        db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
        c1=db.rawQuery("select * from marks where sid='"+s1+"'",null);
        c1.moveToFirst();
        while(i<c1.getColumnCount()){
        	TableRow tr=new TableRow(this);
        	tr.setPadding(10, 5, 10, 5);
        	TextView tv=new TextView(this);
        	tv.setTextColor(Color.WHITE);
        	tv.setTextSize(20);
        	if(c1.getColumnName(i).toString().substring(0, 3).equals("sub")){
        		x=subj[j];
        		j++;
        	}
        	else{
        		x=c1.getColumnName(i);
        	}
        	tv.setText(x+"          ");
        	tr.addView(tv);
        	TextView tv1=new TextView(this);
        	tv1.setText(c1.getString(i)+"");
        	tv1.setTextSize(20);
        	tr.addView(tv1);

        	tv1.setTextColor(Color.WHITE);
        	stk.addView(tr);
        	i++;
        }
       }
       catch(Exception e){
    	   Toast.makeText(Student_Marks.this, e+"", Toast.LENGTH_LONG).show();
       }
}
}
