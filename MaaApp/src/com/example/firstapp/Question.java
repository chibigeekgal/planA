package com.example.firstapp;

import java.io.Serializable;

import android.graphics.Bitmap;

public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int index, bestAnswer;
	private String user, title;
	private Bitmap content;

	public Question(int index, int bestAnswer, String user, String title) {
		super();
		this.index = index;
		this.bestAnswer = bestAnswer;
		this.user = user;
		this.title = title;
		content=getContentFromServer();
	}

	private Bitmap getContentFromServer() {
		return null;
	}

	public int getIndex() {
		return index;
	}

	public int getBestAnswer() {
		return bestAnswer;
	}

	public String getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}

	public Bitmap getContent() {
		return content;
	}
}
