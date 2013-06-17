package model;

import java.io.Serializable;

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

	public String getUsername() {
		return username;
	}

}
