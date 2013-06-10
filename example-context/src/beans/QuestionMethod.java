package beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class QuestionMethod extends Method {

	public QuestionMethod() throws SQLException {
		super();
	}

	public void ask_question(String user, String title, String content) {
		try {
			int index = get_max_index() + 1;
			getStatement().executeUpdate(
					"Insert INTO Question VALUES (" + index + ", '" + title
							+ "', '" + user + "', '" + content + "', 0);");
		} catch (SQLException e) {

		}
	}

	private int get_max_index() throws SQLException {
		ResultSet rs;
		rs = getStatement().executeQuery(
				"SELECT COUNT(Question_index) FROM Question;");
		return rs.getInt(1);

	}

	public Question_bean getQuestionByIndex(int index) {
		ResultSet rs;
		try {
			rs = getStatement().executeQuery(
					"SELECT * FROM Question WHERE Question_index = '" + index
							+ "';");
			if (rs.next()) {
				String owner = rs.getString("Login");
				String content = rs.getString("Content");
				String title = rs.getString("Question_title");
				return new Question_bean(owner, title, content, index);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public LinkedList<Question_bean> getAllQuestions() {
		String SqlQuery = "SELECT * FROM Question;";
		LinkedList<Question_bean> questions;
		questions = getQuestoinsFromSQLQuery(SqlQuery);
		return questions;
	}

	private LinkedList<Question_bean> getQuestoinsFromSQLQuery(String query) {
		ResultSet rs;
		try {
			rs = getStatement().executeQuery(query);
			LinkedList<Question_bean> questions = new LinkedList<Question_bean>();
			while (rs.next()) {
				String owner = rs.getString("Login");
				String content = rs.getString("Content");
				String title = rs.getString("Question_title");
				int index = rs.getInt("Question_index");
				questions.add(new Question_bean(owner, title, content, index));
			}
			return questions;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public LinkedList<Question_bean> get_unanswered_questions(String username) {
		String query = "SELECT * from Question WHERE Login = '" + username
				+ "' AND Best_answer = 0;";
		LinkedList<Question_bean> questions = getQuestoinsFromSQLQuery(query);
		return questions;
	}

	public void chooseBestAnswer(int index, int best_answer) {
		try {
			getStatement().executeUpdate(
					"UPDATE Question SET Best_answer = " + best_answer
							+ " WHERE Question_index = " + index + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public LinkedList<Question_bean> searchSubstring(String substring){
		String query="SELECT * FROM";
		LinkedList<Question_bean> questions=getQuestoinsFromSQLQuery(query);
		return questions;
	}

	public JsonArray toJsonArray(LinkedList<Question_bean> questions) {
		JsonArray qjsons = new JsonArray();
		for (Question_bean question : questions) {
			JsonObject qjson = new JsonObject();
			qjson.addProperty("username", question.getOwner());
			qjson.addProperty("index", question.getIndex());
			qjson.addProperty("title", question.getTitle());
			qjson.addProperty("content", question.getContent());
			qjson.addProperty("best_answer", question.getBestAnswer());
			qjsons.add(qjson);
		}
		return qjsons;
	}

}
