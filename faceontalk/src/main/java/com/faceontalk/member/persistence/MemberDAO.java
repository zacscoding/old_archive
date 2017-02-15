package com.faceontalk.member.persistence;

import com.faceontalk.member.domain.MemberVO;

public interface MemberDAO {
	public void regist(MemberVO vo) throws Exception;
	public MemberVO searchByName(String user_name) throws Exception;
	public MemberVO searchById(Integer user_id) throws Exception;
	public void update(MemberVO vo) throws Exception;
	
}
