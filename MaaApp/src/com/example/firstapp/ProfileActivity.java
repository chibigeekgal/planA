package com.example.firstapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileActivity extends Activity {
	private String username;
	private String point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_view);
		Bundle extras = getIntent().getExtras();
		username = extras.getString("Username");
		point=extras.getString("Points");
		TextView user = (TextView) findViewById(R.id.profile_login);
		user.setText(username);
		Log.d("user",username);
		assert (username != null);
		TextView points = (TextView) findViewById(R.id.profile_points);
		points.setText(point);
	}
	
}
