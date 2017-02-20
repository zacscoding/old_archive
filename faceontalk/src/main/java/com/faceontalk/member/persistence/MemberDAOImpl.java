package com.faceontalk.member.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.faceontalk.member.domain.FollowVO;
import com.faceontalk.member.domain.MemberVO;

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
	public MemberVO searchByNum(Integer user_no) throws Exception {
		return session.selectOne(namespace+".searchByNum",user_no);
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


}
