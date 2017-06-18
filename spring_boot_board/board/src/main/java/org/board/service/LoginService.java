package org.board.service;

import java.util.Date;

import org.board.domain.MemberVO;
import org.board.dto.LoginDTO;
import org.board.persistence.MemberMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordService passwordService;
	
	/**
	 * id-password 매치 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param dto 로그인 DTO 
	 * @return DB에 저장 된 VO 인스턴스 or null (아이디 존재 하지 않거나 비밀번호 일치하지 않으면)
	 * @throws Exception
	 */
	public MemberVO login(LoginDTO dto) throws Exception {
		
		MemberVO vo = memberMapper.selectById( dto.getId() );
		
		if( vo == null )
			logger.info("vo is null");
		else
			logger.info("vo : "+ vo.toString());
		
		if( (vo!= null) && ( !passwordService.isMatched(dto.getPassword(), vo.getPassword()) ) ) {
			vo = null;
		}
		
		return vo;
	}
	
	/**
	 * 로그인 쿠키 유효시간 검사
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param sessionId 기존에 쿠키 로그인으로 사용했던 세션 ID
	 * @return 기간이 유효하면 vo 인스턴스 , 아니면 null
	 * @throws Exception
	 */
	public MemberVO checkLoginBefore(String sessionKey) throws Exception {
		return memberMapper.getUserBySessionKey(sessionKey);
	}
	
	/**
	 * 자동 로그인 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param userNo 유저 시퀀스
	 * @param sessionKey 쿠키 사용 한 세션의 ID 
	 * @param expiredDate 유효 기간 
	 * @throws Exception
	 */
	public void keepLogin(Integer userNo, String sessionKey, Date expiredDate) throws Exception {		
		logger.info( userNo + sessionKey + expiredDate );
		memberMapper.keepLogin(userNo, sessionKey, expiredDate);
	}
	
	

}
