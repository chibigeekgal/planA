package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import keyboard.KeyboardDisplay;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homepage.HomePageActivity;

public class MainActivity extends Activity {

	private String username;
	private String password;
	static Rect p;
	private Button main;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// For textFont Purpose
		TextView loginName = (TextView) findViewById(R.id.loginName);
		Typeface loginNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		loginName.setTypeface(loginNamefont);
		loginName.setTextColor(Color.WHITE);

		TextView userName = (TextView) findViewById(R.id.userName);
		Typeface userNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		userName.setTypeface(userNamefont);
        userName.setTextColor(Color.WHITE);
		// end
		Button registerButton = (Button) findViewById(R.id.Register_button);
		Button loginButton = (Button) findViewById(R.id.Login_button);
		main = (Button) findViewById(R.id.Tempbutton);
		main.setVisibility(View.INVISIBLE);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText user = (EditText) findViewById(R.id.idText);
				user.setSelection(0);
				username = user.getText().toString();
				EditText pass = (EditText) findViewById(R.id.passText);
				password = pass.getText().toString();
				String stringUrl = "/person";
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("Login", username));
				pairs.add(new BasicNameValuePair("Password", password));
				pairs.add(new BasicNameValuePair("Request", "login"));
				ServerConnector connector = new ServerConnector(
						MainActivity.this, stringUrl, pairs,
						new LoginResultProceccer());
				connector.connect();
			};
		});
		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						RegisterScreenActivity.class));
			}
		});

		main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						HomePageActivity.class));
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public static String readIt(InputStream stream, int len)
			throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		p = locateView(findViewById(R.id.extra_symbol));
	}
	
	public static Rect locateView(View v) {
		int[] loc_int = new int[2];
		if (v == null)
			return null;
		try {
			v.getLocationOnScreen(loc_int);
			Log.d("Invx",String.valueOf(loc_int[0]));
			Log.d("Invy",String.valueOf(loc_int[1]));
			System.out.println("Wwtf");
		} catch (NullPointerException npe) {
			// Happens when the view doesn't exist on screen anymore.
			return null;
		}
		Rect location = new Rect();
		location.left = loc_int[0];
		location.top = loc_int[1];
		location.right = location.left + v.getWidth();
		location.bottom = location.top + v.getHeight();
		return location;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.extra_symbol:
			Intent intent = new Intent(this, KeyboardDisplay.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private class LoginResultProceccer implements ResultProcessStrategy {

		@Override
		public void ProcessResults(String results) {
			int index = 0;
			String message = results.substring(0, 5);
			while (results.charAt(index) >= '0' && results.charAt(index) <= '9') {
				index++;
			}
			String output = results.substring(0, index);
			if (message.equals(Library.ERROR)) {
				Library.showAlert(MainActivity.this,
						"Invalid username/password combination");
			} else {
				Intent login = new Intent(getApplicationContext(),
						HomePageActivity.class);
				int point = Integer.parseInt(output);
				login.putExtra("User", new UserInfo(username, point));
				Library.showAlert(MainActivity.this, "login successful");
				startActivity(login);
			}
		}

	}
}