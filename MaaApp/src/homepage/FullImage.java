package homepage;

import android.app.Activity;
import android.os.Bundle;

import com.example.firstapp.R;

public class FullImage extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.enlarge_image);
		
	//	int imgid = getIntent().getBitmap("Image", 0);
	//	ImageView img = (ImageView) findViewById(R.id.answer_image);
	//	img.setImageResource(imgid);
		
	}
}
