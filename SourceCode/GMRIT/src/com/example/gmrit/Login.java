package com.example.gmrit;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	
	Button b1,b2;
	EditText t1,t2;
	TextView tv1;
	String username,password;
	
	SQLiteDatabase db;
	Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final GlobalClass g=(GlobalClass)getApplicationContext();
        setContentView(R.layout.activity_login);

        tv1=(TextView)findViewById(R.id.textView1);
        t1=(EditText)findViewById(R.id.editText1);
        t2=(EditText)findViewById(R.id.editText2);
        b1= (Button)findViewById(R.id.button1);
        b2= (Button)findViewById(R.id.button2);
        b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 username=String.valueOf(t1.getText());
				 g.Setusername(username);
				 password=String.valueOf(t2.getText());
				 g.Setpassword(password);
				 t1.setText("");
				 t2.setText("");
				if(username.equals("admin")&&password.equals("admin")){
					tv1.setText(String.valueOf(""));
				Intent a = new Intent(Login.this,Admin.class);
				startActivity(a);
             	overridePendingTransition( R.anim.right_slide_in, R.anim.right_slide_out );
				}
				else
				{
					db=openOrCreateDatabase("Student", Context.MODE_PRIVATE, null);
					 c=db.rawQuery("select sid,password from student where sid='"+username+"' and password='"+password+"'", null);
						 if(c.getCount()>0)
						 {
							 tv1.setText("");
//							 scan g=new scan();
						//	 g.execute();
							 Toast.makeText(Login.this,"Login Successfull", Toast.LENGTH_LONG).show();
							 Intent i1=new Intent(Login.this,StudentLogin.class);
							 startActivity(i1);
						 }
						 else
						 {
							 c=db.rawQuery("select * from faculty_registration where fid='"+username+"'", null);
							 if(c.getCount()>0)
							 {
								 Intent a = new Intent(Login.this,Faculty.class);
								 startActivity(a);
							 }
							 else{
							 	tv1.setText(String.valueOf("incorrect details"));
							 }
						 }
				}
			}
		});
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a1=new Intent(Login.this,ForgotPassword.class);
				startActivity(a1);
			}
		});
        
        
    }

	public class scan extends AsyncTask<String, String, String>{

		private ProgressDialog pd;

		protected void onPreExecute() {
			super.onPreExecute();
		 pd = new ProgressDialog(Login.this);
		 pd.setTitle("Please Wait!!");
		 pd.setMessage("Logging you In....");
		 pd.setMax(10);
		 pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
	}	
}

