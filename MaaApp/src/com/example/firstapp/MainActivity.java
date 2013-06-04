package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private String username;
	private String password;
	public static String error = "Wrong username and password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button registerButton = (Button) findViewById(R.id.Register_button);
		Button loginButton = (Button) findViewById(R.id.Login_button);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText user = (EditText) findViewById(R.id.idText);
				username = user.getText().toString();
				EditText pass = (EditText) findViewById(R.id.passText);
				password = pass.getText().toString();
				String stringUrl = "http://146.169.53.92:59999/login";
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
	
	private void showDialog(){
		Builder b = new AlertDialog.Builder(this);
		b.setMessage("Username and password wrong");
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog d = b.create();
		d.show();
	}

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
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse hresponse = client.execute(post);
				InputStream in = hresponse.getEntity().getContent();
				String result = readIt(in, 100);
				return result;
			} catch (IOException e) {
				Log.d("Error:", e.getMessage());
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		protected void onPostExecute(String result) {
			showDialog();
		/*	Intent login = new Intent(getApplicationContext(),
					ProfileActivity.class);
			login.putExtra("Points", result);
			startActivity(login);*/
		}

	}
}