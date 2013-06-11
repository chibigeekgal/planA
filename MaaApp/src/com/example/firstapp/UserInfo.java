package com.example.firstapp;

import java.io.Serializable;

public class UserInfo implements Serializable {
	private String username;
	private int points;
	public UserInfo(String username, int points) {
		super();
		this.username = username;
		this.points = points;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public String getUsername() {
		return username;
	}
	
}
