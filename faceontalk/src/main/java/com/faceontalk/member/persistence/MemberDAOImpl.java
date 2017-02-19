package com.faceontalk.member.persistence;

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
	public MemberVO searchByName(String user_name) throws Exception {
		return session.selectOne(namespace+".searchByName",user_name);
	}

	@Override
	public void update(MemberVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public MemberVO searchById(Integer user_id) throws Exception {
		return session.selectOne(namespace+".searchById",user_id);
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
