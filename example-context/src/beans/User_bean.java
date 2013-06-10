package beans;

public class User_bean {
	private int points;
	private String username;
	private String password;

	public User_bean(String username,String password, int points) {
		this.username = username;
		this.points = points;
		this.password=password;
	}

	public int getPoints() {
		return points;
	}

	public String getUserName() {
		return username;
	}
	
	public String getPassword(){
		return password;
	}

	public void setPoints(int pt) {
		points = pt;
	}
}
