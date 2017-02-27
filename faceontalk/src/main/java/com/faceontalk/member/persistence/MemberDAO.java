package com.faceontalk.member.persistence;

import java.util.Date;

import com.faceontalk.member.domain.EmailAuthVO;
import com.faceontalk.member.domain.FollowVO;
import com.faceontalk.member.domain.MemberVO;

public interface MemberDAO {
	//crud
	public void regist(MemberVO vo) throws Exception;
	public MemberVO searchById(String user_id) throws Exception;
	public MemberVO searchByNum(Integer user_no) throws Exception;
	public void update(MemberVO vo) throws Exception;
	
	//login
	public void keepLogin(Integer user_no, String sessionId, Date next) throws Exception;
	public MemberVO checkUserWithSessionKey(String value);
	
	//follower
	public void registFollower(FollowVO vo) throws Exception;
	public void removeFollower(FollowVO vo) throws Exception;
	
	//auth
	public void registerAuthToken(String user_id,String auth_token,Date auth_limit) throws Exception;
	public EmailAuthVO getEmailAuth(EmailAuthVO dto) throws Exception;
	public void activate(String user_id) throws Exception;
	public void removeExpiredAuthEmail() throws Exception;
	public void removeExpiredAuthMember() throws Exception; 
	
	
}
