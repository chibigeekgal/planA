package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button b = (Button) findViewById(R.id.button2);
		b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//startActivity(new Intent(getApplicationContext(), RegisterScreenActivity.class));
				String stringUrl = "http://146.169.53.106:59999/s";
				ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
				if (networkInfo != null && networkInfo.isConnected()) {
		            new DownloadWebpageTask().execute(stringUrl);
				}
			};
		});
	}

	private class Test implements NameValuePair{

		private String key;
		private String value;

		public Test(String key, String value){
			this.key = key;
			this.value = value;
		}
		
		@Override
		public String getName() {
			return key;
		}

		@Override
		public String getValue() {
			// TODO Auto-generated method stub
			return value;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
	    Reader reader = null;
	    reader = new InputStreamReader(stream, "UTF-8");        
	    char[] buffer = new char[len];
	    reader.read(buffer);
	    return new String(buffer);
	}
	
	
	private class DownloadWebpageTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
              
            // params comes from the execute() call: params[0] is the url.
            try {
            	/*HttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost(urls[0]);
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new Test("g1227111_u", "sHg5fr0Alb"));
				post.setEntity(new UrlEncodedFormEntity(pairs));
				HttpResponse response = client.execute(post);*/
                return downloadUrl(urls[0]);
            } catch (IOException e) {
            	Log.d("Error:",e.getMessage());
                return "Unable to retrieve web page. URL may be invalid.";
            }
        }
               
        protected void onPostExecute(String result){
        	TextView t = (TextView) findViewById(R.id.TextView01);
        	t.setText(result);
        }
        private String downloadUrl(String myurl) throws IOException {
            InputStream is = null;
            // Only display the first 500 characters of the retrieved
            // web page content.
            int len = 500;
          
            try {
                URL url = new URL(myurl);  
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();                
                conn.setReadTimeout(10000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // Starts the query                
                conn.connect();
                int response = conn.getResponseCode();
                Log.d("Example", "The response is: " + response);
                is = conn.getInputStream();

                // Convert the InputStream into a string
                String contentAsString = readIt(is, len);
                return contentAsString;
                
            // Makes sure that the InputStream is closed after the app is
            // finished using it.
            } finally {
                if (is != null) {
                    is.close();
                } 
            }
        }
	}
}