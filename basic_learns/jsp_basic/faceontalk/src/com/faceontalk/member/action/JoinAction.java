package com.faceontalk.member.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.faceontalk.jdbc.JdbcUtil;
import com.faceontalk.jdbc.connection.ConnectionProvider;
import com.faceontalk.member.dao.MemberDAO;
import com.faceontalk.member.dto.MemberVO;

public class JoinAction {
	private MemberDAO memberDao = MemberDAO.getInstance();	
	public void join(HttpServletRequest req) throws DuplicateIdException {		
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int result = memberDao.confirmId(conn, req.getParameter("email"));
			if(result<0) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}			
			MemberVO member = convertMember(req);
			memberDao.insert(conn,member);
			conn.commit();			
		} catch(SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}		
	}
	private static MemberVO convertMember(HttpServletRequest req) {
		return new MemberVO(
				0,
				req.getParameter("name"),
				req.getParameter("password"),
				req.getParameter("email"),
				new Date(),
				req.getParameter("phone"),
				req.getParameter("gender"),
				req.getParameter("birth"),
				0);
	}
}
