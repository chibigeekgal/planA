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

	@Override
	protected Bitmap doInBackground(String... urls) {
		try {
			HttpClient client = new DefaultHttpClient();
			System.out.println("client...");
			HttpPost post = new HttpPost(urls[0]);
			System.out.println("post...");
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();
			pairs.add(new BasicNameValuePair("Argument",
					HomePageActivity.string));
			post.setEntity(new UrlEncodedFormEntity(pairs));
			System.out.println("post added....");
			HttpResponse response = client.execute(post);
			System.out.println("executed");
			InputStream i = response.getEntity().getContent();
			System.out.println("input stream...");
			BufferedInputStream bis = new BufferedInputStream(i);
			Bitmap bm = BitmapFactory.decodeStream(bis);
			bis.close();
			i.close();
			System.out.println("I got here...");
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
