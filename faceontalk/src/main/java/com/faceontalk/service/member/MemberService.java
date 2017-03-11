package com.faceontalk.service.member;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;

public interface MemberService {
	//회원 가입
	public void regist(MemberVO vo) throws Exception;
	//회원 정보 수정
	public void edit(MemberVO vo) throws Exception;
	//회원 찾기
	public MemberVO searchById(String user_id) throws Exception;
	public MemberVO searchByNum(Integer user_no) throws Exception;	
	//회원 탈퇴
	
	
	/*	follower	*/
	// regist
	public void regist(FollowVO vo) throws Exception;
	// remove
	public void remove(FollowVO vo) throws Exception;
	// check
	public Boolean isFollow(FollowVO vo) throws Exception;
	
	/*	Confirm Auth	*/
	public void confirmAuth(EmailAuthVO dto) throws Exception;
	
	/*	remove exceed auth	*/
	public void removeExpiredAuth() throws Exception;
	
	
}
