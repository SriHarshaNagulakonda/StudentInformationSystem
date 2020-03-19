package com.example.gmrit;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FirstScreen extends Activity {
	Button b;
	int k=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_screen);
		b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent a=new Intent(FirstScreen.this,Login.class);
				k=1;
				startActivity(a);
				finish();
			}
		});
		Thread background = new Thread() {
			public void run() {
				
				try {
					// Thread will sleep for 5 seconds
					sleep(10*1000);
					
					// After 5 seconds redirect to another intent
				    Intent i=new Intent(getBaseContext(),Login.class);
					if(k==0){
				    startActivity(i);
					
					//Remove activity
					finish();
					}
				} catch (Exception e) {
				
				}
			}
		};
		
		// start thread
		background.start();
		
	}
	
	@Override
    protected void onDestroy() {
		
        super.onDestroy();
        
    }
}
