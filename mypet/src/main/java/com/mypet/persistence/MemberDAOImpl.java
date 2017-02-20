package com.mypet.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.MemberVO;
import com.mypet.security.UserRole;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace = "com.mypet.mapper.MemberMapper";	
	@Inject
	SqlSession session;

	/*	search	*/
	@Override
	public MemberVO selectById(String user_id) throws Exception {
		return session.selectOne(namespace+".selectById",user_id);
	}
	
	/*	search	*/
	@Override
	public List<UserRole> selectPermissionById(Integer user_no) throws Exception {
		return null;
	}

	@Override
	public void registerMember(MemberVO vo) throws Exception {
		session.insert(namespace+".registerMember",vo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
