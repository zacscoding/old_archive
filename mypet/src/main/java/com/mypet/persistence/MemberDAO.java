package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.MemberVO;
import com.mypet.security.UserRole;

public interface MemberDAO {	
	
	//id로 검색
	public MemberVO selectById(String user_id) throws Exception;
	//권한 로그
	public List<UserRole> selectPermissionById(Integer user_no) throws Exception;
	//가입
	public void registerMember(MemberVO vo) throws Exception;
	
	

}
