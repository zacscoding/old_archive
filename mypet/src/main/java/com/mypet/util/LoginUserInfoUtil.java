package com.mypet.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.mypet.domain.MemberVO;
import com.mypet.security.SecurityUserVO;

/**		로그인 유저 정보 얻는 유틸	*/
public class LoginUserInfoUtil {
	
	/*	MemberVO 인스턴스 얻기	*/
	public static MemberVO getMemberVO() {		
		SecurityUserVO user = (SecurityUserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();		
		return user.getMemberVO();
	}
	
	/*	user id 얻기	*/
	public static String getUserId() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	/*	user no 얻기	*/
	public static Integer getUserNo() {
		return getMemberVO().getUser_no();
	}	
}
