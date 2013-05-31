<%@ page language="java" import="java.sql.*"%>
<html>
<head>
<title> Films Example: JSP, Postgres version</title>
</head>
<body bgcolor="white">
<%
        try {
 	    Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            out.println("<h1>Driver not found:" + e + e.getMessage() + "</h1>" );
        }
	try {
	    Connection conn = DriverManager.getConnection (
	    	"jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",
		"g1227111_u", "sHg5fr0Alb" );

            Statement stmt = conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM Person");
	    out.println( "<table border=1>" );
            while ( rs.next() ) {
                String title = rs.getString("login");
                String director = rs.getString("pass_word");
                
                out.println("<tr><td>"+title+"</td><td>"+director+"</td></tr>");
            }
	    out.println( "</table>" );

            conn.close();
        } catch (Exception e) {
            out.println( "<h1>exception: "+e+e.getMessage()+"</h1>" );
        }
%>
</body>
</html>
