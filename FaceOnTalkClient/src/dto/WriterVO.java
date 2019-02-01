package dto;

import java.io.Serializable;

public class WriterVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String name;
	
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
