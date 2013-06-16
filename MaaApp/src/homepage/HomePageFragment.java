package homepage;

import java.util.ArrayList;
import java.util.List;

import main.JsonResultHandler;
import main.Library;
import main.ServerConnector;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
		ServerConnector connector=new ServerConnector(getActivity(),
				"/person", pairs, new IconResultHandler(icon));
		connector.connect();

		// select a picture
		homePageView.findViewById(R.id.personal_profile).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(getActivity(),
								PersonalProfile.class);
						intent.putExtra("User", user);
						startActivity(intent);
					}
				});

		System.out.println(user.getUsername() + " " + user.getPoints());
		TextView username = (TextView) homePageView.findViewById(R.id.username);
		username.setText("Username:   " + user.getUsername());

		TextView points = (TextView) homePageView.findViewById(R.id.points);
		points.setText("Points:       " + String.valueOf(user.getPoints()));

		return homePageView;
	}

	private class IconResultHandler extends JsonResultHandler {
		private ImageView icon;

		private IconResultHandler(ImageView icon) {
			super();
			this.icon = icon;
		}

		@Override
		public void processJsonResults(JsonElement element) {
			JsonObject o=element.getAsJsonObject();
			String byteS=Library.convertToString(o.get("icon"));
			byte[] bytes=Base64.decode(byteS, Base64.DEFAULT);
			Bitmap b=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
			icon.setImageBitmap(b);
		}


	}
}
