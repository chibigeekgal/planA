package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

public class ServerConnector {
	private String url;
	List<NameValuePair> pairs;
	ResultHandler proceccer;
	private Activity activity;

	public ServerConnector(Activity activity, String url,
			List<NameValuePair> pairs, ResultHandler proceccer) {
		this.url = Library.serverUrl + url;
		this.pairs = pairs;
		this.proceccer = proceccer;
		this.activity = activity;
	}

	public void connect() {
		ConnectivityManager connMgr = (ConnectivityManager) activity
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			PageTask pagetask = new PageTask();
			pagetask.execute(url);
		} else {
			Log.d("Connection Failure", "Server Connection failed");
		}
	}

	private class PageTask extends AsyncTask<String, Void, InputStream> {

		@Override
		protected InputStream doInBackground(String... urls) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);
				InputStream results= response.getEntity().getContent();
				return results;
			} catch (IOException e) {
				return null;
			}
		}

		@Override
		protected void onPostExecute(InputStream results) {
			proceccer.processResults(results);
		}

	}

	
}
