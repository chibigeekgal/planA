import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserMethod;
import beans.User_bean;

/**
 * Servlet implementation class Person_Servlet
 */
@WebServlet("/person")
public class Person_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User_bean user;
	private UserMethod user_method;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Person_Servlet() {
		super();
		try {
			user_method = new UserMethod();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Database of Users </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<table>");
		LinkedList<User_bean> users;
		users = user_method.get_all_user();

		for (User_bean user : users) {
			out.println("<tr><td>" + user.getUserName() + "</td><td>"
					+ user.getPassword() + "</td><td>" + user.getPoints()
					+ "</td></tr>");
		}
		out.println("</table>");

		out.println(getServletContext().getRealPath(File.separator));
		out.println();
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		PrintWriter out = response.getWriter();
		String username = request.getParameter("Login");
		String password = request.getParameter("Password");
		String requestType = request.getParameter("Request");
		System.out.println(request);
		response.setContentType("text/html");
		try {
			if (requestType.equals("login")) {
				user = user_method.get_user(username, password);
				if (user == null)
					out.println("error");
				else
					out.println(user.getPoints());
			}
			if (requestType.equals("register")) {
				user = user_method.register_user(username, password);
				if (user == null)
					out.println("exist");
				else
					out.println(user.getPoints());
			}
		} catch (SQLException e) {
			out.println("SQL Exception");
		}
	}

}
