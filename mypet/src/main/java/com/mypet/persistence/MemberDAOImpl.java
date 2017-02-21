package com.mypet.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;
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

	@Override
	public void registerMember(MemberVO vo) throws Exception {
		session.insert(namespace+".registerMember",vo);
	}

	@Override
	public List<MemberVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listSearchCriteria",cri);				
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".listSearchCount",cri);
	}

	@Override
	public MemberVO selectByNum(Integer user_no) throws Exception {
		return session.selectOne(namespace+".selectByNum",user_no);
	}

	@Override
	public void modify(MemberVO vo) throws Exception {
		session.update(namespace+".modify",vo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
