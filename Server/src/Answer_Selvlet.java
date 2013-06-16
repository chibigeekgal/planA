import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AnswerMethod;
import beans.Answer_bean;

import com.google.gson.JsonArray;

/**
 * Servlet implementation class AnswerSelvlet
 */
@WebServlet("/answer")
public class Answer_Selvlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Database of Answers </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<table>");
		try {
			LinkedList<Answer_bean> answers = new AnswerMethod()
					.getAllAnswers();
			for (Answer_bean answer : answers) {
				out.println("<tr><td>" + answer.getQuestionIndex()
						+ "</td><td>" + answer.getAnswerIndex() + "</td><td>"
						+ answer.getContributor() + "</td><td>"
						+ answer.getContent() + "</td></tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			AnswerMethod answermethod = new AnswerMethod();
			String query = request.getParameter("request");
			String username = request.getParameter("username");
			int qIndex = Integer.parseInt(request.getParameter("index"));
			String content = request.getParameter("content");
			PrintWriter out = response.getWriter();
			if (query.equals("answer")) {
				answermethod.addAnswer(username, qIndex, content);
			}
			if (query.equals("get_answers")) {
				JsonArray answers = answermethod.getAnswers(qIndex);
				out.println(answers);
			}
		} catch (SQLException e) {

		}
	}

}
