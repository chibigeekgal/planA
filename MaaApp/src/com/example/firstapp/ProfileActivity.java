package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_view);

		Bundle extras = getIntent().getExtras();
		if(extras != null){
			TextView point = (TextView) findViewById(R.id.textView1);
			point.setText(extras.getString("Points"));
		}
	}
}
