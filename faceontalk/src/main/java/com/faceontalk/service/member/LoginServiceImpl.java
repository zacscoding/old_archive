package com.faceontalk.service.member;

import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.LoginDTO;
import com.faceontalk.persistence.member.MemberDAO;

@Service
public class LoginServiceImpl implements LoginService {
	private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	
	@Inject
	MemberDAO dao;
	
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
		
	@Override
	public MemberVO login(LoginDTO dto) throws Exception {		
		MemberVO vo = dao.searchById(dto.getUser_id());
		
		logger.info("loginService ... DTO : "+dto.toString());
				
		if(vo == null || !passwordEncoder.matches(dto.getPassword(),vo.getPassword())) {			
			vo = null;
		}		
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
