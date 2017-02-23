package com.faceontalk.member.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.dto.LoginDTO;
import com.faceontalk.member.persistence.MemberDAO;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Inject
	MemberDAO dao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public MemberVO login(LoginDTO dto) throws Exception {
		MemberVO vo = dao.searchById(dto.getUser_id());		
		
		//mismatch id or password
		if(vo == null || !passwordEncoder.matches(dto.getPassword(),vo.getPassword()))
			vo = null;
		
		return vo;
	}


	@Override
	public MemberVO checkLoginBefore(String value) {
		return dao.checkUserWithSessionKey(value);
	}


	@Override
	public void keepLogin(Integer user_no, String sessionId, Date next) throws Exception {
		dao.keepLogin(user_no, sessionId, next);
	}
	
	

}
