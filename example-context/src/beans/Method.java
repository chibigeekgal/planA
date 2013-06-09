package beans;

import java.sql.*;

public class Method {

	private Statement stmt;

	public Method() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
		
		}
		Connection conn;
		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u",
					"g1227111_u", "sHg5fr0Alb");
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public User_bean get_user(String login, String password) {
		try {
			ResultSet rs = stmt
					.executeQuery("SELECT Points FROM Person WHERE Login='"
							+ login + "'" + "AND Pass_word='" + password + "';");
			if (rs.next()) {
				return new User_bean(login, rs.getInt("Points"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User_bean register_user(String username, String password) {
		try {
			ResultSet rs = stmt
					.executeQuery("SELECT Login FROM Person WHERE Login='"
							+ username + "';");
			boolean duplicate_username = rs.next();
			if (duplicate_username) {
				return null;
			} else if (!duplicate_username) {
				/* insert the new user into database */
				stmt.executeUpdate("INSERT INTO Person VALUES(" + "'"
						+ username + "'" + ", '" + password + "',10);");
				return new User_bean(username, 10);
			}
		} catch (SQLException e) {
		}
		return null;
	}

	public void update_points(User_bean user, int change) {
		int new_points = user.getPoints() + change;
		try {
			stmt.executeQuery("UPDATE Person SET Points =" + new_points
					+ " WHERE Login='" + user.getUserName() + "';");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
