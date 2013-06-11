package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import keyboard.KeyboardDisplay;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
	public static String error = "error";
	static Rect p;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// For textFont Purpose
		TextView loginName = (TextView) findViewById(R.id.loginName);
		Typeface loginNamefont = Typeface.createFromAsset(getAssets(),
				"Top_Secret.ttf");
		loginName.setTypeface(loginNamefont);

		TextView userName = (TextView) findViewById(R.id.userName);
		Typeface userNamefont = Typeface.createFromAsset(getAssets(),
				"Top_Secret.ttf");
		userName.setTypeface(userNamefont);

		// end
		Button registerButton = (Button) findViewById(R.id.Register_button);
		Button loginButton = (Button) findViewById(R.id.Login_button);
		Button TempButton = (Button) findViewById(R.id.Tempbutton);

		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText user = (EditText) findViewById(R.id.idText);
				username = user.getText().toString();
				EditText pass = (EditText) findViewById(R.id.passText);
				password = pass.getText().toString();
				String stringUrl = "http://10.0.2.2:59999/person";
				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
					new LoginPageTask().execute(stringUrl);
				} else {
					Log.d("Not connected", "oh no...");
				}
			};
		});
		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						RegisterScreenActivity.class));
			}
		});

		TempButton.setOnClickListener(new View.OnClickListener() {
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

	public void showDialog() {
		Builder b = new AlertDialog.Builder(this);
		b.setMessage(error);
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog d = b.create();
		d.show();
	}

	// handler class for DataBase
	private class LoginPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("Login", username));
				pairs.add(new BasicNameValuePair("Password", password));
				pairs.add(new BasicNameValuePair("Request","login"));
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse hresponse = client.execute(post);
				InputStream i = hresponse.getEntity().getContent();
				Integer points = post.getParams().getIntParameter("Points", 0);
				String results = MainActivity.readIt(i, 5);
				//Integer sev_points=(Integer)post.getParams().getParameter("Points");
				//Log.d("SERVER POINTS",sev_points.toString());
				return results;
			} catch (IOException e) {
				Log.d("Error:", e.getMessage());
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		protected void onPostExecute(String result) {
			Log.d("postexecute", result);
			if (result.equals(error)) {
				showDialog();
			} else {
				Intent login = new Intent(getApplicationContext(),
						HomePageActivity.class);
				int point=Integer.parseInt(result);
				login.putExtra("User",new UserInfo(username,point));
				startActivity(login);
			}
		}
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
}