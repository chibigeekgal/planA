/* The Login screen*/
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login_Servlet extends HttpServlet {
 
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
	
        try {
 	    Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            out.println("<h1>Driver not found: " + e + e.getMessage() + "</h1>" );
        }
	try { 
	    Connection conn = DriverManager.getConnection (
							   "jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",
							   "g1227111_u", "sHg5fr0Alb" );

            Statement stmt = conn.createStatement();
	    /* Get the password from the username etc using sql, then
	     * do the check, if everything matches then give back the
	     * points for the user */
	    conn.close();
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
    }
    
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Login");
        String password = request.getParameter("Password");

	//        User user = userDAO.find(username, password);
        PrintWriter out = response.getWriter();
	/*	System.out.println("Entered response.");
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>" + username + password+ "</h1>");*/
	out.println("Daling is Fred.");
	try {	    
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
	    out.println("<h1>Driver not found: " + e + e.getMessage() + "</h1>" );
        }
	
	try { 
	    Connection conn = DriverManager.getConnection (
							   "jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",	
						   "g1227111_u", "sHg5fr0Alb" );
	    
	    Statement stmt = conn.createStatement();
	          
	    int result;
	    result = stmt.executeUpdate("INSERT INTO Person VALUES("+"'"+username+"'"+", '"+password+"',10);");
	    conn.close();
	} catch (Exception e) {
	    out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
	}
	out.println("</body>");
        out.println("</html>");
        
    }
}


















