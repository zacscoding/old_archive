package com.faceontalk.member.domain;

import java.util.Date;

public class MemberVO {
	private Integer user_no;
	private String user_id;
	private String user_email;
	private String password;
	private Date regdate;
	private String phone;
	
	//setters,getters,toString

	public String getUser_email() {
		return user_email;
	}
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
		return "MemberVO [user_no=" + user_no + ", user_id=" + user_id + ", user_email=" + user_email + ", password="
				+ password + ", regdate=" + regdate + ", phone=" + phone + "]";
	}	
}
