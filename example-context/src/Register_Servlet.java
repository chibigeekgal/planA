/* The register screen*/

import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register_Servlet extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("Login");
		String password = request.getParameter("Password");
		PrintWriter out = response.getWriter();

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
			int result;
			/*check the duplication of username*/
			ResultSet rs = stmt
					.executeQuery("SELECT Login FROM Person WHERE Login='"
							+ username +"';");
			boolean duplicate_username = rs.next();
			if (duplicate_username) {
				out.println("Username already exist!");
				// throw new Exception();
			} else if (!duplicate_username) {
				/*insert the new user into database*/
			        result = stmt.executeUpdate("INSERT INTO Person VALUES(" + "'"
					+ username + "'" + ", '" + password + "',10);");
				out.println("Welcome to the world of Maths!");
			}
							
			conn.close();
		} catch (Exception e) {
			out.println("<h1>exception: " + e.getMessage() + "</h1>");

		}
	}
}

