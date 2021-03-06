package com.mypet.domain;

import java.io.Serializable;
import java.util.Date;

/*
create table tbl_member (
	user_no number not null primary key,
	user_id varchar2(20) not null unique,
	user_password varchar2(150) not null,
	user_name varchar2(40) not null,
	user_email varchar2(40) not null,
	user_phone varchar2(20) not null,
	role varchar2(20) default 'MEMBER',
	reg_date timestamp default sysdate,
	email_auth char(1) default 'n',	
	postcode_fk varchar2(7) not null, 
	address varchar2(100) 	
);
create sequence member_seq;
 */
public class MemberVO implements Serializable {
	//default UID
	private static final long serialVersionUID = 1L;
	
	private Integer user_no;
	private String user_id;
	private String user_password;
	private String user_name;
	private String user_email;
	private String user_phone;
	private String email_auth;
	private String postcode_fk;
	private String address;
	private Date reg_date;
	private String role;
	
	
	//setters,getters,toString()
	
	public String getEmail_auth() {
		return email_auth;
	}

	public void setEmail_auth(String email_auth) {
		this.email_auth = email_auth;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
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

	public String getUser_phone() {
		return user_phone;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public String getPostcode_fk() {
		return postcode_fk;
	}

	public void setPostcode_fk(String postcode_fk) {
		this.postcode_fk = postcode_fk;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "MemberVO [user_no=" + user_no + ", user_id=" + user_id + ", user_password=" + user_password
				+ ", user_name=" + user_name + ", user_email=" + user_email + ", user_phone=" + user_phone
				+ ", email_auth=" + email_auth + ", postcode_fk=" + postcode_fk + ", address=" + address + ", reg_date="
				+ reg_date + ", role=" + role + "]";
	}	
}
