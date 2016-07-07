package edu.psu.teamone.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Map;
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
			// ResultSet = st.executeQuery("SELECT COUNT(*) FROM classes");
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

	public void addMeeting(int meetingId, String startTime, String endTime, String days) {
		// course Id and meeting Id are same, thus meeting 0000001 means it
		// teaches course 0000001
		// days indicate which days the meeting is held. "00101" means
		// Wednesdays and Fridays
		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			// Add meeting to db
			// Ex) 100000, Mec
			String command = String.format(
					"INSERT INTO meetings VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s')", meetingId,
					startTime, endTime, days.charAt(0), days.charAt(1), days.charAt(2), days.charAt(3), days.charAt(4));
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

	public int generateId() {
		// Check if database is empty, if yes give 1000000 to course id.
		// If not, give last row's course id added by 1.
		// Ex) If Last row's course id is 1000003, give 1000004
		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			ResultSet getData = st.executeQuery("SELECT * FROM classes");
			int rowCount = 0;
			String lastId = "";
			while (getData.next()) {
				rowCount++;
				lastId = getData.getString(1);
			} /*
				 * if (rowCount == 0) { return 1000000; } else { return
				 * Integer.parseInt(lastId) + 1; }
				 */
			return rowCount == 0 ? 1000000 : Integer.parseInt(lastId) + 1;
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
		return 0;
	}

	public int checkEmpty() {
		// Check if database is empty, if yes give 1000000 to course id.
		// If not, give last row's course id added by 1.
		// Ex) If Last row's course id is 1000003, give 1000004
		try {

			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			ResultSet getData = st.executeQuery("SELECT * FROM classes");
			int rowCount = 0;
			String lastId = "";
			while (getData.next()) {
				rowCount++;
				lastId = getData.getString(1);
			} /*
				 * if (rowCount == 0) { return 1000000; } else { return
				 * Integer.parseInt(lastId) + 1; }
				 */
			return rowCount == 0 ? 1000000 : Integer.parseInt(lastId) + 1;
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
		return 0;
	}

	void getDataFromDB(ArrayList<Section> section, ArrayList<Meeting> meeting) {
		try {
			con = DriverManager.getConnection(url, user, password);
			st = con.createStatement();
			ArrayList<Section> tempSection = new ArrayList<Section>();
			ArrayList<Meeting> tempMeeting = new ArrayList<Meeting>();
			ResultSet getData = st.executeQuery("SELECT * FROM classes");
			while (getData.next()) {
				tempSection.add(new Section(getData.getString(2), getData.getString(3),
						Integer.parseInt(getData.getString(1))));
				// System.out.println(getData.getString(2)+ " " +
				// getData.getString(3) + " " +
				// Integer.parseInt(getData.getString(1)));
			}
			ResultSet getData2 = st.executeQuery("SELECT * FROM meetings");
			while (getData2.next()) {
				boolean[] days = new boolean[7];
				// Time startTime = new Time(getData2.getString(2)+ ":00");
				Time startTime = java.sql.Time.valueOf(getData2.getString(2));
				Time endTime = java.sql.Time.valueOf(getData2.getString(3));
				days[0] = Integer.parseInt(getData2.getString(4)) == 1 ? true : false;
				days[1] = Integer.parseInt(getData2.getString(5)) == 1 ? true : false;
				days[2] = Integer.parseInt(getData2.getString(6)) == 1 ? true : false;
				days[3] = Integer.parseInt(getData2.getString(7)) == 1 ? true : false;
				days[4] = Integer.parseInt(getData2.getString(8)) == 1 ? true : false;
				// System.out.println(days[0]+ " " +days[1]+" " +days[2]+" "
				// +days[3]+" " +days[4]);
				// System.out.println(startTime);
				// System.out.println(endTime);
				tempMeeting.add(new Meeting(days, startTime, endTime));
			}
			section = tempSection;
			meeting = tempMeeting;
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