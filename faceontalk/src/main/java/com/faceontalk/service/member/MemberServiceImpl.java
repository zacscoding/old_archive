package com.faceontalk.service.member;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.email.EmailSenderUtil;
import com.faceontalk.email.RegistrationNotifierService;
import com.faceontalk.persistence.member.MemberDAO;
import com.facontalk.errors.DuplicateIdException;
import com.facontalk.errors.ExceedPeriodException;


@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	@Inject 
	private RegistrationNotifierService registrationNotifierService;

	//회원 가입
	@Transactional
	@Override
	public void regist(MemberVO vo) throws Exception {		
		//1.confirm id
		MemberVO member = dao.searchById(vo.getUser_id());
		if(member != null)
			throw new DuplicateIdException();
		
		//2.encrypt password
		vo.setPassword(passwordEncoder.encode(vo.getPassword()));
		
		//3.register db		
		dao.regist(vo);
		
		//4.create auth token
		String auth_token = EmailSenderUtil.createToken();
		int amount = 60*60*24; //하루
		Date auth_limit = new Date(System.currentTimeMillis()+(1000*amount));	
		dao.registerAuthToken(vo.getUser_id(),auth_token,auth_limit);
		
		//send email authentication with uri
		registrationNotifierService.sendMail(vo, auth_token);		
	}
	
	//이메일 인증
	@Transactional
	@Override
	public void confirmAuth(EmailAuthVO dto) throws Exception {
		EmailAuthVO auth = dao.getEmailAuth(dto);
		if(auth == null)
			throw new ExceedPeriodException();
				
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

	@Override
	@Transactional
	public void removeExpiredAuth() throws Exception {
		dao.removeExpiredAuthMember();
		dao.removeExpiredAuthEmail();		
	}	
}
