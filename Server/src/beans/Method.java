package beans;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;

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

	public byte[] EncodeImage(BufferedImage i) throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ImageIO.write(i, "png", bo);
		bo.flush();
		byte[] ba = bo.toByteArray();
		// String output=Base64.encode(ba);
		return ba;
	}

	public void update_points(User_bean user, int change) throws SQLException {
		int new_points = user.getPoints() + change;
		getStatement().executeUpdate(
				"UPDATE Person SET Points =" + new_points + " WHERE Login='"
						+ user.getUserName() + "';");
	}
}
