package com.faceontalk.member.domain;

import java.util.Date;

public class MemberVO {
	private Integer user_id;
	private String user_name;
	private String user_email;
	private String password;
	private Date regdate;
	private String phone;
	
	//setters,getters,toString
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "MemberVO [user_id=" + user_id + ", user_name=" + user_name + ", user_email=" + user_email
				+ ", password=" + password + ", regdate=" + regdate + ", phone=" + phone + "]";
	}	
}
