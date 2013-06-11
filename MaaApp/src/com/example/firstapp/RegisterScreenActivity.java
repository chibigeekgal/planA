package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
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
import android.graphics.Typeface;
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

		//For textFont purpose
		TextView RegisterTitle = (TextView) findViewById(R.id.RegisterTitle);  
		Typeface RegisterTitlefont = Typeface.createFromAsset(getAssets(), "Bigfish.ttf");  
		RegisterTitle.setTypeface(RegisterTitlefont);  
		
		TextView userName = (TextView) findViewById(R.id.profilelogintext);  
		Typeface userNamefont = Typeface.createFromAsset(getAssets(), "Bigfish.ttf");  
		userName.setTypeface(userNamefont);  
		
		TextView loginName = (TextView) findViewById(R.id.loginName);  
		Typeface loginNamefont = Typeface.createFromAsset(getAssets(), "Bigfish.ttf");  
		loginName.setTypeface(loginNamefont);  
		
		TextView Points = (TextView) findViewById(R.id.Points);  
		Typeface Pointsfont = Typeface.createFromAsset(getAssets(), "Bigfish.ttf");  
		Points.setTypeface(Pointsfont);
		
		//end
		
		Button bu = (Button) findViewById(R.id.toRegisterButton);
		bu.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				EditText password = (EditText) findViewById(R.id.editPassword);
				EditText confirmP = (EditText) findViewById(R.id.editConfirmPassword);
					String stringUrl = "http://10.0.2.2:59999/person";
					ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
					NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
					if (networkInfo != null && networkInfo.isConnected()) {
						EditText user = (EditText) findViewById(R.id.new_User);
						username = user.getText().toString();
						pass_word = password.getText().toString();

						assert (username != null && pass_word != null);
						LoginPageTask lpt = new LoginPageTask();
						lpt.execute(stringUrl);
					} else {
						Log.d("Not connected", "oh no...");
					}
				};
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
	
	
    //handler Class of Register Button
	private class LoginPageTask extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... urls) {

			// params comes from the execute() call: params[0] is the url.
			try {
			
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("Login", username));
				pairs.add(new BasicNameValuePair("Password", pass_word));
				pairs.add(new BasicNameValuePair("Request","register"));
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse hresponse = client.execute(post);
				InputStream i = hresponse.getEntity().getContent();
				String results = MainActivity.readIt(i,5);
				return results;
			} catch (IOException e) {
				Log.d("Error:", e.getMessage());
				return "Unable to retrieve web page. URL may be invalid.";
			}
		}

		protected void onPostExecute(String result) {
			if(result.equals("exist")){
				showDialog();
			} else {
			Intent login = new Intent(getApplicationContext(),
					ProfileActivity.class);
			login.putExtra("Points", result);
			login.putExtra("Username", username);
			startActivity(login);
			}
		}
	}
	
	public void showDialog() {
		Builder b = new AlertDialog.Builder(this);
		b.setMessage("Username already exist");
		b.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog d = b.create();
		d.show();
	}

}
