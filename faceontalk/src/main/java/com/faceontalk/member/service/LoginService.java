package com.faceontalk.member.service;

import java.util.Date;

import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.dto.LoginDTO;

public interface LoginService {
	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(Integer user_no,String sessionId,Date next) throws Exception;
	
	public MemberVO checkLoginBefore(String value);
}
