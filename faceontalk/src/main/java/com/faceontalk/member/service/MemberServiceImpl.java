package com.faceontalk.member.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faceontalk.member.domain.FollowVO;
import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.persistence.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	//회원 가입
	@Override
	public void regist(MemberVO vo) throws Exception {
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		dao.regist(vo);
	}
	
	//회원 정보 수정
	@Override
	public void edit(MemberVO vo) throws Exception {
		dao.update(vo);
	}

	//회원 검색 by id
	@Override
	public MemberVO searchById(String user_id) throws Exception {
		return dao.searchById(user_id);		
	}
	
	//회원 검색 by num
	@Override
	public MemberVO searchByNum(Integer user_no) throws Exception {
		return dao.searchByNum(user_no);
	}	
	
	@Override
	public void regist(FollowVO vo) throws Exception {
		dao.registFollower(vo);
	}

	@Override
	public void remove(FollowVO vo) throws Exception {
		dao.removeFollower(vo);
	}
}
