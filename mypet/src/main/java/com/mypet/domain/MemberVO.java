package com.mypet.domain;


/*
create table tbl_member (
	user_no number not null primary key,
	user_id varchar2(20) not null unique,
	user_name varchar2(40) not null,
	user_email varchar2(40) not null,
	user_phone varchar2(20) not null,
	reg_date timestamp default sysdate,	
	role varchar2(20) default 'MEMBER',
	postcode_fk varchar2(7) not null,	
	address varchar2(100) 	
); 
 */
public class MemberVO {
	private Integer user_no;
	private String user_id;
	private String password;
	private String user_name;
	private String user_email;
	private String user_phone;
	private String role;
	
	
	//setters,getters,toString()
	
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

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public Integer getUser_no() {
		return user_no;
	}

	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "MemberVO [user_no=" + user_no + ", user_id=" + user_id + ", password=" + password + ", user_name="
				+ user_name + ", user_email=" + user_email + ", user_phone=" + user_phone + "]";
	}	
}
