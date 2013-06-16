package homepage;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.firstapp.R;

public class PersonalProfile extends Activity {

	private static int RESULT_ACT = 1;

	private ImageView personal_imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personal_profile_view);
		// setting default picture
		personal_imageView = (ImageView) findViewById(R.id.personal_pics);
		personal_imageView.setImageResource(R.drawable.default_pic);

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

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == RESULT_ACT && resultCode == Activity.RESULT_OK
				&& data != null) {
			Uri selectedImage = data.getData();

			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getContentResolver().query(selectedImage,
					filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

			String picturePath = cursor.getString(columnIndex);

			cursor.close();

			personal_imageView.setImageBitmap(BitmapFactory
					.decodeFile(picturePath));
		}
	}

	private void updatepersonalPic(String path) {

	}

}
