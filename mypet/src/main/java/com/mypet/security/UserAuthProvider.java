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

import com.mypet.service.UserService;

/** 
 * -파라미터로 전달받은 authentication 객체에 대해 인증 처리를 지원하지 않는다면 null을 리턴
 * -authentication 객체를 이용한 인증에 실패했다면, AuthenticationException을 발생
 * -인증에 성공하면, 인증 정보를 담은 Authentication 객체를 리턴 
 */

public class UserAuthProvider implements AuthenticationProvider {	
	private static final Logger logger= LoggerFactory.getLogger(UserAuthProvider.class);
	@Inject
	private UserService service;
	
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String userid = authentication.getName();
		String userpw = (String) authentication.getCredentials();
		
		logger.info("USERID : "+userid);
		logger.info("USERPW : "+userpw);
		
		UserDetails securityUser = service.loadUserByUsername(userid);
		
		if(securityUser == null || !securityUser.getUsername().equalsIgnoreCase(userid)) 
			throw new BadCredentialsException("Username not found");
		
		if(!userpw.equals(securityUser.getPassword()))
			throw new BadCredentialsException("Wrong password");
		
		Collection<? extends GrantedAuthority> authorities
					= securityUser.getAuthorities();
		
		logger.info("Authorities : "+authorities);
		
		return new UsernamePasswordAuthenticationToken(securityUser,userpw,authorities);		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
	
	

}
