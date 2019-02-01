package com.faceontalk.service.member;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.FollowDTO;
import com.faceontalk.email.EmailSenderUtil;
import com.faceontalk.email.RegistrationNotifierService;
import com.faceontalk.exception.DuplicateIdException;
import com.faceontalk.exception.ExceedPeriodException;
import com.faceontalk.persistence.member.MemberDAO;


@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO dao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	@Inject 
	private RegistrationNotifierService registrationNotifierService;

	//회원 가입
	//@Transactional
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
		
		//5.send email authentication with uri
		registrationNotifierService.sendMail(vo, auth_token);		
	}
	
	//이메일 인증
	@Transactional
	@Override
	public void confirmAuth(EmailAuthVO dto) throws Exception {
		EmailAuthVO auth = dao.getEmailAuth(dto);
		if(auth == null)
			throw new ExceedPeriodException();
		
		dao.activate(auth.getUser_id());
	}
	
	//회원 정보 수정
	@Override
	public void edit(MemberVO vo) throws Exception {
		dao.update(vo);
	}
	
	//비밀번호 수정
	@Override
	public void changePassoword(Integer user_no,String password) throws Exception {
		//encrypt password
		password = passwordEncoder.encode(password);
		dao.changePassoword(user_no, password);
	}
	
	//프로필 업데이트 
	@Override
	public void editProfile(Integer user_no, String profile_pic) throws Exception {
		dao.editProfile(user_no,profile_pic);		
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
	
	//회원 검색 % 연산
	@Override
	public List<MemberVO> searchListById(String user_id) throws Exception {
		return dao.searchListById(user_id);
	}
	
	
	//팔로우 등록
	@Override	
	public void regist(FollowVO vo) throws Exception {		
		dao.registFollower(vo);
	}
	
	//언팔
	@Override
	public void remove(FollowVO vo) throws Exception {
		dao.removeFollower(vo);
	}
	
	//팔로우 체크
	@Override
	public Boolean isFollow(FollowVO vo) throws Exception {
		return dao.isFollow(vo);
	}
	
	@Override
	public List<FollowDTO> getFollowerList(Integer user_no) throws Exception {		
		return dao.getFollowerList(user_no);
	}

	@Override
	public List<FollowDTO> getFollowingList(Integer user_no) throws Exception {
		return dao.getFollowingList(user_no);
	}
	
	//팔로우 카운트
	@Override
	public int getFollowerCount(Integer user_no) throws Exception {
		return dao.getFollowerCount(user_no);
	}

	//팔로잉 카운트
	@Override
	public int getFollowingCount(Integer user_no) throws Exception {
		return dao.getFollowingCount(user_no);
	}
	

	//이메일 인증 기간 지난 것 DB 삭제
	@Override
	@Transactional
	public void removeExpiredAuth() throws Exception {
		dao.removeExpiredAuthMember();
		dao.removeExpiredAuthEmail();		
	}	

}
