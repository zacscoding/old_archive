package com.faceontalk.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.faceontalk.jdbc.JdbcUtil;
import com.faceontalk.member.dto.MemberVO;

public class MemberDAO {
	//singleton
	private static MemberDAO inst = new MemberDAO();
	private MemberDAO(){}
	public static MemberDAO getInstance() {
		return inst;
	}
	
	
	/////////////////////////////
	// Email 존재 유무 -> 존재 1 , 존재X -1반환
	/////////////////////////////
	public int confirmId(Connection conn,String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			pstmt = conn.prepareStatement(
					"select user_id from member where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				result = 1;
			}			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return result;
	}
	
	
	/////////////////////
	//멤버 삽입(회원가입)
	/////////////////////
	public void insert(Connection conn,MemberVO mem) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{						
			pstmt = conn.prepareStatement("insert into member (user_id, user_name, email , password ,phone,birth,gender,friend_count, regdate) "
					+ "values(member_seq.nextval,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, mem.getUser_name());
			pstmt.setString(2, mem.getEmail());
			pstmt.setString(3, mem.getPassword());			
			pstmt.setString(4, mem.getPhone());
			pstmt.setString(5, mem.getBirth());
			pstmt.setString(6, mem.getGender());
			pstmt.setInt(7, mem.getFriend_count());
			pstmt.setTimestamp(8, new Timestamp(mem.getRegdate().getTime()));			
			pstmt.executeUpdate();			
		}catch(SQLException e){
			throw new SQLException(e);
		}
		finally{			
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	/////////////////////////////
	// email로 Member 꺼내기
	/////////////////////////////
	public MemberVO selectByEmail(Connection conn,String email) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from member where email = ?");
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();			
			if (rs.next()) {
				member = convertMember(rs);
			}			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return member;
	}
	
	
	//////////////////
	//private methods
	
	//ResultSet을 MemberVO로 바꿔주는 메소드(admin용 -> 비밀번호 등 모든 정보를 담음)
		private MemberVO convertMember(ResultSet rs) throws SQLException {
			return new MemberVO(						
					rs.getInt("user_id"),
					rs.getString("user_name"),
					rs.getString("password"),
					rs.getString("email"),
					toDate(rs.getTimestamp("regdate")),					
					rs.getString("phone"),					
					rs.getString("gender"),
					rs.getString("birth"),
					rs.getInt("friend_count")					
					);
		}
		
		//Timestamp -> Date 인스턴스로 변경
		private Date toDate(Timestamp date) {
			return date == null ? null : new Date(date.getTime());
		}
	

	
	/////////////////////////////
	//
	/////////////////////////////
}
