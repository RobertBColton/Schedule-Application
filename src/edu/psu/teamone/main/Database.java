package edu.psu.teamone.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	// db connection credentials
	private String url = "jdbc:mysql://localhost:3306/schedule";
	private String user = "root";
	private String password = "root";

	public void addClass(int courseId, String courseName, String courseAbb) {
		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			// Add classes to db
			// Ex) 100000, Mec
			String command = String.format("INSERT INTO classes VALUES ('%d', '%s', '%s')", courseId, courseName,
					courseAbb);
			st.executeUpdate(command);
			st.close();
		} catch (SQLException ex) {

			Logger lgr = Logger.getLogger(Database.class.getName());
			lgr.log(Level.SEVERE, ex.getMessage(), ex);

		} finally {
			// close db connection
			try {

				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}

			} catch (SQLException ex) {

				Logger lgr = Logger.getLogger(Database.class.getName());
				lgr.log(Level.WARNING, ex.getMessage(), ex);
			}
		}
	}
}