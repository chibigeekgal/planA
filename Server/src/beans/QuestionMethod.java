package beans;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class QuestionMethod extends Method {

	public QuestionMethod() throws SQLException {
		super();
	}

	public Question_bean ask_question(String user, String title, String content) {
		try {

			int index = get_max_index() + 1;
			getStatement().executeUpdate(
					"Insert INTO Question VALUES (" + index + ", '" + title
							+ "', '" + user + "', '" + content + "', 0);");
			return new Question_bean(user, title, content, index, 0);
		} catch (SQLException e) {

		}
		return null;
	}

	private int get_max_index() throws SQLException {
		ResultSet rs;
		rs = getStatement().executeQuery(
				"SELECT COUNT (Question_index) FROM Question;");

		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	public Question_bean getQuestionInfoByIndex(int index) {
		ResultSet rs;
		try {
			rs = getStatement().executeQuery(
					"SELECT * FROM Question WHERE Question_index = '" + index
							+ "';");
			if (rs.next()) {
				return new Question_bean(rs.getString("Login"),
						rs.getString("Question_title"),
						rs.getString("Question_content"), index,
						rs.getInt("best_answer"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public JsonArray getAllQuestions() {
		String SqlQuery = "SELECT * FROM Question;";
		return getQuestionsFromSQLQuery(SqlQuery);
	}

	public JsonArray getAllUnansweredQuestions() {
		String SqlQuery = "SELECT * FROM Question NATURAL JOIN Person WHERE Best_answer = 0 ORDER BY Points DESC;";
		return getQuestionsFromSQLQuery(SqlQuery);
	}

	private JsonArray getQuestionsFromSQLQuery(String query) {
		ResultSet rs;
		try {
			rs = getStatement().executeQuery(query);
			LinkedList<Question_bean> questions = new LinkedList<Question_bean>();
			while (rs.next()) {
				String owner = rs.getString("Login");
				String content = rs.getString("Content");
				String title = rs.getString("Question_title");
				int index = rs.getInt("Question_index");
				int best_answer = rs.getInt("Best_answer");
				questions.add(new Question_bean(owner, title, content, index,
						best_answer));
			}
			return toJsonArray(questions);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public JsonArray get_questions_by_username(String username) {
		String query = "SELECT * from Question WHERE Login = '" + username
				+ "' AND Best_answer = 0;";
		return getQuestionsFromSQLQuery(query);
	}

	public void chooseBestAnswer(int index, int best_answer) {
		try {
			getStatement().executeUpdate(
					"UPDATE Question SET Best_answer = " + best_answer
							+ " WHERE Question_index = " + index + ";");
			ResultSet rs = getStatement()
					.executeQuery(
							"SELECT Points, Login FROM Person NATURAL JOIN Question NATURAL JOIN Answer WHERE Question_index = "
									+ index
									+ " AND Answer_index = "
									+ best_answer);
			if (rs.next()) {
				String username = rs.getString("Login");
				int points = rs.getInt("Points") + 5;
				System.out.println(username);
				System.out.println(points);
				getStatement().executeUpdate(
						"UPDATE Person SET Points = " + points
								+ " WHERE Login = '" + username + "';");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JsonArray searchSubstring(String substring) {
		String query = "SELECT * FROM Question WHERE Question_title LIKE '%"
				+ substring + "%' OR content LIKE '%" + substring + "%';";
		return getQuestionsFromSQLQuery(query);

	}

	public BufferedImage get_question_content(int index) throws IOException {
		try {
			ResultSet rs = getStatement().executeQuery(
					"SELECT Content FROM Question WHERE Question_index = "
							+ index + ";");
			if (rs.next()) {
				String content = rs.getString("content");
				return toBufferedImage(content);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public JsonArray toJsonArray(LinkedList<Question_bean> questions) {
		JsonArray qjsons = new JsonArray();
		for (Question_bean question : questions) {
			JsonObject qjson = new JsonObject();
			qjson.addProperty("username", question.getOwner());
			qjson.addProperty("index", question.getIndex());
			qjson.addProperty("content", question.getContent());
			qjson.addProperty("title", question.getTitle());
			qjson.addProperty("best_answer", question.getBestAnswer());
			qjsons.add(qjson);
		}
		return qjsons;
	}

	/*
	 * private static String process(String s2) { String result = ""; int length
	 * = s2.length(); for (int i = 0; i < length - 1; i++) { if (s2.charAt(i) ==
	 * '\\' && s2.charAt(i + 1) == '\\') { i++; } result +=
	 * String.valueOf(s2.charAt(i)); } return result; }
	 */

}
