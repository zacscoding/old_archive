package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
	}	
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		}
	}	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
			}
		}
	}
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs) {
		close(rs);
		close(pstmt);
		close(conn);
	}
	public static void close(Connection conn,PreparedStatement pstmt) {		
		close(pstmt);
		close(conn);
	}
	
}
