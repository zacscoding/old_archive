package com.faceontalk.feed.dto;

public class WriterVO {
	private int id;
	private String email;
	private String name;
	
	public WriterVO(String id,String name,String email){
		this(Integer.parseInt(id), name, email);
	}
	public WriterVO(int id,String name,String email){
		this.id = id;
		this.name = name;		
		this.email = email;
	}	
	//getter
	public int getId(){
		return id;
	}
	public String getName(){
		return name;
	}	
	public String getEmail(){
		return email;
	}
}
