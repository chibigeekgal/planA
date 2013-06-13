package com.example.homepage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.Library;
import com.example.firstapp.R;
import com.example.firstapp.ResultHandlerStrategy;
import com.example.firstapp.ServerConnector;
import com.example.firstapp.UserInfo;

public class AskFragment extends Fragment {

	public static Button re;
	private UserInfo user;
	private static String url = "/question";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		// setting up the view
		final View askPageView = inflater.inflate(R.layout.askfragment_view,
				container, false);

		askPageView.findViewById(R.id.ask).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						EditText q_title = (EditText) askPageView
								.findViewById(R.id.q_title);
						EditText q_content = (EditText) askPageView
								.findViewById(R.id.content);
						String title = q_title.getText().toString();
						String content = q_content.getText().toString();
						List<NameValuePair> pairs = new ArrayList<NameValuePair>();
						pairs.add(new BasicNameValuePair("title", title));
						pairs.add(new BasicNameValuePair("username", user
								.getUsername()));
						pairs.add(new BasicNameValuePair("content", content));
						pairs.add(new BasicNameValuePair("request", "ask"));
						new ServerConnector(getActivity(), url, pairs,
								new AskResultProcesser()).connect();

					}

				});

		return askPageView;
	}

	private class ImageResultProcesser implements ResultHandlerStrategy {

		@Override
		public void ProcessResults(String results) {

		}

	}

	private class AskResultProcesser implements ResultHandlerStrategy {

		@Override
		public void ProcessResults(String results) {
			Library.showAlert(getActivity(), "Your question has been posted");
		}
	}
}