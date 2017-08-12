package com.faceontalk.member.action;

public class User {
	private String id;
	private String name;
	private String email;
	
	public User(String id,String name,String email) {
		this.id = id;
		this.name = name;		
		this.email = email;
	}
	
	public User(int id,String name,String email) {
		this(String.valueOf(id),name,email);
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}	
}
