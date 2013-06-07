/* This is a servelet to display the webpage for the database*/
import java.io.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Question_Servlet extends HttpServlet {
 
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head>");
	out.println("<title> Database of MaaApp </title>");
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
	    rs = stmt.executeQuery("SELECT * FROM Question;");
	    out.println( "<table>" );
            while ( rs.next() ) {
                int index = rs.getInt("question_index");
                String login = rs.getString("login");
                String content = rs.getString("content");
                out.println("<tr><td>"+index+"</td><td>"+login+"</td><td>"+content+"</td></tr>" );
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
