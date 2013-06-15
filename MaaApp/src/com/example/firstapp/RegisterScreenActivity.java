package com.example.firstapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homepage.HomePageActivity;

public class RegisterScreenActivity extends Activity {

	private String username;

	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.registration_view);
		final TextView t = (TextView) findViewById(R.id.passwordError);
		t.setVisibility(TextView.INVISIBLE);

		// For textFont purpose
		TextView RegisterTitle = (TextView) findViewById(R.id.RegisterTitle);
		Typeface RegisterTitlefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		RegisterTitle.setTypeface(RegisterTitlefont);
		RegisterTitle.setTextColor(Color.WHITE );

		TextView userName = (TextView) findViewById(R.id.profilelogintext);
		Typeface userNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		userName.setTypeface(userNamefont);
		userName.setTextColor(Color.WHITE );
		
		TextView loginName = (TextView) findViewById(R.id.loginName);
		Typeface loginNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		loginName.setTypeface(loginNamefont);
		loginName.setTextColor(Color.WHITE );

		TextView Points = (TextView) findViewById(R.id.Points);
		Typeface Pointsfont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		Points.setTypeface(Pointsfont);
		Points.setTextColor(Color.WHITE );

		// end

		Button bu = (Button) findViewById(R.id.toRegisterButton);
		bu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText passwordText = (EditText) findViewById(R.id.editPassword);
				EditText confirmPText = (EditText) findViewById(R.id.editConfirmPassword);
				String password = passwordText.getText().toString();
				String confirmP = confirmPText.getText().toString();
				if (!password.equals(confirmP)) {
					Library.showAlert(RegisterScreenActivity.this,
							"Passwords inconsistant");
				} else {
					EditText usernameText = (EditText) findViewById(R.id.new_User);
					username = usernameText.getText().toString();
					if (username.length() < 4 || password.length() < 4) {
						Library.showAlert(RegisterScreenActivity.this,
								"Username and Password must be at least 4 characters");
					} else {
						String stringUrl = "/person";
						List<NameValuePair> pairs = new ArrayList<NameValuePair>();
						pairs.add(new BasicNameValuePair("Login", username));
						pairs.add(new BasicNameValuePair("Password", password));
						pairs.add(new BasicNameValuePair("Request", "register"));
						ServerConnector connector = new ServerConnector(
								RegisterScreenActivity.this, stringUrl, pairs,
								new RegisterResultHanler());
						connector.connect();
					}
				}

			}
		});
	}

	@Override
	protected void onStart() {
		EditText password = (EditText) findViewById(R.id.editPassword);
		EditText confirmP = (EditText) findViewById(R.id.editConfirmPassword);

		if (password.isInEditMode() || confirmP.isInEditMode()) {
			final TextView t = (TextView) findViewById(R.id.passwordError);
			t.setVisibility(TextView.INVISIBLE);
		}
		super.onStart();
	}

	private class RegisterResultHanler extends StringResultHandler {

		@Override
		public void processStringResults(String results) {
			String message = results.substring(0, 5);
			if (message.equals(Library.EXIST)) {
				Library.showAlert(RegisterScreenActivity.this,
						"Username already exists");
			} else {
				Library.showAlert(RegisterScreenActivity.this,
						"Register successful!");
				Intent homepage = new Intent(getApplicationContext(),
						HomePageActivity.class);
				int points = Library.parseInt(results);
				homepage.putExtra("User", new UserInfo(username, points));
				startActivity(homepage);
			}
		}

	}

}
