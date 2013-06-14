package beans;

import java.sql.SQLException;
import java.util.LinkedList;

public class AnswerMethod extends Method {

	public AnswerMethod() throws SQLException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addAnswer(String username, int qIndex, int aIndex, int content) {

	}

	public Answer_bean chooseAnswer(int qIndex, int aIndex) {
		return null;
	}

	public LinkedList<Answer_bean> getAnswers(int qIndex) {
		return null;
	}

}
