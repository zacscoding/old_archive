package com.faceontalk.feed.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.faceontalk.feed.dto.Criteria;
import com.faceontalk.feed.dto.FeedVO;
import com.faceontalk.feed.dto.WriterVO;
import com.faceontalk.jdbc.JdbcUtil;

public class FeedDAO {
	// singleton
	private static FeedDAO inst = new FeedDAO();
	private FeedDAO() {}
	public static FeedDAO getInstance() {
		return inst;
	}
	
	
	///////////////////
	// 전체 게시글
	///////////////////
	public List<FeedVO> selectFeedFromAll(Connection conn, Criteria cri) throws SQLException {
		String query = "select * from (" + "select feed.*,row_number() over (order by feed_no desc)R from feed)"
				+ "where R between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeedVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, cri.getPageStart());
			pstmt.setInt(2, cri.getPageStart() + cri.getPerPageNum() - 1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(convertFeed(rs));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}

	///////////////////
	// 본인 게시글
	///////////////////
	public List<FeedVO> selectFeedFromUser(Connection conn, Criteria cri, int id) throws SQLException {
		String query = "select * from ("
				+ "select feed.*,row_number() over (order by feed_no desc)R from feed where writer_id = ?)"
				+ "where R between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<FeedVO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, cri.getPageStart());
			pstmt.setInt(3, cri.getPageStart() + cri.getPerPageNum() - 1);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(convertFeed(rs));
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return list;
	}
	///////////////////
	// 전체 피드 개수
	///////////////////
	public int selectAllCount(Connection conn) throws SQLException {
		String query = "select count(feed_no) from feed";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return total;
	}
	///////////////////
	// 자신 피드 개수
	///////////////////
	public int selectUserCount(Connection conn, int id) throws SQLException {
		String query = "select count(*) from feed where writer_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return total;
	}
	///////////////////
	// 피드 가져 오기
	///////////////////
	public FeedVO getFeed(Connection conn,int feed_no) throws SQLException {
		String query = "select * from feed where feed_no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		FeedVO feed = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, feed_no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				feed = convertFeed(rs);
			}
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
		return feed;
	}
	
	///////////////////
	// 피드 저장하기
	///////////////////
	public int create(Connection conn,FeedVO feed) throws SQLException {
		String query = "insert into feed (feed_no, writer_id, writer_name, writer_email, content, regdate, moddate, like_count)"
						+ " values(feed_seq.nextval,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,feed.getWriter().getId());
			pstmt.setString(2,feed.getWriter().getName());
			pstmt.setString(3,feed.getWriter().getEmail());
			pstmt.setString(4,feed.getContent());
			pstmt.setTimestamp(5,toTimestamp(feed.getRegDate()));
			pstmt.setTimestamp(6,toTimestamp(feed.getModifiedDate()));
			pstmt.setInt(7,0);				
			return pstmt.executeUpdate();			
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}		
	}
	
	///////////////////
	// 피드 수정 하기
	///////////////////
	public int update(Connection conn,FeedVO feed) throws SQLException {
		String query = "update feed set content = ? , moddate = ? where feed_no = ? and writer_id = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;	
		try{							
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,feed.getContent());
			pstmt.setTimestamp(2,toTimestamp(feed.getModifiedDate()));
			pstmt.setInt(3, feed.getFeedNumber());
			pstmt.setInt(4, feed.getWriter().getId());
			return pstmt.executeUpdate();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}	
		
	}
	
	
	
	
	// ResultSet -> Feed 로 바꿔주는 메소드
	private FeedVO convertFeed(ResultSet rs) throws SQLException {
		return new FeedVO(rs.getInt("feed_no"),
				new WriterVO(rs.getInt("writer_id"), rs.getString("writer_name"), rs.getString("writer_email")),
				rs.getString("content"), toDate(rs.getTimestamp("regdate")), toDate(rs.getTimestamp("moddate")),
				rs.getInt("like_count"));
	}

	// Timestamp -> Date 로 바꿔주는 메소드
	private Date toDate(Timestamp timestamp) {
		return new Date(timestamp.getTime());
	}

	// Date -> Timestamp 로 바꿔주는 메소드
	private Timestamp toTimestamp(Date date) {
		return new Timestamp(date.getTime());
	}
}
