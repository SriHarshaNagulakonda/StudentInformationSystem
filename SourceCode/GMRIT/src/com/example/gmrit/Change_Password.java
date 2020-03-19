package com.example.gmrit;

import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
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

public class Change_Password extends Activity {

	EditText pass,npass,cpass;
	TextView tv_pass,tv_npass,tv_cpass;
	SQLiteDatabase db;
	int p=0,q=0,r=0;
	Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final GlobalClass g=(GlobalClass) getApplicationContext();
		setContentView(R.layout.activity_change__password);
	
		pass=(EditText)findViewById(R.id.pass);
		npass=(EditText)findViewById(R.id.npass);
		cpass=(EditText)findViewById(R.id.cpass);
		
		
		
		
		tv_pass=(TextView)findViewById(R.id.tv_pass);
		tv_npass=(TextView)findViewById(R.id.tv_npass);
		tv_cpass=(TextView)findViewById(R.id.tv_cpass);
		
		b1=(Button)findViewById(R.id.button1);
		
		db=openOrCreateDatabase("Student",Context.MODE_PRIVATE,null);
		
		pass.addTextChangedListener(new TextWatcher() {
			
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!g.GetPassword().equals(pass.getText().toString())){
					tv_pass.setText("Incorrect Password");
					p=1;
				}
				else{
					tv_pass.setText("");
					p=0;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		npass.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(!Pattern.matches("[a-zA-Z0-9]{1,}",npass.getText().toString())){
					tv_npass.setText("Only Alphanumeric");
					q=1;
				}
				else{
					tv_npass.setText("");
					q=0;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
	
		cpass.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(npass.getText().toString().equals(cpass.getText().toString())){
					tv_cpass.setText("Passwords Matched");
					r=0;
				}
				else{
					tv_cpass.setText("Passwords Did'nt Match");
					r=1;
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {}
			
			@Override
			public void afterTextChanged(Editable s) {}
		});
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					if(p==1||q==1||r==1){
						Toast.makeText(Change_Password.this, "Incorrect Details", Toast.LENGTH_LONG).show();
					}
					else{
						db.execSQL("update student set password='"+npass.getText().toString()+"' where sid='"+g.GetUsername()+"'");
						Toast.makeText(Change_Password.this, "Successfully Updated", Toast.LENGTH_LONG).show();
					}
			}
		});
	}
}
