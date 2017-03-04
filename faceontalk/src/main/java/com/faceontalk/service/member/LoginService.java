package com.faceontalk.service.member;

import java.util.Date;

import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.LoginDTO;

public interface LoginService {
	public MemberVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(Integer user_no,String sessionId,Date next) throws Exception;
	
	public MemberVO checkLoginBefore(String value);
}
