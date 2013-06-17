package main;

import homepage.HomePageActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.firstapp.R;

public class MainActivity extends Activity {

	private String username;
	private String password;
	static Rect p;
	private Button main;
	private EditText user;
	private EditText pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// For textFont Purpose
		TextView loginName = (TextView) findViewById(R.id.loginName);
		Typeface loginNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		loginName.setTypeface(loginNamefont);
		loginName.setTextColor(Color.WHITE);

		TextView userName = (TextView) findViewById(R.id.userName);
		Typeface userNamefont = Typeface.createFromAsset(getAssets(),
				"Chunkfive.otf");
		userName.setTypeface(userNamefont);
		userName.setTextColor(Color.WHITE);
		// end
		Button registerButton = (Button) findViewById(R.id.Register_button);
		Button loginButton = (Button) findViewById(R.id.Login_button);
		main = (Button) findViewById(R.id.Tempbutton);
		// main.setVisibility(View.INVISIBLE);
		loginButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("login", "login");
				user = (EditText) findViewById(R.id.idText);
				user.setSelection(0);
				username = user.getText().toString();
				pass = (EditText) findViewById(R.id.passText);
				password = pass.getText().toString();
				String stringUrl = "/person";
				List<NameValuePair> pairs = new ArrayList<NameValuePair>();
				pairs.add(new BasicNameValuePair("Login", username));
				pairs.add(new BasicNameValuePair("Password", password));
				pairs.add(new BasicNameValuePair("Request", "login"));
				ServerConnector connector = new ServerConnector(
						MainActivity.this, stringUrl, pairs,
						new LoginResultHandler());
				connector.connect();
			};
		});
		registerButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						RegisterScreenActivity.class));
			}
		});

		main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						HomePageActivity.class));
			}
		});

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

	}

	public static String readIt(InputStream stream, int len)
			throws IOException, UnsupportedEncodingException {
		Reader reader = null;
		reader = new InputStreamReader(stream, "UTF-8");
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}

	private class LoginResultHandler extends StringResultHandler {

		@Override
		public void processStringResults(String results) {

			String message = results.substring(0, 5);
			if (message.equals(Library.ERROR)) {
				Library.showAlert(MainActivity.this,
						"invalid username/password combinaiton");
			} else {
				Intent login = new Intent(getApplicationContext(),
						HomePageActivity.class);
				int index = 0;
				while (results.charAt(index) >= '0'
						&& results.charAt(index) <= '9') {
					index++;
				}
				String pointString = results.substring(0, index);
				if (pointString == null) {
					Library.showAlert(MainActivity.this,
							"Server connection failure");
				} else {
					int point = Integer.parseInt(pointString);
					user.setText("");
					pass.setText("");
					login.putExtra("User", new UserInfo(username, point));
					startActivity(login);
				}

			}
		}
	}

}