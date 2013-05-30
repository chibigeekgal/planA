package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterScreenActivity extends Activity{

	@Override
	protected void onCreate(Bundle b){
		super.onCreate(b);
		setContentView(R.layout.registration_view);
		final TextView t = (TextView) findViewById(R.id.passwordError);
		t.setVisibility(TextView.INVISIBLE);

		Button bu = (Button) findViewById(R.id.buttonRegister);
		bu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText password = (EditText) findViewById(R.id.editPassword);
				EditText confirmP = (EditText) findViewById(R.id.editConfirmPassword);
				if(!password.getText().equals(confirmP.getText())){
					t.setVisibility(TextView.VISIBLE);
				} 
			}
			
		});
	}	
	
	@Override
	protected void onStart() {
		EditText password = (EditText) findViewById(R.id.editPassword);
		EditText confirmP = (EditText) findViewById(R.id.editConfirmPassword);
		
		if(password.isInEditMode() || confirmP.isInEditMode()){
			final TextView t = (TextView) findViewById(R.id.passwordError);
			t.setVisibility(TextView.INVISIBLE);
		}
		super.onStart();
	}
}
