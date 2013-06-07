import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Profile_Servlet extends HttpServlet {

    @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException, ServletException {

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	String username = request.getParameter("Login");

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
		.executeQuery("SELECT Points FROM Person WHERE Login='"+username+"';");

	    /* if not exist in database,have to type the login again */
	    if(rs.next()){
		int points=rs.getInt("points");
                out.println(points);
            }

	    conn.close();
	} catch (Exception e) {
	    out.println("<h1>exception: " + e + e.getMessage() + "</h1>");
	}
    }

}
