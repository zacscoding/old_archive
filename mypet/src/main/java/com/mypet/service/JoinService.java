package com.mypet.service;

import com.mypet.domain.MemberVO;
import com.mypet.dto.EmailAuthDTO;

public interface JoinService {
	public void registMember(MemberVO vo) throws Exception;
	
	public void confirmAuth(EmailAuthDTO dto) throws Exception;
	
	
}
