package com.example.firstapp;

import java.util.LinkedList;

public class User {
	private LinkedList<Question> questions_asked;
	private int points;
	private String username;

	public User(String username, String points) {
		this.username = username;
		this.points = Integer.parseInt(points);
		questions_asked = new LinkedList<Question>();
	}

	public int getPoints() {
		return points;
	}

	public String getUserName() {
		return username;
	}

	public void AddQuestion(Question question) {
		questions_asked.add(question);
		// TODO:ADD TO DATABASE
	}
}
