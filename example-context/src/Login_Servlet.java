/* The Login screen*/
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login_Servlet extends HttpServlet {
 
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        PrintWriter out = response.getWriter();
        String username = request.getParameter("Login");
        String password = request.getParameter("Password");
	
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
	    boolean login_exist = false;
	    boolean password_exist = false;
	    /* Get the password from the username etc using sql, then
	     * do the check, if everything matches then give back the
	     * points for the user */
	    login_exist = stmt.execute("SELECT EXISTS (SELECT Login FROM Person WHERE Login="
							+"'"+username+"'"+");");
	    password_exist = stmt.execute("SELECT EXISTS (SELECT Pass_word FROM Person WHERE 									Pass_word="+"'"+password+"'"+");");
	    
	    /*if not exist in database,have to type the login again*/
	    if(login_exist && password_exist == false){
		throw new Exception();
	    }
	      else{
		/*if correct entering,log into the system*/
		out.println("Oh baby!");
		conn.close();
			}	
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
    }
    
}


















