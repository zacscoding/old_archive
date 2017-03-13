package com.faceontalk.dto;

public class LoginDTO {
	
	private String user_id;
	private String password;
	private boolean useCookie;
	
	//setters,getters,toString()
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isUseCookie() {
		return useCookie;
	}
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}
	@Override
	public String toString() {
		return "LoginDTO [user_id=" + user_id + ", password=" + password + ", useCookie=" + useCookie + "]";
	}
	
}
