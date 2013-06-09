package beans;
import java.util.LinkedList;

public class User_bean {
	private LinkedList<Question_bean> questions_asked;
	private int points;
	private String username;

	public User_bean(String username, int points) {
		this.username = username;
		this.points = points;
		questions_asked = new LinkedList<Question_bean>();
	}

	public int getPoints() {
		return points;
	}

	public String getUserName() {
		return username;
	}

	public void setPoints(int pt) {
		points = pt;
	}
}
