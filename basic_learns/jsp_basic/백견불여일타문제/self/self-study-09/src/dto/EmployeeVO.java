package dto;

import java.util.Date;

public class EmployeeVO {
	private String id;
	private String password;
	private String name;
	private String lev;		
	private Date enter;
	private String gender;
	private String phone;
	
	public EmployeeVO(){}
	public EmployeeVO(String id, String password, String name, String lev, Date enter, String gender, String phone) {		
		this.id = id;
		this.password = password;
		this.name = name;
		this.lev = lev;
		this.enter = enter;
		this.gender = gender;
		this.phone = phone;
	}



	//getter & setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLev() {
		return lev;
	}

	public void setLev(String lev) {
		this.lev = lev;
	}

	public Date getEnter() {
		return enter;
	}

	public void setEnter(Date enter) {
		this.enter = enter;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}	
}
