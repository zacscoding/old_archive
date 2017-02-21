package com.mypet.member.service;

import javax.inject.Inject;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.MemberVO;
import com.mypet.error.DuplicateIdException;
import com.mypet.persistence.MemberDAO;

@Service
public class JoinServiceImpl implements JoinService {	
	@Inject
	private MemberDAO dao;	
	@Inject
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public void registMember(MemberVO vo) throws Exception {
		MemberVO member = dao.selectById(vo.getUser_id());
		if (member != null)
			throw new DuplicateIdException();

		String password = passwordEncoder.encode(vo.getUser_password());
		vo.setUser_password(password);

		dao.registerMember(vo);			
	}
}
