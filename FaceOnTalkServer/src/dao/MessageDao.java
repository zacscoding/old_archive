package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dto.MessageVO;
import jdbc.DBManager;

/*
 * 미구현 클래스
 * 디비에 저장할 시 사용
 */
public class MessageDao {
	//singleton
	private MessageDao(){}
	private static MessageDao inst = null;
	public static MessageDao getInstance() {
		if (inst == null)
			inst = new MessageDao();
		return inst;
	}
	// variables
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	////////////////////////
	//메시지 저장
	///////////////////////
	public void insert(MessageVO message) throws SQLException {
		try{
			conn = DBManager.getConnection();			
			pstmt = conn.prepareStatement("insert into message (message_id, group_id,content,sender,send_date)"
					+ "values(message_seq.nextval,?,?,?,?)");
			pstmt.setString(1, message.getGroup_id());
			pstmt.setString(2, message.getContent());
			pstmt.setInt(3, message.getSender());
			pstmt.setTimestamp(4, new Timestamp(message.getSendDate().getTime()));						
			pstmt.executeUpdate();			
		}catch(SQLException e){
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
		
		
		
	}
	
	
			
}
