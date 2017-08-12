package com.faceontalk.auth.action;

import java.sql.Connection;
import java.sql.SQLException;

import com.faceontalk.jdbc.JdbcUtil;
import com.faceontalk.jdbc.connection.ConnectionProvider;
import com.faceontalk.member.action.User;
import com.faceontalk.member.dao.MemberDAO;
import com.faceontalk.member.dto.MemberVO;

public class LoginAction {
	private MemberDAO memberDao = MemberDAO.getInstance();
	
	public User login(String email,String password) throws LoginFailedException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();		
			MemberVO member = memberDao.selectByEmail(conn,email);
			
			if(member==null || !member.getPassword().equals(password)) 
				throw new LoginFailedException();
			return new User(member.getUser_id(),member.getUser_name(),member.getEmail());			
		} catch(SQLException e) {			
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}	
	}

}
