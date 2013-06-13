import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.QuestionMethod;
import beans.Question_bean;

import com.google.gson.JsonArray;

/**
 * Servlet implementation class Question_Servlet
 */
@WebServlet("/question")
public class Question_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuestionMethod question_method;

	public Question_Servlet() {
		super();
		try {
			question_method = new QuestionMethod();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
		LinkedList<Question_bean> questions = question_method.getAllQuestions();
		for (int i = 0; i < questions.size(); i++) {
			Question_bean question = questions.get(i);
			out.println("<tr><td>" + question.getIndex() + "</td><td>"
					+ question.getOwner() + "</td><td>" + question.getTitle()
					+ "</td><td>" + question.getContent() + "</td><td>"
					+ question.getBestAnswer() + "</td></tr>");
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
			bestAnswer = Integer.parseInt(request.getParameter("best_answer"));
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		if (query.equals("ask")) {
			question_method.ask_question(owner, title, content);
		}
		if(query.equals("get_all")){
			JsonArray qjsons = question_method.toJsonArray(question_method.getAllUnansweredQuestions());
			out.println(qjsons);
		}
		if(query.equals("get_content")){
			response.setContentType("image/png");
			
		}
		if (query.equals("choose_best")) {
			question_method.chooseBestAnswer(index, bestAnswer);
		}
		if (query.equals("get_question_info")) {
			out.println(question_method.get_questions_by_username(owner));
		}
		if (query.equals("search")) {
			out.println(question_method.searchSubstring(substring));
			OutputStream o=response.getOutputStream();
			ImageIO.write(question_method.get_question_content(index), "png", o);
		}
	}
}
