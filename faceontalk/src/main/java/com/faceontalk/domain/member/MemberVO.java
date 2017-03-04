package com.faceontalk.domain.member;

import java.util.Date;

public class MemberVO {
	private Integer user_no;
	private String user_id;
	private String user_email;
	private String password;
	private boolean enabled;	
	private Date regdate;	
	private String phone;
	private String profile_pic;
	private Integer follower_cnt;
	private Integer following_cnt;
	
	//setters,getters,toString

	public Integer getFollower_cnt() {
		return follower_cnt;
	}
	public void setFollower_cnt(Integer follower_cnt) {
		this.follower_cnt = follower_cnt;
	}
	public Integer getFollowing_cnt() {
		return following_cnt;
	}
	public void setFollowing_cnt(Integer following_cnt) {
		this.following_cnt = following_cnt;
	}
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	
	@Override
	public String toString() {
		return "MemberVO [user_no=" + user_no + ", user_id=" + user_id + ", user_email=" + user_email + ", password="
				+ password + ", enabled=" + enabled + ", regdate=" + regdate + ", phone=" + phone + ", profile_pic="
				+ profile_pic + ", follower_cnt=" + follower_cnt + ", following_cnt=" + following_cnt + "]";
	}
	
	
	
	
}
