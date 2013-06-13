package com.example.firstapp;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

public abstract class StringResultHandler implements ResultHandler {

	@Override
	public void processResults(InputStream results) {
		try {

			String stringResults = readIt(results, 100);
			processStringResults(stringResults);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	protected abstract void processStringResults(String results);

	private String readIt(InputStream stream, int len) throws IOException,
			UnsupportedEncodingException {
		Reader reader = new InputStreamReader(stream);
		char[] buffer = new char[len];
		reader.read(buffer);
		return new String(buffer);
	}
}
