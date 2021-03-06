package homepage;

import java.util.ArrayList;
import java.util.List;

import main.Library;
import main.ServerConnector;
import main.StringResultHandler;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.firstapp.R;

public class AskFragment extends Fragment {

	public static Button re;
	private UserInfo user;
	private static String url = "/question";
	private EditText q_title;
	private EditText q_content;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		user = (UserInfo) getActivity().getIntent().getExtras().get("User");
		// setting up the view
		final View askPageView = inflater.inflate(R.layout.askfragment_view,
				container, false);
		q_title = (EditText) askPageView.findViewById(R.id.profile_username);
		q_content = (EditText) askPageView.findViewById(R.id.content);
		askPageView.findViewById(R.id.ask).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View arg0) {

						String title = q_title.getText().toString();
						String content = q_content.getText().toString();
						List<NameValuePair> pairs = new ArrayList<NameValuePair>();
						pairs.add(new BasicNameValuePair("title", title));
						pairs.add(new BasicNameValuePair("username", user
								.getUsername()));
						pairs.add(new BasicNameValuePair("content", content));
						pairs.add(new BasicNameValuePair("request", "ask"));
						new ServerConnector(getActivity(), url, pairs,
								new AskResultHandler()).connect();

					}
				});

		return askPageView;
	}

	private class AskResultHandler extends StringResultHandler {
		@Override
		public void processStringResults(final String results) {
			Library.showAlert(getActivity(), "Your question has been posted");
			q_title.setText("");
			q_content.setText("");
		}
	}
}