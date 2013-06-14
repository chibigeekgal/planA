package com.example.homepage;

public class Answer {

	private int answer;
	private String username;
	
	public Answer(int answer, String username){
		this.setAnswer(answer);
		this.setUsername(username);
	}

	public int getAnswer() {
		return answer;
	}

	public void setAnswer( int answer) {
		this.answer = answer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
