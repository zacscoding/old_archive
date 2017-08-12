package com.faceontalk.feed.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.faceontalk.feed.dao.FeedDAO;
import com.faceontalk.feed.dto.Criteria;
import com.faceontalk.feed.dto.FeedVO;
import com.faceontalk.jdbc.JdbcUtil;
import com.faceontalk.jdbc.connection.ConnectionProvider;

public class FeedListAction {
	private FeedDAO feedDao = FeedDAO.getInstance();
	private static final int viewSize = 10;	
	
	public List<FeedVO> listCriteria(Criteria cri,String readType,int id) {
		Connection conn = null;
		List<FeedVO> list = null;
		try {
			conn = ConnectionProvider.getConnection();
			if(readType.equals("all")) {
				list = feedDao.selectFeedFromAll(conn, cri);				
			} else if(readType.equals("admin")) {
				list = feedDao.selectFeedFromUser(conn, cri, id);
			}
		} catch(SQLException e) {			
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	public int listCount(String readType,int id) {
		Connection conn = null;
		int count = 0;
		try {
			conn = ConnectionProvider.getConnection();
			if(readType.equals("all")) {
				count = feedDao.selectAllCount(conn);				
			} else if(readType.equals("admin")) {
				count = feedDao.selectUserCount(conn, id);
			}
		} catch(SQLException e) {			
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		return count;
	}
	
	
//	//FeedPage를 이용할 경우
//	public FeedPage getFeedPage(int id,int pageNo,String readType) {
//		Connection conn = null;
//		try {
//			int total = 0;
//			List<FeedVO> feedList = null;		
//			conn = ConnectionProvider.getConnection();
//			if(readType.equals("all")) {
//				total = feedDao.selectAllCount(conn, id);
//				feedList = feedDao.selectFeedsFromAll(conn,id,(pageNo-1)*viewSize,viewSize);				
//			} else if(readType.equals("admin")) {
//				total = feedDao.selectUserCount(conn, id);
//				feedList = feedDao.selectFeedsFromUser(conn, id,(pageNo-1)*viewSize,viewSize);
//			}					
//			return new FeedPage(total,pageNo,viewSize,feedList);
//		} catch(SQLException e) {			
//			throw new RuntimeException(e);
//		} finally {
//			JdbcUtil.close(conn);
//		}		
//	}
	
}
