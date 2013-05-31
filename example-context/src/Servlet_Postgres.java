// Servlet_Postgres.java - example connection to Postgres
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class Servlet_Postgres extends HttpServlet {
 
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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

            rs = stmt.executeQuery("SELECT * FROM Person");
	    out.println( "<table>" );
            while ( rs.next() ) {
                String title = rs.getString("login");
                String director = rs.getString("pass_word");
                //String origin = rs.getString("origin");
                //String made = rs.getString("made");
                //String length = rs.getString("length");
                out.println("<tr><td>"+title+"</td><td>"+director+"</td></tr>" );
            }
	    out.println( "</table>" );

            conn.close();
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
        out.println("</body>");
        out.println("</html>");
    }
}
