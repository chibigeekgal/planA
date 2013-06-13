package com.example.homepage;

public class QuestionWrap {
	private int personal_pic;
	private String title;
	private String username;

	public QuestionWrap(int imageId, String title, String username) {
		this.personal_pic = imageId;
		this.title = title;
		this.username = username;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public int getpersonalPic() {
		return personal_pic;
	}

	public void setpersonalPic(int image) {
		this.personal_pic = image;
	}

	@Override
	public String toString() {
		return title + "\n" + username;
	}
}