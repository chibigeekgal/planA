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
	    /* Get the password from the username etc using sql, then
	     * do the check, if everything matches then give back the
	     * points for the user */

	    ResultSet rs = stmt.executeQuery("SELECT Login FROM Person WHERE Login='"+username+"';");
	    /*password_exist = stmt.execute("SELECT Pass_word FROM Person WHERE Pass_word="+"'"+password+"'"+";");*/

			    out.println(username);
	    /*if not exist in database,have to type the login again*/
	    boolean plz = rs.next(); 
	    
            if(!plz){			    
	    out.println("There is an error1423.");
		        File f = new File("/homes/dz1611/planA/e123log.txt");
             FileWriter fw = new FileWriter(f.getAbsoluteFile());
             BufferedWriter bw = new BufferedWriter(fw);
            bw.write(username+"  ");
            bw.close();
		//throw new Exception();
	    }else if(plz){
	    out.println("on baby");
		}
	conn.close();
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
    }
    
}


















