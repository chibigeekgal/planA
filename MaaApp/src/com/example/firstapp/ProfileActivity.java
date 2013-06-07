package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
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
		TextView user = (TextView) findViewById(R.id.profile_login);
		user.setText(username);
		Log.d("user",username);
		assert (username != null);
		TextView points = (TextView) findViewById(R.id.profile_points);
		String stringUrl = "http://146.169.53.93:59999/prof";
		ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			assert (username != null);
			ProfilePageTask task=new ProfilePageTask();
			task.execute(stringUrl);
			try {
				points.setText(task.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		} else {
			Log.d("Not connected", "oh no...");
		}
	}

	private class ProfilePageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... StringUrls) {

			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(StringUrls[0]);
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			Log.d("Username background",username);
			pairs.add(new BasicNameValuePair("Login", username));
			try {
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse hresponse = client.execute(post);
				InputStream i = hresponse.getEntity().getContent();
				String results = MainActivity.readIt(i, 5);
				Log.d("Points",results);
				return results;
			} catch (IOException e) {
				Log.d("Error:", e.getMessage());
				return "invalid Url";
			}
		}

	}
}
