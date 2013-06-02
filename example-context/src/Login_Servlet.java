// Servlet_Postgres.java - example connection to Postgres
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Login_Servlet extends HttpServlet {
 
     
    //private UserDAO userDAO;
    
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //String select = request.getParameter("SELECT");
        //String from = request.getParameter("FROM");
        System.err.println("hello");
        
        out.println("<html>");
        out.println("<head>");
	out.println("<title> Films Example: Servlet, Postgres version</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

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
            ResultSet rs;
            //int result;
            //result = stmt.executeUpdate("INSERT INTO Person VALUES()");
            rs = stmt.executeQuery("SELECT * FROM Person;");
	    out.println( "<table>" );
            while ( rs.next() ) {
                String title = rs.getString("login");
                String director = rs.getString("pass_word");
                int origin = rs.getInt("points");
                out.println("<tr><td>"+title+"</td><td>"+director+"</td><td>"+origin+"</td></tr>" );
            }
	    out.println( "</table>" );

            conn.close();
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
        out.println("</body>");
        out.println("</html>");
    }






    /*  public void init() {
        this.userDAO = Config.getInstance(getServletContext()).getDAOFactory().getUserDAO();
	}
    */
   
   
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


















