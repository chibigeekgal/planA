package model;

import android.graphics.Bitmap;


public class Answer {

	private String username;
	private Bitmap content;
	private int index;

	public Answer(String username, Bitmap content, int index) {
		this.username = username;
		this.content = content;
		this.index = index;
	}

	public String getUsername() {
		return username;
	}
	
	public Bitmap getContent(){
		return content;
	}

	public int getIndex() {
		return index;
	}

}
