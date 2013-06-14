package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.firstapp.BitmapResultHandler;
import com.example.firstapp.Question;
import com.example.firstapp.R;
import com.example.firstapp.ServerConnector;
import com.example.firstapp.UserInfo;

public class IndividualQuestion extends Activity {

	private Question question;
	private UserInfo user;
	private ImageView questionView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.individual_question);
		user = (UserInfo) getIntent().getExtras().get("User");
		question = (Question) getIntent().getExtras().get("Question");
		questionView = (ImageView) findViewById(R.id.question_display);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("request", "get_content"));
		pairs.add(new BasicNameValuePair("index", ((Integer) question
				.getIndex()).toString()));
		ServerConnector connector = new ServerConnector(this, "/question",
				pairs, new QuestionResultHandler());
		connector.connect();

	}

	private class QuestionResultHandler extends BitmapResultHandler {

		@Override
		protected void processBitmapResults(Bitmap results) {
			questionView.setImageBitmap(results);
		}

	}

}
