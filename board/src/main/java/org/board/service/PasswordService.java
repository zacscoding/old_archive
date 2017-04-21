package org.board.service;

import org.board.domain.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public void encryptPassword(MemberVO vo) {
		String encPassword = passwordEncoder.encode( vo.getPassword() );
		vo.setPassword(encPassword);
	}

}
