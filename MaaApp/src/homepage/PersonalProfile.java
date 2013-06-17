package homepage;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import main.ResultHandler;
import main.ServerConnector;
import model.UserInfo;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.firstapp.R;

public class PersonalProfile extends Activity {

	private static int RESULT_ACT = 1;

	private ImageView personal_imageView;
	private String username;
	private UserInfo user;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// selected = false;
		user = ((UserInfo) getIntent().getExtras().getSerializable("User"));
		username = user.getUsername();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_profile_view);
		// setting default picture
		personal_imageView = (ImageView) findViewById(R.id.personal_pics);
		TextView usernameText = (TextView) findViewById(R.id.profile_username);
		usernameText.setText(username);
		Button buttonLoadImage = (Button) findViewById(R.id.select_pic);
		buttonLoadImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				startActivityForResult(i, RESULT_ACT);
			}
		});

		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		Button upload = (Button) findViewById(R.id.save_button);
		upload.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Drawable image = personal_imageView.getDrawable();
				EditText ptext = (EditText) findViewById(R.id.profile_password);
				String password = ptext.getText().toString();
				if (image != null) {
					personal_imageView.buildDrawingCache();
					Bitmap icon = personal_imageView.getDrawingCache();
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					icon.compress(Bitmap.CompressFormat.PNG, 100, stream);
					byte[] byteArray = stream.toByteArray();
					String imageString = Base64.encodeToString(byteArray,
							Base64.DEFAULT);
					List<NameValuePair> imagePair = new ArrayList<NameValuePair>();
					imagePair
							.add(new BasicNameValuePair("Request", "set_icon"));
					imagePair.add(new BasicNameValuePair("Icon", imageString));
					imagePair.add(new BasicNameValuePair("Login", username));
					class ImageResultHandler implements ResultHandler {
						@Override
						public void processResults(InputStream results) {
						}
					}
					new ServerConnector(PersonalProfile.this, "/person",
							imagePair, new ImageResultHandler()).connect();

				}
				if (!password.equals("")) {
					Log.d("password", password);
					List<NameValuePair> pairs = new ArrayList<NameValuePair>();
					pairs.add(new BasicNameValuePair("Request", "edit_password"));
					pairs.add(new BasicNameValuePair("Login", username));
					pairs.add(new BasicNameValuePair("Password", password));
					class PasswordResultHandler implements ResultHandler {
						@Override
						public void processResults(InputStream results) {
						};
					}
					new ServerConnector(PersonalProfile.this, "/person", pairs,
							new PasswordResultHandler()).connect();
				}
				finish();
			}

		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_ACT && resultCode == Activity.RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();
			// selected = true;
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

			String picturePath = cursor.getString(columnIndex);

			cursor.close();
			Log.d("done", "done");
			Log.d("path", picturePath);
			Bitmap icon = BitmapFactory.decodeFile(picturePath);
			personal_imageView.setImageBitmap(icon);
		}

	}

}
