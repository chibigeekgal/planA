package homepage;

import java.util.ArrayList;
import java.util.List;

import main.BitmapResultHandler;
import main.ServerConnector;
import main.StringResultHandler;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;

public class HomePageFragment extends Fragment {
	private UserInfo user;
	private TextView t;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		user = (UserInfo) getActivity().getIntent().getExtras()
				.getSerializable("User");
		View homePageView = inflater.inflate(R.layout.homepage_structure,
				container, false);
		get_user_info(homePageView);
		homePageView.findViewById(R.id.profile_button).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						System.out.println("Click");
						Intent intent = new Intent(getActivity(),
								PersonalProfile.class);
						intent.putExtra("User", user);
						startActivityForResult(intent, 1);
					}
				});
		TextView username = (TextView) homePageView.findViewById(R.id.username);
		username.setText("Username:   " + user.getUsername());

		TextView points = (TextView) homePageView.findViewById(R.id.points);
		points.setText("Points:       " + String.valueOf(user.getPoints()));
		Button b = (Button) homePageView.findViewById(R.id.unanswered_button);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getActivity(), UnansweredQuestions.class);
				i.putExtra("User", user);
				startActivity(i);
			}

		});
		
		t = (TextView) homePageView.findViewById(R.id.maths_status);
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("Request", "get_status"));
		pairs.add(new BasicNameValuePair("Login", user.getUsername()));
		ServerConnector connector = new ServerConnector(getActivity(),
				"/person", pairs, new StatusResultHandler());
		connector.connect();
		
		
		return homePageView;
	}

	private void get_user_info(View homePageView) {
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("Request", "get_icon"));
		pairs.add(new BasicNameValuePair("Login", user.getUsername()));
		ImageView icon = (ImageView) homePageView
				.findViewById(R.id.personal_picture);
		ServerConnector connector = new ServerConnector(getActivity(),
				"/person", pairs, new IconResultHandler(icon));
		connector.connect();
	}

	private class IconResultHandler extends BitmapResultHandler {
		private ImageView icon;

		public IconResultHandler(ImageView icon) {
			super();
			this.icon = icon;
		}

		@Override
		protected void processBitmapResults(Bitmap results) {
			icon.setImageBitmap(results);
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
			if (resultCode == Activity.RESULT_OK) {
				get_user_info(getView());
			}
		}
			break;
		}
	}
	
	private class StatusResultHandler extends StringResultHandler {

		@Override
		protected void processStringResults(String results) {
			if(results == "" || results == null)
				t.setText(results);	
		}
	}

}
