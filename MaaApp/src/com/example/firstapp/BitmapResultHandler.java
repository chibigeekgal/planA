package com.example.firstapp;

import java.io.BufferedInputStream;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class BitmapResultHandler implements ResultHandler {

	@Override
	public void processResults(InputStream results) {
		BufferedInputStream b = new BufferedInputStream(results);
		Bitmap bitmapResult = BitmapFactory.decodeStream(b);
		processBitmapResults(bitmapResult);
	}

	protected abstract void processBitmapResults(Bitmap results);

}
