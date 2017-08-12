package com.faceontalk.member.dto;

import java.util.Date;

public class MemberVO {
	//variables
	private int user_id;
	private String user_name;
	private String password;
	private String email;
	private Date regdate;
	private String phone;
	private String gender;
	private String birth;
	private int friend_count;
	
	//constructors
	public MemberVO(){}
	public MemberVO(int user_id, String user_name, String password, String email, Date regdate, String phone,
			String gender, String birth, int friend_count) {		
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.email = email;
		this.regdate = regdate;
		this.phone = phone;
		this.gender = gender;
		this.birth = birth;
		this.friend_count = friend_count;
	}


	//getters,setters
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public int getFriend_count() {
		return friend_count;
	}
	public void setFriend_count(int friend_count) {
		this.friend_count = friend_count;
	}
	
	
}
