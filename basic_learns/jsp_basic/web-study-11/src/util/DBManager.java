package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class DBManager {
	public static Connection getConnection() {
		Connection conn = null;		 
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env"); //lookup메소드로 DBCP에서 지정한 이름 찾기
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle"); //jdbc/myoracle -> server.xml 파일의 <Resource>태그의 name속성
			conn = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}				
		return conn;		
	}
	
	public static void close(Connection conn,PreparedStatement pstmt,java.sql.ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null)	conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void close(Connection conn,PreparedStatement pstmt) {		
		try {		
			if(pstmt!=null) pstmt.close();
			if(conn!=null)	conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
