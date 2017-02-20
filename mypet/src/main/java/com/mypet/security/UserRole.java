package com.mypet.security;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {	
	private String roleName;
	
	public UserRole(String role) {
		this.roleName = role;
	}

	public String getRoleName() {
		return roleName;
	}

	@Override
	public String getAuthority() {
		return roleName;
	}
}

	
