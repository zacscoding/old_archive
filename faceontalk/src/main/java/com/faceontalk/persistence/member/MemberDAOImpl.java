package com.faceontalk.persistence.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	@Inject
	private SqlSession session;
	private static String namespace="com.faceontalk.mapper.MemberMapper";
	
	@Override
	public void regist(MemberVO vo) throws Exception {
		session.insert(namespace+".regist",vo);
	}
	
	@Override
	public MemberVO searchById(String user_id) throws Exception {
		return session.selectOne(namespace+".searchById",user_id);
	}

	@Override
	public void update(MemberVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}
	
	@Override
	public void changePassoword(Integer user_no,String password) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_no",user_no);
		paramMap.put("password",password);
		
		session.update(namespace+".changePassoword",paramMap);
	}
	
	@Override
	public void editProfile(Integer user_no, String profile_pic) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_no",user_no);
		paramMap.put("profile_pic",profile_pic);		
		
		session.update(namespace+".editProfile",paramMap);		
	}

	@Override
	public MemberVO searchByNum(Integer user_no) throws Exception {
		return session.selectOne(namespace+".searchByNum",user_no);
	}
	

	@Override
	public List<MemberVO> searchListById(String user_id) throws Exception {
		return session.selectList(namespace+".searchListById",user_id);
	}
	
	
	/**	login	*/
	@Override
	public void keepLogin(Integer user_no, String sessionId, Date next) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_no",user_no);
		paramMap.put("sessionId",sessionId);
		paramMap.put("next",next);
		
		session.insert(namespace+".keepLogin",paramMap);		
	}
	
	@Override
	public MemberVO checkUserWithSessionKey(String value) {
		return session.selectOne(namespace+".checkUserWithSessionKey",value);
	}
	
	/** follower	*/
	@Override
	public void registFollower(FollowVO vo) throws Exception {
		session.insert(namespace+".registFollower",vo);
	}
	@Override
	public void removeFollower(FollowVO vo) throws Exception {
		session.insert(namespace+".removeFollower",vo);
	}
	@Override
	public Boolean isFollow(FollowVO vo) throws Exception {
		int result = session.selectOne(namespace+".isFollow",vo);
		return (result>0) ? Boolean.TRUE : Boolean.FALSE;
	}
	
	//users follower count
	@Override
	public int getFollowerCount(Integer user_no) throws Exception {
		return session.selectOne(namespace+".getFollowerCount",user_no);
	}

	@Override
	public int getFollowingCount(Integer user_no) throws Exception {
		return session.selectOne(namespace+".getFollowingCount",user_no);
	}
	
	/**		Email Auth register*/
	@Override
	public void registerAuthToken(String user_id, String auth_token, Date auth_limit) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_id",user_id);
		paramMap.put("auth_token",auth_token);
		paramMap.put("auth_limit",auth_limit);		
		session.insert(namespace+".registerAuthToken",paramMap);
	}
	
	@Override
	public EmailAuthVO getEmailAuth(EmailAuthVO dto) throws Exception {		
		return session.selectOne(namespace+".getEmailAuth",dto);
	}

	@Override
	public void activate(String user_id) throws Exception {
		session.update(namespace+".activate",user_id);
	}

	/**		Remove Expired	Auth 	*/	
	@Override
	public void removeExpiredAuthEmail() throws Exception {
		session.delete(namespace+".removeExpiredAuthEmail");		
	}

	@Override
	public void removeExpiredAuthMember() throws Exception {
		session.delete(namespace+".removeExpiredAuthMember");		
	}


	
}
