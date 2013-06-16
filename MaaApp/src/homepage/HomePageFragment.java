package homepage;

import java.util.ArrayList;
import java.util.List;

import main.BitmapResultHandler;
import main.ServerConnector;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;

public class HomePageFragment extends Fragment {
	private UserInfo user;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View homePageView = inflater.inflate(R.layout.homepage_structure,
				container, false);
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("Request", "get_icon"));
		pairs.add(new BasicNameValuePair("Login", user.getUsername()));
		ImageView icon = (ImageView) homePageView
				.findViewById(R.id.personal_picture);
		ServerConnector connector = new ServerConnector(getActivity(),
				"/person", pairs, new IconResultHandler(icon));
		connector.connect();
		homePageView.findViewById(R.id.profile_button).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						System.out.println("Click");
						Intent intent = new Intent(getActivity(),
								PersonalProfile.class);
						intent.putExtra("User", user);
						startActivity(intent);
					}
				});
		TextView username = (TextView) homePageView.findViewById(R.id.username);
		username.setText("Username:   " + user.getUsername());

		TextView points = (TextView) homePageView.findViewById(R.id.points);
		points.setText("Points:       " + String.valueOf(user.getPoints()));

		return homePageView;
	}

	private class IconResultHandler extends BitmapResultHandler {
		private ImageView icon;

		private IconResultHandler(ImageView icon) {
			super();
			this.icon = icon;
		}

		@Override
		protected void processBitmapResults(Bitmap results) {
			icon.setImageBitmap(results);
		}

	}
}
