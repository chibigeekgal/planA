package beans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class UserMethod extends Method {

	public UserMethod() throws SQLException {
		super();
	}

	public User_bean get_user(String login, String password)
			throws IOException, SQLException {

		ResultSet rs = getStatement().executeQuery(
				"SELECT Points FROM Person WHERE Login='" + login + "'"
						+ "AND Pass_word='" + password + "';");

		if (rs.next()) {
			return new User_bean(login, password, rs.getInt("Points"));
		}
		File f = new File("/homes/dz1611/planA/elog.txt");
		FileWriter fw = new FileWriter(f.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("done this");
		bw.close();
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

	public void update_icon(String icon, String username) throws SQLException {
		getStatement().executeUpdate(
				"UPDATE Person SET Icon = '" + icon + "' WHERE Login = '"
						+ username + "';");
	}

	public void update_password(String username,String password) throws SQLException{
		getStatement().executeQuery("UPDATE Person SET Password = '"+password+"' WHERE Login = '"+username +"';");
	}

	public String get_icon(String username) throws SQLException {
		ResultSet rs = getStatement().executeQuery(
				"SELECT Icon FROM Person WHERE Login = '" + username + "';");
		String icon = "";
		if (rs.next()) {
			icon = rs.getString("Icon");
		}
		return icon;
	}

}
