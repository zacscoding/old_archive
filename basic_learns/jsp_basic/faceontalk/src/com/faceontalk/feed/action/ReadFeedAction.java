package com.faceontalk.feed.action;

import java.sql.Connection;
import java.sql.SQLException;

import com.faceontalk.feed.dao.FeedDAO;
import com.faceontalk.feed.dto.FeedVO;
import com.faceontalk.jdbc.JdbcUtil;
import com.faceontalk.jdbc.connection.ConnectionProvider;

public class ReadFeedAction {
	private FeedDAO feedDao = FeedDAO.getInstance();
	
	public FeedVO getFeed(int feed_no) {
		Connection conn = null;
		FeedVO feed = null;
		try {
			conn = ConnectionProvider.getConnection();
			feed = feedDao.getFeed(conn, feed_no); 
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return feed; 
	}
	
	
}
