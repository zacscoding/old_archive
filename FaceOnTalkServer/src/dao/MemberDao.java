package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import dto.MemberVO;
import jdbc.DBManager;

public class MemberDao {
	//singleton
	private MemberDao(){}
	private static MemberDao inst = null;
	public static MemberDao getInstance(){
		if(inst == null)
			inst = new MemberDao();
		return inst;
	}
	
	//variables
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	//아이디 존재 유무, 존재하면 1 아니면 -1반환
	public int confirmId(String userEmail) throws SQLException {
		int result = -1;	    
	    Connection connn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;	    
	    try {
	      connn = DBManager.getConnection();
	      pstmt = connn.prepareStatement(
	    		  "select * from member where email=?");
	      pstmt.setString(1, userEmail);
	      rs = pstmt.executeQuery();
	      if (rs.next()) { 
	        result = 1;
	      } else { 
	        result = -1;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      DBManager.close(conn);
	      DBManager.close(pstmt);
	      DBManager.close(rs);
	    }
	    return result;			
	}
	
	//id존재하는지 확인하는 메소드 -> 존재 1,존재X -1반환
	public int confirmId(int id) throws SQLException {
		int result = -1;	    
	    Connection connn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;	    
	    try {
	      connn = DBManager.getConnection();
	      pstmt = connn.prepareStatement(
	    		  "select * from member where user_id=?");
	      pstmt.setInt(1,id);
	      rs = pstmt.executeQuery();
	      if (rs.next()) { 
	        result = 1;
	      } else { 
	        result = -1;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
	      DBManager.close(conn);
	      DBManager.close(pstmt);
	      DBManager.close(rs);
	    }
	    return result;			
	}	
	//이메일로로 멤버 인스턴스 얻음
	public MemberVO selectById(String userEmail) throws SQLException {				
		try{
			conn = DBManager.getConnection();			
			pstmt = conn.prepareStatement("select * from member where email = ?");
			pstmt.setString(1, userEmail);
			rs = pstmt.executeQuery();
			MemberVO member = null;
			if(rs.next())
				member = convertMember(rs);			
			return member;			
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}	
	//id로 멤버 인스턴스 얻어옴
	public MemberVO selectById(int id) throws SQLException {				
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from member where user_id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			MemberVO member = null;
			if(rs.next())
				member = convertMember(rs);			
			return member;			
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	//동일한 이름의 회원 숫자 반환
	public int selectCountByName(String name) throws SQLException {
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select count(*) from member where user_name = ?");
			pstmt.setString(1,name);
			int count = 0;
			rs = pstmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
			return count;			
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	//이름으로 검색해서 List에 담음
	public List<MemberVO> selectByName(String name,int from) throws SQLException {
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from member where user_name like '%"+name+"%' and user_id!=?");
			pstmt.setInt(1, from);
			rs = pstmt.executeQuery();
			List<MemberVO> memberList = new Vector<>();
			while(rs.next()){
				memberList.add(convertMember(rs));
			}
			return memberList;						
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	//폰으로 검색한 멤버들을 vector에 담음
	public List<MemberVO> selectByPhone(String phone,int from) throws SQLException {
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from member where phone = ? and user_id != "+from);
			pstmt.setString(1,phone);
			//pstmt.setInt(2, from);
			rs = pstmt.executeQuery();
			List<MemberVO> memberList = new Vector<>();
			while(rs.next()){
				memberList.add(convertMember(rs));
			}			
			return memberList;						
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	//생일,폰,성별로 아이디 찾을때 사용
	public MemberVO findId(String birth,String phone,String gender) throws SQLException {
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from member where phone = ? and birth = ? and gender = ?");			
			pstmt.setString(1,phone);
			pstmt.setString(2, birth);
			pstmt.setString(3, gender);			
			rs = pstmt.executeQuery();
			MemberVO member = null;
			while(rs.next()){
				member = convertMember(rs);
			}			
			return member;		
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	//비밀번호 찾을때 사용
	public MemberVO findPassword(String email,String phone) throws SQLException {
		try{
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("select * from member where phone = ? and email = ?");			
			pstmt.setString(1,phone);
			pstmt.setString(2, email);						
			rs = pstmt.executeQuery();
			MemberVO member = null;
			while(rs.next()){
				member = convertMember(rs);
			}			
			return member;		
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	
	/////////////////////
	//멤버 삽입(회원가입)
	/////////////////////
	public void insert(MemberVO mem) throws SQLException {
		try{
			conn = DBManager.getConnection();			
			pstmt = conn.prepareStatement("insert into member (user_id, user_name, email , password ,phone,birth,gender,friend_count, regdate) "
					+ "values(member_seq.nextval,?,?,?,?,?,?,?,?)");
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getEmail());
			pstmt.setString(3, mem.getPassword());			
			pstmt.setString(4, mem.getPhone());
			pstmt.setString(5, mem.getBirth());
			pstmt.setString(6, mem.getGender());
			pstmt.setInt(7, mem.getFriendCount());
			pstmt.setTimestamp(8, new Timestamp(mem.getRegDate().getTime()));			
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
	
	
	/////////////////////
	//멤버 정보 수정
	/////////////////////
	public void update(MemberVO mem) throws SQLException {		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("update member set user_name=? , password=? , phone = ? where user_id=?");
			pstmt.setString(1, mem.getName());
			pstmt.setString(2, mem.getPassword());
			pstmt.setString(3, mem.getPhone());
			pstmt.setInt(4, mem.getId());
			pstmt.executeUpdate();			
		}catch(SQLException e){			
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}		
	}
	
	/////////////////////
	//친구 정보 얻기
	/////////////////////
	public List<MemberVO> getFriendsList(int id,int friendCount) throws SQLException {
		String query = "select * from member where user_id in("
				+"(select user_one_id from relationship where user_two_id=? and status='1') union all"
				+"(select user_two_id from relationship where user_one_id=? and status='1') )";				
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			List<MemberVO> friendsList = new ArrayList<>(friendCount);
			rs = pstmt.executeQuery();
			while(rs.next()){
				friendsList.add(convertFriend(rs));
			}
			return friendsList;			
		}catch(SQLException e){			
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}	
	}
	/////////////////////
	//친구 요청 대기 리스트 얻기
	/////////////////////
	public List<MemberVO> getPendingList(int id) throws SQLException {
		String query = "select * from member where user_id in"				
				+"(select user_one_id from relationship where user_two_id=? and status='0')";
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);			
			List<MemberVO> pendingList = new ArrayList<>();
			rs = pstmt.executeQuery();
			while(rs.next()){
				pendingList.add(convertFriend(rs));
			}
			return pendingList;			
		}catch(SQLException e){			
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	/////////////////////
	//친구 수 하나 증가 하기
	/////////////////////
	public void increaseFriendCount(int id) throws SQLException {		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("update member set friend_count = friend_count + 1 where user_id = ?");
			pstmt.setInt(1, id);			
			pstmt.executeUpdate();						
		}catch(SQLException e){			
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	/////////////////////
	//친구 수 하나 감소 하기
	/////////////////////
	public void decreaseFriendCount(int id) throws SQLException {		
		try{			
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement("update member set friend_count = friend_count - 1 where user_id = ?");
			pstmt.setInt(1, id);			
			pstmt.executeUpdate();						
		}catch(SQLException e){			
			e.printStackTrace();
			throw new SQLException(e);
		}
		finally{
			DBManager.close(conn);
			DBManager.close(pstmt);
			DBManager.close(rs);
		}
	}
	
	
	
	//////////////////////
	///private methods
	
	//ResultSet을 MemberVO로 바꿔주는 메소드(친구용 -> 비밀번호를 담지 않음)
	private MemberVO convertFriend(ResultSet rs) throws SQLException {
		return new MemberVO(						
				rs.getInt("user_id")
				,rs.getString("email")
				,rs.getString("user_name")
				,rs.getString("phone")
				,rs.getString("birth")
				,rs.getString("gender")				
				);
	}
	//ResultSet을 MemberVO로 바꿔주는 메소드(admin용 -> 비밀번호 등 모든 정보를 담음)
	private MemberVO convertMember(ResultSet rs) throws SQLException {
		return new MemberVO(						
				rs.getInt("user_id")
				,rs.getString("email")
				,rs.getString("user_name")						
				,rs.getString("password")
				,rs.getString("phone")
				,rs.getString("birth")
				,rs.getString("gender")
				,rs.getInt("friend_count")
				,toDate(rs.getTimestamp("regdate"))
				);
	}	
	//Timestamp -> Date 인스턴스로 변경
	private Date toDate(Timestamp date) {
		return date == null ? null : new Date(date.getTime());
	}
}
