package org.board.service;

import org.board.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 암호 관련 서비스 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 1.
 */
@Service
public class PasswordService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 암호화 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param vo
	 */
	
	public void encryptPassword(MemberVO vo) {
		String encPassword = passwordEncoder.encode( vo.getPassword() );
		vo.setPassword(encPassword);
	}
	
	/**
	 * 암호 매치 여부 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param rawPassword 원본 문자열
	 * @param encodedPassword 암호화 된 문자열
	 * @return true : matched , false : not matched
	 */
	
	public boolean isMatched(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	

}
