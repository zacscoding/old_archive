package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/*
 * DB관련 매니저
 * connQueue는 커넥션풀을 구현하지 못해 임시로 넣어놓는 코드
 * 기본적으로 커낵션 풀 + static getConnection()메소드를 정의해서 커넥션을 얻음
 * 수정 필요
 */
public class DBManager {
	private static String url = "jdbc:oracle:thin:@localhost:1521:XE";
	private static String uid = "web02";
	private static String pwd = "web02";
	private static Queue<Connection> connQueue = new LinkedList<>();
	
	static {		
		pushConnection();		
	}
	
	private static void pushConnection() {
		Thread thread = new Thread() {
			@Override
			public void run() {				
				for(int i=0;i<10;i++) {
					Connection conn = null;
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						conn = DriverManager.getConnection(url, uid, pwd);
					} catch (Exception e) {
						e.printStackTrace();
					}					
					connQueue.offer(conn);
				}	
			}
		};
		thread.start();			
	}
	
	// 카넥션 얻어오기
	public synchronized static Connection getConnection() {
		if(connQueue.isEmpty()) {
			Connection conn = null;
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, uid, pwd);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pushConnection();
			}
			return conn;
		} else {
			Connection conn = connQueue.poll();
			if(connQueue.size()<3){
				Thread thread = new Thread() {
					@Override
					public void run() {
						pushConnection();
					}
				};
				thread.start();
			}			
			return conn;
		}		
	}
	
	//커넥션 닫기	
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		}
	}	
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
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
	public static void rollback(Connection conn) {
		if (conn != null) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
			}
		}
	}
}
