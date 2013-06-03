package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterScreenActivity extends Activity {

	private String username;
	private String pass_word;
	
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.registration_view);
		final TextView t = (TextView) findViewById(R.id.passwordError);
		t.setVisibility(TextView.INVISIBLE);

		Button bu = (Button) findViewById(R.id.toRegisterButton);
		bu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText password = (EditText) findViewById(R.id.editPassword);
				EditText confirmP = (EditText) findViewById(R.id.editConfirmPassword);
				/*if (!password.getText().equals(confirmP.getText())) {
					t.setVisibility(TextView.VISIBLE);
				} else {*/
					String stringUrl = "http://146.169.53.2:59999/reg";
					ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
					if (networkInfo != null && networkInfo.isConnected()) {
						EditText user = (EditText) findViewById(R.id.new_User);
						username = user.getText().toString();
						pass_word = password.getText().toString();
						new LoginPageTask().execute(stringUrl);
					} else {
						Log.d("Not connected", "oh no...");
					}
				}
				;
			//}

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

	private class LoginPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
				URL url = new URL(urls[0]);
				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.setReadTimeout(10000 /* milliseconds */);
				conn.setConnectTimeout(15000 /* milliseconds */);
				conn.setRequestMethod("POST");
			//Starts the query
				conn.connect();
				int response = conn.getResponseCode();
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new Test("Login", username));
				pairs.add(new Test("Password", pass_word));
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse hresponse = client.execute(post);
				InputStream i=conn.getInputStream();
				String results=MainActivity.readIt(i,100);
				
				
				conn.disconnect();
				return results;
			} catch (IOException e) {
				Log.d("Error:", e.getMessage());
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		protected void onPostExecute(String result) {
			Intent login = new Intent(getApplicationContext(),
					ProfileActivity.class);
			login.putExtra("Points", result);
			startActivity(login);
		}
	}
}
