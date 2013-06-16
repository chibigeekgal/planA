package main;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class BitmapResultHandler implements ResultHandler {

	@Override
	public void processResults(InputStream results) {
		byte[] bytes = new byte[1024 * 1024 * 4];
		int length = 0;
		try {
			length = results.read(bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (length > 0) {
			Bitmap bitmapResult = BitmapFactory.decodeByteArray(bytes, 0,
					length);
			processBitmapResults(bitmapResult);
		}
	}

	protected abstract void processBitmapResults(Bitmap results);

}
