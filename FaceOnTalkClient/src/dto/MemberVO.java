package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.StringTokenizer;

public class MemberVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String email;
	private String name;
	private String password;
	private String phone;
	private String birth;
	private String gender;
	private int friendCount;
	private Date regDate;	
	
	public MemberVO(int id,String email,String name, String password,String phone,String birth,String gender,int friendCount, Date regDate) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
		this.friendCount = friendCount;
		this.regDate = regDate;
	}
	public MemberVO(int id,String email,String name,String phone,String birth,String gender) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
	}
	
	public MemberVO(String name) {
		this.name = name;
	}

	//setter
	public void setName(String name) {
		this.name = name;
	}	
	public void changePassword(String newPwd) {
		password = newPwd;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	//getter
	public int getId() {
		return id;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}	
	public String getPhone(){
		return phone;
	}
	public String getBirth(){
		return birth;
	}
	public String getGender(){
		return gender;
	}
	public int getFriendCount(){
		return friendCount;
	}
	public Date getRegDate() {
		return regDate;
	}	
	public boolean matchPassword(String pwd) {
		return password.equals(pwd);
	}
	@Override
	public String toString(){		
		return name+"("+email+")";
	}	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof MemberVO) {
			MemberVO comp = (MemberVO) obj;
			return comp.getId() == id;
		}
		return false;
	}
}
















