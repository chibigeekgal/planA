package com.example.homepage;

import android.graphics.Bitmap;

public class Answer {

	private Bitmap answer;
	private String username;
	
	public Answer(Bitmap answer, String username){
		this.setAnswer(answer);
		this.setUsername(username);
	}

	public Bitmap getAnswer() {
		return answer;
	}

	public void setAnswer(Bitmap answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
