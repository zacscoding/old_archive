package com.mypet.security;

import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 
 * -파라미터로 전달받은 authentication 객체에 대해 인증 처리를 지원하지 않는다면 null을 리턴
 * -authentication 객체를 이용한 인증에 실패했다면, AuthenticationException을 발생
 * -인증에 성공하면, 인증 정보를 담은 Authentication 객체를 리턴 
 */

public class UserAuthProvider implements AuthenticationProvider {	
	private static final Logger logger= LoggerFactory.getLogger(UserAuthProvider.class);
	@Inject
	private UserDetailsServiceImpl service;
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//1.authentication을 지원하는 타입으로 변환
		String userid = authentication.getName();
		String userpw = (String) authentication.getCredentials();		
		
		logger.info("USERID : "+userid);
		logger.info("USERPW : "+userpw);
			
		UserDetails securityUser = service.loadUserByUsername(userid);
		
		//2.사용자 정보를 조회한다. 존재하지 않으면 익셉션 발생 
		if(securityUser == null || !securityUser.getUsername().equalsIgnoreCase(userid)) 
			throw new BadCredentialsException("Username not found");
		
		//3.조회한 사용자와 파라미터로 받은 authentication의 암호를 비교 암호가 일치하지 않으면 익셉션 발생
		if(!passwordEncoder.matches(userpw, securityUser.getPassword()))
			throw new BadCredentialsException("Wrong password");
		
		//4.사용자가 가진 권한 목록을 구한다
		Collection<? extends GrantedAuthority> authorities = securityUser.getAuthorities();		
		logger.info("Authorities : "+authorities);
		
		//5.인증된 사용자에 대한 Authentication 객체를 생성해서 리턴
		return new UsernamePasswordAuthenticationToken(securityUser,userpw,authorities);	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	

}
