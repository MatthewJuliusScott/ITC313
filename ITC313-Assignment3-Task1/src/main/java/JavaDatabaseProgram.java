import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;

public class JavaDatabaseProgram {

	public static void main(String[] args) {
		try {
			DB database = DB.newEmbeddedDB(3306);
			database.start();

			JavaDatabaseProgram jdp = new JavaDatabaseProgram();

			try {
				jdp.createTable("Staff");
			} catch (SQLException e) {
				// connection or sql syntax error
				e.printStackTrace();
			}

		} catch (ManagedProcessException e) {
			// failed to create in memory database
			e.printStackTrace();
		}

	}

	public void createTable(String tableName) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "CREATE TABLE IF NOT EXISTS ? (\r\n"
		        + "        id INT NOT NULL AUTO_INCREMENT,\r\n"
		        + "        lastName VARCHAR(30) NOT NULL,\r\n"
		        + "        firstName VARCHAR(30) NOT NULL,\r\n"
		        + "        middleInitial VARCHAR(2) NOT NULL,\r\n"
		        + "        address VARCHAR(255) NOT NULL,\r\n"
		        + "        city VARCHAR(30) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        telephoneNumber VARCHAR(10) NOT NULL,\r\n"
		        + "        PRIMARY KEY (ID)\r\n" + "    );";

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
			        "root", "");
			pstmt = conn.prepareStatement(sql);
			int count = 1;
			pstmt.setString(count++, tableName);

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public int insertStaff(Staff staff) throws SQLException {
		int id = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO Staff VALUES (0, ?, ?, ?, ?, ?, ?, ?);";

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
			        "root", "");
			pstmt = conn.prepareStatement(sql);
			int count = 1;
			pstmt.setString(count++, staff.getLastName());
			pstmt.setString(count++, staff.getFirstName());
			pstmt.setString(count++, staff.getMiddleInitial());
			pstmt.setString(count++, staff.getAddress());
			pstmt.setString(count++, staff.getCity());
			pstmt.setString(count++, staff.getState());
			pstmt.setString(count++, staff.getTelephoneNumber());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return id;
	}
	
	public void updateStaff(Staff staff) throws SQLException {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO  (\r\n"
		        + "        id INT NOT NULL AUTO_INCREMENT,\r\n"
		        + "        lastName VARCHAR(30) NOT NULL,\r\n"
		        + "        firstName VARCHAR(30) NOT NULL,\r\n"
		        + "        middleInitial VARCHAR(2) NOT NULL,\r\n"
		        + "        address VARCHAR(255) NOT NULL,\r\n"
		        + "        city VARCHAR(30) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        telephoneNumber VARCHAR(10) NOT NULL,\r\n"
		        + "        PRIMARY KEY (ID)\r\n" + "    );";

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
			        "root", "");
			pstmt = conn.prepareStatement(sql);
			int count = 1;
			pstmt.setString(count++, "");

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
	
	public Staff viewStaff(int id) throws SQLException {
		Staff staff = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO  (\r\n"
		        + "        id INT NOT NULL AUTO_INCREMENT,\r\n"
		        + "        lastName VARCHAR(30) NOT NULL,\r\n"
		        + "        firstName VARCHAR(30) NOT NULL,\r\n"
		        + "        middleInitial VARCHAR(2) NOT NULL,\r\n"
		        + "        address VARCHAR(255) NOT NULL,\r\n"
		        + "        city VARCHAR(30) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        state VARCHAR(3) NOT NULL,\r\n"
		        + "        telephoneNumber VARCHAR(10) NOT NULL,\r\n"
		        + "        PRIMARY KEY (ID)\r\n" + "    );";

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test",
			        "root", "");
			pstmt = conn.prepareStatement(sql);
			int count = 1;
			pstmt.setString(count++, "");

			rs = pstmt.executeQuery();

		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return staff;
	}

}
