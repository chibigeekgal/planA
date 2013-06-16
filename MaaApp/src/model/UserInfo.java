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
	private Bitmap icon;

	public Bitmap getIcon() {
		return icon;
	}

	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}

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
