package beans;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

public class Method {

	private Statement stmt;

	public Method()  {
		try {
			Class.forName("org.postgresql.Driver");
		Connection conn;
		
		conn = DriverManager
				.getConnection(
						"jdbc:postgresql://db.doc.ic.ac.uk/g1227111_u?&ssl=true"
								+ "&sslfactory=org.postgresql.ssl.NonValidatingFactory",
						"g1227111_u", "sHg5fr0Alb");
		stmt = conn.createStatement();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	protected Statement getStatement() {
		return stmt;
	}
	
	protected byte[] toByteArray(String s) throws IOException {
		TeXFormula t = new TeXFormula(s.replaceAll(" ", "\\\\:"));
		TeXIcon icon = t.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);
		icon.setInsets(new Insets(5, 5, 5, 5));
		BufferedImage i = new BufferedImage(icon.getIconWidth(),
				icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = i.createGraphics();
		g2.setColor(Color.white);
		g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
		JLabel jl = new JLabel();
		jl.setForeground(new Color(0, 0, 0));
		icon.paintIcon(jl, g2, 0, 0);
		return encodeImage(i);
	}
	
	public byte[] encodeImage(BufferedImage i) throws IOException{
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ImageIO.write(i, "png", bo);
		bo.flush();
		byte[] ba = bo.toByteArray();
		return ba;
	}
}
