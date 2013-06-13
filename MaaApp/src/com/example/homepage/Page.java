package com.example.homepage;

import java.io.BufferedInputStream;
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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class Page extends AsyncTask<String, Void, Bitmap> {

	private String argument;
	
	public Page(String argument) {
		this.argument = argument;
	}
	
	@Override
	protected Bitmap doInBackground(String... urls) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("http://146.169.53.91:59999/image");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("Argument",
					this.argument));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			InputStream i = response.getEntity().getContent();
			BufferedInputStream bis = new BufferedInputStream(i);
			Bitmap bm = BitmapFactory.decodeStream(bis);
			bis.close();
			i.close();
			return bm;

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		ImageView i = HomePageActivity.image;
		i.setImageBitmap(result);

		super.onPostExecute(result);
	}

}
