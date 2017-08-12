package com.faceontalk.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	private JdbcUtil(){}
	
	public static void close(ResultSet rs) {
		if(rs!=null) {
			try {rs.close();} catch(SQLException ignored){}
		}			
	}	
	public static void close(Connection Conn) {
		if(Conn != null) {
			try {Conn.close();} catch(SQLException ignored){}
		}			
	}
	
	public static void close(PreparedStatement pstmt) {
		if(pstmt!=null) {
			try {pstmt.close();} catch(SQLException ignored){}
		}			
	}	
	
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
			}
		}
	}
}
