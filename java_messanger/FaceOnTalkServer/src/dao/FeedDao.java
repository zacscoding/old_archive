package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.FeedVO;
import dto.WriterVO;
import jdbc.DBManager;

public class FeedDao {
		//singleton
		private FeedDao(){}
		private static FeedDao inst = null;
		public static FeedDao getInstance() {
			if(inst == null)
				inst = new FeedDao();
			return inst;
		}		
		//variables
		private Connection conn;
		private PreparedStatement pstmt;
		private ResultSet rs;
		
		//feed_no로 게시글 얻기
		public FeedVO selectByFeedNumber(int feed_no) throws SQLException {
			try{
				conn = DBManager.getConnection();
				pstmt = conn.prepareStatement("select * from feed where feed_no = ?");
				pstmt.setInt(1,feed_no);				
				rs = pstmt.executeQuery();
				FeedVO feed = null;
				if(rs.next()){
					feed = convertFeed(rs);
				}
				return feed;				
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("selectById예외");
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}			
		}	
		
		///////////////////////////////////
		//1.게시글 쓰기
		///////////////////////////////////
		public int insert(FeedVO feed) throws SQLException {
			try{
				conn = DBManager.getConnection();
				pstmt = 
					conn.prepareStatement("insert into feed (feed_no, writer_id, writer_name, writer_email, content, regdate, moddate, like_count)"
						+ " values(feed_seq.nextval,?,?,?,?,?,?,?)");
				pstmt.setInt(1,feed.getWriter().getId());
				pstmt.setString(2,feed.getWriter().getName());
				pstmt.setString(3,feed.getWriter().getEmail());
				pstmt.setString(4,feed.getContent());
				pstmt.setTimestamp(5,toTimestamp(feed.getRegDate()));
				pstmt.setTimestamp(6,toTimestamp(feed.getModifiedDate()));
				pstmt.setInt(7,0);				
				return pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}		
		
		///////////////////////////////////
		//2.게시글 수정
		///////////////////////////////////
		public int update(FeedVO feed) throws SQLException {
			try{
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement("update feed set content = ? , moddate = ? where feed_no = ? and writer_id = ?");
				pstmt.setString(1,feed.getContent());
				pstmt.setTimestamp(2,toTimestamp(feed.getModifiedDate()));
				pstmt.setInt(3, feed.getFeedNumber());
				pstmt.setInt(4, feed.getWriter().getId());
				return pstmt.executeUpdate();
			}catch(SQLException e){	
				e.printStackTrace();
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}
		
		///////////////////////////////////
		//3.게시글 삭제
		///////////////////////////////////
		public int delete(int feedNumber) throws SQLException {
			try{
				conn = DBManager.getConnection();				
				pstmt = conn.prepareStatement("delete from feed where feed_no = ? ");
				pstmt.setInt(1,feedNumber);				
				return pstmt.executeUpdate();
			}catch(SQLException e){
				e.printStackTrace();				
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}
		///////////////////////////////////
		//4.게시글 보기
		///////////////////////////////////		
		//자신 + 친구 게시글 총 개수
		public int selectAllCount(int id) throws SQLException {
			//자기 아이디 + 나한테 친구추가 신청한 사람 + 내가 친구추가 신청한 사람 쿼리
			String query = "select count(*) from feed where writer_id = ? or writer_id in ( "
					  + " select user_one_id from relationship where user_two_id = ? and  status='1' union all "
					  + " select user_two_id from relationship where user_one_id = ? and status = '1' ) ";
			try{
				conn = DBManager.getConnection();								
				pstmt = conn.prepareStatement(query);				
				pstmt.setInt(1, id);
				pstmt.setInt(2, id);
				pstmt.setInt(3, id);				
				rs = pstmt.executeQuery();
				int count = 0;				
				if(rs.next())
					count = rs.getInt(1);				
				return count;
			}catch(SQLException e){
				e.printStackTrace();
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}	
		
		//자신의 글 개수
		public int selectCount(int id) throws SQLException {
			String query = "select count(*) from feed where writer_id = ?";
			try{
				conn = DBManager.getConnection();								
				pstmt = conn.prepareStatement(query);				
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();
				int count = 0;
				if(rs.next())
					count = rs.getInt(1);				
				return count;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("delete예외");
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}
		
		////////////////
		//자신이 등록한 피드만 가져오는 쿼리
		////////////////		
		public List<FeedVO> selectOnlyUser(int id,int startRow,int size) throws SQLException {
			String query = "select * from ("
					+ "select feed.*,row_number() over (order by feed_no desc)R from feed where writer_id = ?)"
					+ "where R between ? and ?";		
			try {
				conn = DBManager.getConnection();								
				pstmt = conn.prepareStatement(query);				
				pstmt.setInt(1, id);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, size+startRow-1);			
				rs = pstmt.executeQuery();
				List<FeedVO> list = new ArrayList<>(size);				
				while(rs.next()){
					list.add(convertFeed(rs));
				}
				return list;
			}catch(SQLException e){
				e.printStackTrace();				
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}
		////////////////
		//최신 게시글을 부터 내림차순 정렬된 feed를 startRow 부터 size개 list에 담음
		////////////////
		public List<FeedVO> select(int id,int startRow, int size) throws SQLException {
			String query = "select * from ( "
					  +"select feed.*,row_number() over (order by feed_no desc)R from feed where writer_id = ? or writer_id in( "
					  +"select user_one_id from relationship where user_two_id=? and status='1' union all "
					  +"select user_two_id from relationship where user_one_id=? and status='1' ))where R between ? and ?";					  
			try {
				conn = DBManager.getConnection();								
				pstmt = conn.prepareStatement(query);				
				pstmt.setInt(1, id);
				pstmt.setInt(2, id);
				pstmt.setInt(3, id);				
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, size+startRow-1);
				rs = pstmt.executeQuery();
				List<FeedVO> list = new ArrayList<>(size);				
				while(rs.next()){
					list.add(convertFeed(rs));
				}
				return list;
			}catch(SQLException e){
				e.printStackTrace();				
				throw new SQLException(e);
			}finally{
				DBManager.close(conn);
				DBManager.close(pstmt);				
			}
		}
		
		
		/////////////////////
		///private methods	
		
		//ResultSet -> Feed 로 바꿔주는 메소드
		private FeedVO convertFeed(ResultSet rs) throws SQLException {			
			return new FeedVO(rs.getInt("feed_no")
					,new WriterVO(rs.getInt("writer_id"),rs.getString("writer_name"),rs.getString("writer_email"))
					,rs.getString("content")
					,toDate(rs.getTimestamp("regdate"))
					,toDate(rs.getTimestamp("moddate"))
					,rs.getInt("like_count"));
		}	
		//Timestamp -> Date 로 바꿔주는 메소드
		private Date toDate(Timestamp timestamp) {
			return new Date(timestamp.getTime());
		}		
		//Date -> Timestamp 로 바꿔주는 메소드
		private Timestamp toTimestamp(Date date) {
			return new Timestamp(date.getTime());
		}
		
}
