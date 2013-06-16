package model;

import android.graphics.Bitmap;


public class Answer {

	private String username;
	private Bitmap content;

	public Answer(String username, Bitmap content) {
		this.username = username;
		this.content = content;
	}

	public String getUsername() {
		return username;
	}
	
	public Bitmap getContent(){
		return content;
	}

}
