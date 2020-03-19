package com.example.gmrit;

import android.R.integer;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Student_assignment extends Activity {
	TextView tv,tv1;
	int i=0,s11;
	TableRow tr;
	Cursor cc;
	TableLayout stk;
	SQLiteDatabase db,db1;
	String s1,s2,s4,s5,s9,s10;
	Button b1;
	//String tv="";
	Spinner spin1,spin2;
	String []sem_num={"1","2","3","4","5","6","7","8"};
	public void getCourse()
	{
		s1=String.valueOf(spin1.getSelectedItem());
		db1=openOrCreateDatabase("Student",SQLiteDatabase.CREATE_IF_NECESSARY,null);
		Cursor cc = db1.rawQuery("select c_name from course where sem='"+s1+"'and did='"+s2+"'", null);
		int n=cc.getCount();
		//Toast.makeText(Assignment.this,""+n,Toast.LENGTH_LONG).show();
		String []c_name=new String[n];
		int i=0;
		while(cc.moveToNext())
		{
			c_name[i]=cc.getString(0);
			i++;
		}
		ArrayAdapter ac = new ArrayAdapter(this,android.R.layout.simple_spinner_item,c_name);
		ac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin2.setAdapter(ac);
		
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_assignment);
		b1=(Button)findViewById(R.id.button1);
		final GlobalClass g=(GlobalClass)getApplicationContext();
		final String s3=g.GetUsername();
		int n1=Integer.parseInt(s3);
		int n2=n1/10000;
		s2=String.valueOf(n2);
		//Toast.makeText(Student_assignment.this,"fdgfh"+s2,Toast.LENGTH_LONG).show();
		spin1 = (Spinner) findViewById(R.id.spinner1);
		spin2= (Spinner) findViewById(R.id.spinner2);
		ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,sem_num);
		aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin1.setAdapter(aa);
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
				// TODO Auto-generated method stub
				s4=String.valueOf(spin2.getSelectedItem());
				//Toast.makeText(Student_assignment.this,"fdgfh"+s4,Toast.LENGTH_LONG).show();	
				Cursor c=db1.rawQuery("select cid from course where c_name='"+s4+"'",null);
				c.moveToFirst();
				s5=c.getString(0);
				//Toast.makeText(Student_assignment.this,"fdgfh"+s5,Toast.LENGTH_LONG).show();
				//t1=(TextView)findViewById(R.id.textView2);
				//Toast.makeText(Student_assignment.this,"fdgfh"+s5,Toast.LENGTH_LONG).show();
				/*db=openOrCreateDatabase("GIS",Context.MODE_PRIVATE, null);
				 cc=db.rawQuery("select * from assignments where cid='"+s5+"'", null);
				 s11=cc.getCount();*/
				// Toast.makeText(Student_assignment.this,"fdgfh"+s11,Toast.LENGTH_LONG).show(); 
				 
				/* while(cc.moveToNext()){
				 s9=cc.getString(0);
				 s10=cc.getString(1);
				Toast.makeText(Student_assignment.this,"fdgfh"+s9+s10,Toast.LENGTH_LONG).show();
				}*/
				/*tv="<html><body><table border=1><tr><th>Assignment no </th><th>Assignment</th></tr>";
				while(cc.moveToNext())
				{
					//Toast.makeText(Student_assignment.this,"fdgfh"+cc.getString(0),Toast.LENGTH_LONG).show();
					tv=tv+"<tr>";
					for(int i=0;i<2;i++)
					{
						tv=tv+"<td> "+cc.getString(i)+" </td>";
					}
					tv=tv+"\n</tr>";
					
				}
				tv=tv+"</table></body></html>";
				
		            t1.setText(Html.fromHtml(tv));*/

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent a=new Intent(Student_assignment.this,Assignmentpage.class);
				//String value="";
				a.putExtra("abc", s5);
				startActivity(a);
			}
		});
		
					}
	
}
