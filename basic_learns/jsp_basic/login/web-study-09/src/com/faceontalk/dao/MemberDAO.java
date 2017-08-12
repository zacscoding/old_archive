package com.faceontalk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.faceontalk.dto.MemberVO;

public class MemberDAO {
	//singleton
	private MemberDAO(){}
	private static MemberDAO inst = null;
	public static MemberDAO getInstance() {
		if(inst==null)
			inst = new MemberDAO();
		return inst;
	}	
	//get connection from dbcp
	public Connection getConnection() throws Exception {
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext  = (Context)initContext.lookup("java:/comp/env"); //lookup메소드로 DBCP에서 지정한 이름 찾기
		DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle"); //jdbc/myoracle -> server.xml 파일의 <Resource>태그의 name속성
		conn = ds.getConnection();
		return conn;
	}
	
	////////////
	//사용자 인증 시 사용(일치 : 1 // 비번 불일치 0 // 아이디 존재 X : -1
	////////////	
	public int userCheck(String userId,String pwd) {
		int result = -1;
		String sql = "select pwd from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd))
					result = 1;
				else 
					result = 0;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e){}
		}		
		return result;		
	}
	
	////////////
	//해당 회원을 찾아 MemberVO에 담아서 반환
	////////////
	public MemberVO getMember(String userId) {		
		String sql = "select * from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				member = convertMember(rs);				
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e){}
		}		
		return member;	
	}
	

	
	////////////
	//아이디 중복 확인(존재하면 1 ,아니면 -1 반환)
	////////////
	public int confirmId(String userId) {		
		int result = 0;
		String sql = "select userid from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if(rs.next()) {				
				result = 1;
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e){}
		}		
		return result;	
	}
	
	////////////
	//삽입
	////////////
	public int insertMember(MemberVO member) {
		String sql = "insert into member (name,userid,pwd,email,phone,admin) values(?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getName());
			pstmt.setString(2,member.getUserId());
			pstmt.setString(3,member.getPwd());
			pstmt.setString(4,member.getEmail());
			pstmt.setString(5,member.getPhone());
			pstmt.setInt(6,member.getAdmin());
			result = pstmt.executeUpdate();						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{				
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e){}
		}
		return result;
	}
	
	////////////
	//정보변경
	////////////
	public int updateMember(MemberVO member) {
		int result = -1;
		String sql = "update member set pwd=?,email=?,phone=?,admin=? where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,member.getPwd());
			pstmt.setString(2,member.getEmail());
			pstmt.setString(3,member.getPhone());
			pstmt.setInt(4,member.getAdmin());
			pstmt.setString(5,member.getUserId());			
			result = pstmt.executeUpdate();						
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try{				
				if(pstmt!=null) pstmt.close();
				if(conn!=null) conn.close();
			} catch(SQLException e){}
		}
		return result;
	}
	
	private MemberVO convertMember(ResultSet rs) throws SQLException {
		MemberVO inst = new MemberVO();
		inst.setName(rs.getString("name"));		
		inst.setUserId(rs.getString("userid"));
		inst.setPwd(rs.getString("pwd"));
		inst.setEmail(rs.getString("email"));
		inst.setPhone(rs.getString("phone"));
		inst.setAdmin(rs.getInt("admin"));		
		return inst;
	}	
}


