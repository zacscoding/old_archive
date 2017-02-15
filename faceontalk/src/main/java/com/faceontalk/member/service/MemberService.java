package com.faceontalk.member.service;

import com.faceontalk.member.domain.MemberVO;

public interface MemberService {
	//회원 가입
	public void regist(MemberVO vo) throws Exception;
	//회원 정보 수정
	public void edit(MemberVO vo) throws Exception;
	//회원 찾기
	public MemberVO searchByName(String user_name) throws Exception;
	public MemberVO searchById(Integer user_id) throws Exception;
	
	//회원 탈퇴
	
	
	
}
