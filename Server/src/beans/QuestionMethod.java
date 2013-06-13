package beans;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class QuestionMethod extends Method {

	public QuestionMethod() throws SQLException {
		super();
	}

	public Question_bean ask_question(String user, String title, String content) {
		try {

			int index = get_max_index() + 1;
			// System.out.println("index");
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

	public LinkedList<Question_bean> getAllQuestions() {
		String SqlQuery = "SELECT * FROM Question;";
		return getQuestionsFromSQLQuery(SqlQuery);
	}
	
	public LinkedList<Question_bean> getAllUnansweredQuestions(){
		String SqlQuery= "SELECT * FROM Question WHERE Best_answer = 0;";
		return getQuestionsFromSQLQuery(SqlQuery);
	}

	private LinkedList<Question_bean> getQuestionsFromSQLQuery(String query) {
		ResultSet rs;
		try {
			rs = getStatement().executeQuery(query);
			LinkedList<Question_bean> questions = new LinkedList<Question_bean>();
			while (rs.next()) {
				String owner = rs.getString("Login");
				String content = rs.getString("Content");
				String title = rs.getString("Question_title");
				int index = rs.getInt("Question_index");
				int best_answer = rs.getInt("best_answer");
				questions.add(new Question_bean(owner, title, content, index,
						best_answer));
			}
			return questions;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public LinkedList<Question_bean> get_questions_by_username(String username) {
		String query = "SELECT * from Question WHERE Login = '" + username
				+ "' AND Best_answer = 0;";
		return getQuestionsFromSQLQuery(query);
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

	public LinkedList<Question_bean> searchSubstring(String substring) {
		String query = "SELECT * FROM Question WHERE Question_title LIKE '%"
				+ substring + "%' OR content LIKE '%" + substring + "%';";
		return getQuestionsFromSQLQuery(query);

	}
	
	public BufferedImage get_question_content(int index){
		try {
			ResultSet rs=getStatement().executeQuery("SELECT Content FROM Question WHERE Question_index = "+index+";");
			if(rs.next()){
				String content=rs.getString("Question_index");
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
			qjson.addProperty("title", question.getTitle());
			qjson.addProperty("best_answer", question.getBestAnswer());
			qjsons.add(qjson);
		}
		return qjsons;
	}
	
	private BufferedImage toBufferedImage(String s) {
		s = process(s);
		TeXFormula t = new TeXFormula(s);
		TeXIcon icon = t.new TeXIconBuilder()
				.setStyle(TeXConstants.STYLE_DISPLAY).setSize(20).build();
		icon.setInsets(new Insets(5, 5, 5, 5));

		BufferedImage i = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = i.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
		JLabel jl = new JLabel();
		jl.setForeground(new Color(0, 0, 0));
		icon.paintIcon(jl, g2, 0, 0);
		return i;
	}

	private static String process(String s2) {
		String result = "";
		int length = s2.length();
		for (int i = 0; i < length - 1; i++) {
			if (s2.charAt(i) == '\\' && s2.charAt(i + 1) == '\\') {
				i++;
			}
			result += String.valueOf(s2.charAt(i));
		}
		return result;
	}

}
