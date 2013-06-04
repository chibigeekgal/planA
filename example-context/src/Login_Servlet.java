/* The Login screen*/

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login_Servlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("Login");
		String password = request.getParameter("Password");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			out.println("<h1>Driver not found: " + e + e.getMessage() + "</h1>");
		}
		try {
			Connection conn = DriverManager.getConnection(
					"jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",
					"g1227111_u", "sHg5fr0Alb");

			Statement stmt = conn.createStatement();
			/*
			 * Check the username and password in database,then do the check, if
			 * everything matches then give back the points for the user
			 */
  
			ResultSet rs = stmt
					.executeQuery("SELECT Login FROM Person WHERE Login='"
							+ username + "'" + "AND Pass_word='" +     password
							+ "';");

			/* if not exist in database,have to type the login again */
			boolean valid_login = rs.next();

			if (!valid_login) {
				out.println("error");
				// throw new Exception();
			} else if (valid_login) {
				out.println("oh baby, you are in!");
			}

			conn.close();
		} catch (Exception e) {
			out.println("<h1>exception: " + e + e.getMessage() + "</h1>");
		}
	}

}

