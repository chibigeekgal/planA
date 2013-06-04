/* The register screen*/
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Register_Servlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("Login");
        String password = request.getParameter("Password");
        PrintWriter out = response.getWriter();
	try {	    
	    Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
	    out.println("<h1>Driver not found: " + e + e.getMessage() + "</h1>" );
        }
      File f = new File("/homes/hs2711/planA/e123log.txt");
    FileWriter fw = new FileWriter(f.getAbsoluteFile());
    BufferedWriter bw = new BufferedWriter(fw);
	try { 
	    Connection conn = DriverManager.getConnection (
							   "jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",	
							   "g1227111_u", "sHg5fr0Alb" );
	    Statement stmt = conn.createStatement();
	    int result;

	    result = stmt.executeUpdate("INSERT INTO Person VALUES("+"'"+username+"'"+", '"+password+"',10);");
      bw.write(username+"  ");
//      bw.close();

	    conn.close();
	} catch (Exception e) {
	    out.println( "<h1>exception: "+e.getMessage()+"</h1>" );
	    bw.write(e.getMessage());
	} finally {
    bw.close();	
	}
    }
}
