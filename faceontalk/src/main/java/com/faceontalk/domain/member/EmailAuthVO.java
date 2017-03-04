package com.faceontalk.domain.member;

public class EmailAuthVO {	
	private String user_id;
	private String auth_token;	
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAuth_token() {
		return auth_token;
	}
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}
	@Override
	public String toString() {
		return "EmailAuthVO [user_id=" + user_id + ", auth_token=" + auth_token + "]";
	}
}
