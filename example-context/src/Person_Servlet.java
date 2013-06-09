import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import beans.Method;
import beans.User_bean;

public class Person_Servlet extends HttpServlet {
	private User_bean user;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String username = request.getParameter("Login");
		String password = request.getParameter("Password");
		String requestType = request.getParameter("Request");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Method user_method = new Method();
		if (requestType.equals("login")) {
			user = user_method.get_user(username, password);
		}
		if (requestType.equals("register")) {
			user = user_method.register_user(username, password);
		}
		if (user == null) {
			out.println("Oh,no!");
		} else {
			out.println(user.getPoints());
		}
	}
}
