package model;

import java.io.Serializable;

import android.graphics.Bitmap;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private int points;

	public UserInfo(String username, int points) {
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
