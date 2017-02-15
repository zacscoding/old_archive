package com.faceontalk.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.persistence.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	
	//회원 가입
	@Override
	public void regist(MemberVO vo) throws Exception {
		dao.regist(vo);
	}

	//회원 정보 수정
	@Override
	public void edit(MemberVO vo) throws Exception {
		dao.update(vo);
	}

	//회원 검색 by name
	@Override
	public MemberVO searchByName(String user_name) throws Exception {
		return dao.searchByName(user_name);		
	}
	//회원 검색 by id
	@Override
	public MemberVO searchById(Integer user_id) throws Exception {
		return dao.searchById(user_id);
	}
	
}
