package com.example.firstapp;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public abstract class JsonResultHandler implements ResultHandler {

	@Override
	public void processResults(InputStream results) {
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(new InputStreamReader(results),
				JsonElement.class);
		processJsonResults(element);

	}

	public abstract void processJsonResults(JsonElement element);

}
