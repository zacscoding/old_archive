package com.faceontalk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.faceontalk.dto.BoardVO;

import util.DBManager;

public class BoardDAO {
	//singleton
	private static BoardDAO inst = null;
	private BoardDAO(){}
	public static BoardDAO getInstance() {
		if(inst==null)
			inst = new BoardDAO();
		return inst;
	}
	
	
	
	////////////////////////
	//methods 
	
		
	////////////////////////
	// 모든 게시글 가져오기
	///////////////////////
	public List<BoardVO> selectAllBoards() {
		String sql = "select * from board order by num desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(convertBoard(rs));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt,rs);
		}
		return list;
	}
	
	////////////////////////
	// 게시글 삽입
	///////////////////////	
	public void insertBoard(BoardVO board) {
		String sql = "insert into board (num,name,email,pass,title,content) values(board_seq.nextval,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,board.getName());
			pstmt.setString(2,board.getEmail());
			pstmt.setString(3,board.getPass());
			pstmt.setString(4,board.getTitle());
			pstmt.setString(5,board.getContent());
			pstmt.executeUpdate();			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	////////////////////////
	// 게시글 조회수 증가
	///////////////////////
	public void updateReadCount(String num) {
		String sql = "update board set readcount = readcount +1 where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,num);			
			pstmt.executeUpdate();			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	////////////////////////
	// 게시글 상세 보기
	///////////////////////
	public BoardVO selectOneBoardByNum(String num) {
		String sql = "select * from board where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = convertBoard(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt,rs);
		}
		return board;
	}

	
	////////////////////////
	// 게시글 수정
	///////////////////////
	public void updateBoard(BoardVO board) {
		String sql = "update board set name=?,email=?,pass=?,title=?,content=? where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getEmail());
			pstmt.setString(3, board.getPass());
			pstmt.setString(4, board.getTitle());
			pstmt.setString(5, board.getContent());
			pstmt.setInt(6, board.getNum());			
			pstmt.executeUpdate();			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	////////////////////////
	// 게시글 비밀번호 체크 
	///////////////////////
	public BoardVO checkPassword(String pass,String num) {
		String sql = "select password from board where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,pass);
			pstmt.setString(2,num);						
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board = convertBoard(rs);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return board;
	}
	
		
	////////////////////////
	// 게시글 삭제
	///////////////////////
	public void deleteBoard(String num) {
		String sql = "delete board where num = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,num);						
			pstmt.executeUpdate();			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	
	
	////////////////////
	//private methods	
	private BoardVO convertBoard(ResultSet rs) throws SQLException {
		return new BoardVO(
			rs.getInt("num"),
			rs.getString("name"),
			rs.getString("email"),
			rs.getString("pass"),
			rs.getString("title"),
			rs.getString("content"),
			rs.getInt("readcount"),
			rs.getTimestamp("writedate")
			);
	}
}
