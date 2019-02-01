package com.faceontalk.persistence.member;

import java.util.Date;
import java.util.List;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.FollowDTO;

public interface MemberDAO {
	//crud
	public void regist(MemberVO vo) throws Exception;
	public MemberVO searchById(String user_id) throws Exception;
	public MemberVO searchByNum(Integer user_no) throws Exception;
	public void update(MemberVO vo) throws Exception;
	public void changePassoword(Integer user_no,String password) throws Exception;
	public void editProfile(Integer user_no, String profile_pic) throws Exception;
	
	public int getFollowerCount(Integer user_no)throws Exception;
	public int getFollowingCount(Integer user_no)throws Exception;
	public List<MemberVO> searchListById(String user_id) throws Exception;
	
	//login
	public void keepLogin(Integer user_no, String sessionId, Date next) throws Exception;
	public MemberVO checkUserWithSessionKey(String value);
	
	//follower
	public void registFollower(FollowVO vo) throws Exception;
	public void removeFollower(FollowVO vo) throws Exception;
	public Boolean isFollow(FollowVO vo) throws Exception;	
	public List<FollowDTO> getFollowerList(Integer user_no) throws Exception;
	public List<FollowDTO> getFollowingList(Integer user_no) throws Exception;
	
	
	//auth
	public void registerAuthToken(String user_id,String auth_token,Date auth_limit) throws Exception;
	public EmailAuthVO getEmailAuth(EmailAuthVO dto) throws Exception;
	public void activate(String user_id) throws Exception;
	public void removeExpiredAuthEmail() throws Exception;
	public void removeExpiredAuthMember() throws Exception; 
	
	
}
