import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.postgresql.util.Base64;

import beans.UserMethod;
import beans.User_bean;



/**
 * Servlet implementation class Person_Servlet
 */
@WebServlet("/person")
public class Person_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User_bean user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Person_Servlet() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Database of Users </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");

		out.println("<table>");
		LinkedList<User_bean> users;
		try {
			users = new UserMethod().get_all_user();

			for (User_bean user : users) {
				out.println("<tr><td>" + user.getUserName() + "</td><td>"
						+ user.getPassword() + "</td><td>" + user.getPoints()
						+ "</td></tr>");
			}
		} catch (SQLException e) {

		}
		out.println("</table>");

		out.println(getServletContext()
				.getRealPath("icons/ic_launcher-web.png"));
		out.println();
		out.println("</body>");
		out.println("</html>");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ServletOutputStream out = response.getOutputStream();
		String username = request.getParameter("Login");
		String password = request.getParameter("Password");
		String requestType = request.getParameter("Request");
		String icon = request.getParameter("Icon");
		try {
			UserMethod user_method = new UserMethod();
			if (requestType.equals("login")) {
				user = user_method.get_user(username, password);
				if (user == null)
					out.println("error");
				else
					out.println(user.getPoints());
			}
			if (requestType.equals("register")) {
				user = user_method.register_user(username, password);
				if (user == null)
					out.println("exist");
				else
					out.println(user.getPoints());
			}
			if (requestType.equals("edit_password")) {
				user_method.update_password(username, password);
			}
			if (requestType.equals("set_icon")) {
				user_method.update_icon(icon, username);
			}
			if (requestType.equals("get_icon")) {
				String imageString = user_method.get_icon(username);
				BufferedImage image;
				if (imageString == null) {
					File f = new File(getServletContext().getRealPath(
							"icons/default_pic.png"));
					image = ImageIO.read(f);
					ImageIO.write(image, "png", out);
				} else {
					byte[] bytes;
					try {
						bytes = Base64.decode(imageString);
						InputStream in = new ByteArrayInputStream(bytes);
						image = ImageIO.read(in);
						ImageIO.write(image, "png", out);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
