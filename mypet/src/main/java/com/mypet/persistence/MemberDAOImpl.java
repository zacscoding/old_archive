package com.mypet.persistence;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;
import com.mypet.dto.EmailAuthDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	private static final String namespace = "com.mypet.mapper.MemberMapper";	
	@Inject
	SqlSession session;

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

	@Override
	public MemberVO getLastInsertedMember() throws Exception {
		return session.selectOne(namespace+".getLastInsertedMember");
	}
	@Override
	public void registerAuthToken(String user_id,String auth_token, Date auth_limit) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("user_id", user_id);
		paramMap.put("auth_token", auth_token);
		paramMap.put("auth_limit", auth_limit);		
		session.insert(namespace+".registerAuthToken",paramMap);		
	}

	@Override
	public EmailAuthDTO getEmailAuth(EmailAuthDTO dto) throws Exception {		
		return session.selectOne(namespace+".getEmailAuth",dto);
	}

	@Override
	public void registerMemberAuth(String user_id) throws Exception {		
		session.update(namespace+".registerMemberAuth", user_id);
	}

	@Override
	public void checkEmailAuthPeriod() throws Exception {
		session.update(namespace+".checkEmailAuthPeriod");
	}

	@Override
	public void removeExceedAuthMember() throws Exception {
		session.update(namespace+".removeExceedAuthMember");
	}	
	
	
	
	
	

}
