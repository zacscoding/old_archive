package com.faceontalk.dto;

public class MemberVO {
	private String name;
	private String userId;
	private String pwd;
	private String email;
	private String phone;
	private int admin;
	
	public MemberVO(){}
	public MemberVO(String name, String userId, String pwd, String email, String phone, int admin) {
		super();
		this.name = name;
		this.userId = userId;
		this.pwd = pwd;
		this.email = email;
		this.phone = phone;
		this.admin = admin;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	
	
	
}
