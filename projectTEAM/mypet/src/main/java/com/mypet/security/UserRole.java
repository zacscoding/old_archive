package com.mypet.security;

import org.springframework.security.core.GrantedAuthority;

public class UserRole implements GrantedAuthority {
	private static final long serialVersionUID = 1L;
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

	
