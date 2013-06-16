package beans;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class AnswerMethod extends Method {

	public AnswerMethod() throws SQLException {
		super();
	}

	public void addAnswer(String username, int qIndex, String content) {
		try {
			int aIndex = 1;
			ResultSet rs = getStatement().executeQuery(
					"SELECT COUNT ( Answer_index ) FROM Answer WHERE Question_index = "
							+ qIndex + ";");
			if (rs.next()) {
				aIndex += rs.getInt("count");
			}
			System.out.println("index " + aIndex);
			getStatement().executeUpdate(
					"INSERT INTO Answer VALUES (" + qIndex + "," + aIndex
							+ ",'" + username + "','" + content + "');");
			rs = getStatement().executeQuery(
					"SELECT Points FROM Person WHERE Login = '" + username
							+ "';");
			int points = 0;
			if (rs.next())
				points = rs.getInt("Points") + 1;
			System.out.println("Points " + points);
			getStatement().executeUpdate(
					"UPDATE Person SET Points = " + points + " WHERE Login = '"
							+ username + "';");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JsonArray getAnswers(int qIndex) throws IOException {
		JsonArray answers = new JsonArray();
		try {
			ResultSet rs = getStatement().executeQuery(
					"SELECT * FROM Answer WHERE Question_index = " + qIndex
							+ ";");
			while (rs.next()) {
				JsonObject o = new JsonObject();
				String username = rs.getString("Login");
				String content = rs.getString("Answer_content");
				byte[] ba = encodeImage(toBufferedImage(content));
				o.addProperty("username", username);
				o.addProperty("content", Base64.encode(ba));
				answers.add(o);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answers;
	}

	public LinkedList<Answer_bean> getAllAnswers() {
		LinkedList<Answer_bean> answers = new LinkedList<Answer_bean>();
		try {
			ResultSet rs = getStatement().executeQuery("SELECT * FROM Answer");
			while (rs.next()) {
				int qIndex = rs.getInt("Question_index");
				int aIndex = rs.getInt("Answer_index");
				String username = rs.getString("Login");
				String content = rs.getString("Answer_content");
				answers.add(new Answer_bean(qIndex, aIndex, username, content));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answers;
	}
}
