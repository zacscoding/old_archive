package com.mypet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.MemberVO;
import com.mypet.domain.SearchCriteria;
import com.mypet.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO dao;

	@Override
	public List<MemberVO> listSearchCriteria(SearchCriteria cri) throws Exception {
		return dao.listSearchCriteria(cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {		
		return dao.listSearchCount(cri);
	}

	@Override
	public MemberVO selectByNum(Integer user_no) throws Exception {
		return dao.selectByNum(user_no);
	}

	@Override
	public MemberVO selectById(String user_id) throws Exception {
		return dao.selectById(user_id);
	}

	@Override
	public void modifyUser(MemberVO vo) throws Exception {
		dao.modify(vo);
		
	}

	@Override
	@Transactional
	public void removeExpiredAuth() throws Exception {
		dao.removeExpiredAuthMember();
		dao.removeExpiredAuthEmail();		
	}

}
