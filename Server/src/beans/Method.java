package beans;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class Method {

	private Statement stmt;

	public Method() throws SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}
		Connection conn;
		conn = DriverManager
				.getConnection(
						"jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u?&ssl=true"
								+ "&sslfactory=org.postgresql.ssl.NonValidatingFactory",
						"g1227111_u", "sHg5fr0Alb");
		stmt = conn.createStatement();
	}
	
	protected Statement getStatement() {
		return stmt;
	}
	public User_bean get_user(String login, String password)
			throws SQLException, IOException {
		ResultSet rs = getStatement().executeQuery(
				"SELECT Points FROM Person WHERE Login='" + login + "'"
						+ "AND Pass_word='" + password + "';");
		if (rs.next()) {
			return new User_bean(login, password, rs.getInt("Points"));
		}
		return null;
	}

	public User_bean register_user(String username, String password)
			throws SQLException {
		ResultSet rs = getStatement().executeQuery(
				"SELECT Login FROM Person WHERE Login='" + username + "';");
		boolean duplicate_username = rs.next();
		if (duplicate_username) {
			return null;
		}

		/* insert the new user into database */
		getStatement().executeUpdate(
				"INSERT INTO Person VALUES(" + "'" + username + "', '"
						+ password + "',10);");
		return new User_bean(username, password, 10);
	}

	public LinkedList<User_bean> get_all_user() throws SQLException {
		LinkedList<User_bean> users = new LinkedList<User_bean>();
		ResultSet rs = getStatement().executeQuery("SELECT * FROM Person;");
		while (rs.next()) {
			String username = rs.getString("Login");
			String password = rs.getString("Pass_word");
			int points = rs.getInt("Points");
			users.add(new User_bean(username, password, points));
		}
		return users;
	}

	public void update_points(User_bean user, int change) throws SQLException {
		int new_points = user.getPoints() + change;
		getStatement().executeUpdate("UPDATE Person SET Points =" + new_points
				+ " WHERE Login='" + user.getUserName() + "';");
	}
}
