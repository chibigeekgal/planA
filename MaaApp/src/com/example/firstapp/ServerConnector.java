package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
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
	ResultHandlerStrategy proceccer;
	private Activity activity;
	private String serverError = "Unable to retrieve web page. URL may be invalid.";

	public ServerConnector(Activity activity, String url,
			List<NameValuePair> pairs, ResultHandlerStrategy proceccer) {
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

	private class PageTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... urls) {
			try {
				HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);
				InputStream i = response.getEntity().getContent();
				String results = readIt(i, 100);
				return results;
			} catch (IOException e) {
				return serverError;
			}
		}

		@Override
		protected void onPostExecute(String results) {
			proceccer.ProcessResults(results);
		}

	}

	private String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}
}
