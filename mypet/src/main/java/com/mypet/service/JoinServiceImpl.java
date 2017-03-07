package com.mypet.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.MemberVO;
import com.mypet.dto.EmailAuthDTO;
import com.mypet.exception.DuplicateIdException;
import com.mypet.exception.ExceedPeriodException;
import com.mypet.persistence.MemberDAO;
import com.mypet.util.EmailSenderUtil;

@Service
public class JoinServiceImpl implements JoinService {	
	@Inject
	private MemberDAO dao;	
	@Inject
	private PasswordEncoder passwordEncoder;
	@Inject 
	private RegistrationNotifierService registrationNotifierService;

	@Transactional
	@Override
	public void registMember(MemberVO vo) throws Exception {		
		/*
		 * 1.tbl_member 에 유저 등록
		 * 2.등록된 user_no 가져오기(sequence) 
		 * 3.tbl_email_auth 테이블에 삽입
		 * 4.이메일 발송 
		 */		
		//register tbl_member
		MemberVO member = dao.selectById(vo.getUser_id());
		if (member != null)
			throw new DuplicateIdException();
		String password = passwordEncoder.encode(vo.getUser_password());
		vo.setUser_password(password);
		dao.registerMember(vo);
		
		//dao.transTest();
		
		// 인증 토큰 생성
		String auth_token = EmailSenderUtil.createToken();
		int amount = 60*60*24; //하루
		Date auth_limit = new Date(System.currentTimeMillis()+(1000*amount));	
		dao.registerAuthToken(vo.getUser_id(),auth_token,auth_limit);
		
		//이메일 전송 vo,token으로 넘기기
		registrationNotifierService.sendMail(vo, auth_token);
	}
	
	@Override
	@Transactional
	public void confirmAuth(EmailAuthDTO dto) throws Exception {		
		dto = dao.getEmailAuth(dto);
		if(dto == null)
			throw new ExceedPeriodException();
		dao.registerMemberAuth(dto.getUser_id());
	}
}














