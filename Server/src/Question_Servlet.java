import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.QuestionMethod;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Question_Servlet
 */
@WebServlet("/question")
public class Question_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Database of Questions </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<table>");
		try {
			JsonArray questions = new QuestionMethod().getAllQuestions();
			for (int i = 0; i < questions.size(); i++) {
				JsonObject o = questions.get(i).getAsJsonObject();
				out.println("<tr><td>" + o.get("index") + "</td><td>"
						+ o.get("username") + "</td><td>" + o.get("title")
						+ "</td><td>" + o.get("content") + "</td><td>"
						+ o.get("best_answer") + "</td></tr>");
			}
		} catch (SQLException e) {

		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("request");
		String indexString = request.getParameter("index");
		try {
			QuestionMethod question_method = new QuestionMethod();

			int index = 0;
			if (indexString != null) {
				index = Integer.parseInt(indexString);
			}
			String owner = request.getParameter("username");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String substring = request.getParameter("substring");
			String bestAnswerText = request.getParameter("best_answer");
			int bestAnswer = 0;
			if (bestAnswerText != null)
				bestAnswer = Integer.parseInt(request
						.getParameter("best_answer"));
			response.setContentType("application/json");
			ServletOutputStream out = response.getOutputStream();
			if (query.equals("ask")) {
				new QuestionMethod().ask_question(owner, title, content);
			}
			if (query.equals("get_all")) {
				JsonArray qjsons = question_method.getAllUnansweredQuestions();
				out.println(qjsons.toString());
			}
			if (query.equals("get_content")) {
				byte[] ba = new QuestionMethod().get_question_content(index);
				out.write(ba);
			}
			if (query.equals("choose_best")) {
				new QuestionMethod().chooseBestAnswer(index, bestAnswer);
			}
			if (query.equals("get_question_info")) {
				JsonArray questions = question_method
						.get_questions_by_username(owner);
				out.println(questions.toString());
			}
			if (query.equals("search")) {
				JsonArray questions = question_method
						.searchSubstring(substring);
				out.println(questions.toString());
			}
		} catch (SQLException e) {

		}
	}
}
