package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.MemberVO;
import com.mypet.security.UserRole;

public interface MemberDAO {	
	
	public MemberVO selectById(String user_id) throws Exception;
	
	
	//권한 로그
	public List<UserRole> selectPermissionById(Integer user_no) throws Exception;

}
