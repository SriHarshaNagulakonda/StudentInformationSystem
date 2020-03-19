package com.example.gmrit;

import android.os.Bundle;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class Admin extends Activity {

	Button b1,b2,b3,b4,b5,b6;
	Intent a1,a2,a3,a4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button6);
		   a1=new Intent(Admin.this,Add_Student.class);
		  a2=new Intent(Admin.this,Faculty_reg.class);
		  a3=new Intent(Admin.this,Add_Course.class);
		  a4=new Intent(Admin.this,Stud_Details.class);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(a1);
				overridePendingTransition(R.anim.slide_up,  R.anim.slide_down);
				
			}
		});
b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(a2);
				overridePendingTransition(R.anim.slide_up,  R.anim.slide_down);
				
			}
		});
b3.setOnClickListener(new OnClickListener() {
	@Override
	public void onClick(View v) {
		startActivity(a3);
		overridePendingTransition(R.anim.slide_up,  R.anim.slide_down);
		
	}
});
b4.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		startActivity(a4);
		
	}
});
b5.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent a5=new Intent(Admin.this,Login.class);
		startActivity(a5);
		finish();
		
	}
});
b6.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Intent a=new Intent(Admin.this,SendSms.class);
		startActivity(a);
		
	}
});
	}
}
