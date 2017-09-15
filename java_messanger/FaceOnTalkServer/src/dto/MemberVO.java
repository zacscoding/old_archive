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
	
	//삽입 관련 생성자
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
	//친구 리스트를 위한 생성자
	public MemberVO(int id,String email,String name,String phone,String birth,String gender) {
		this.id = id;
		this.email = email;
		this.name = name;
		this.phone = phone;
		this.birth = birth;
		this.gender = gender;
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
	
	///////////////////////////////////////////	
	//hide information for searched friends list
	public void hidePassword() {
		password = null;
	}	
	public void hideInfoForSearchList() {
		password = null;
		regDate = null;
		hideEmail();
		hideName();
	}	
	public void hideForFriendsList(){
		password = null;
		regDate = null;
	}	
	private void hideEmail() {
		StringTokenizer token = new StringTokenizer(email,"@",true);
		String emailId = token.nextToken();
		emailId = changeToAsterisk(emailId,3);
		email = emailId + token.nextToken()+token.nextToken();		
	}	
	private void hideName() {
		name = changeToAsterisk(name,2);		
	}	
	private String changeToAsterisk(String value, int showLength) {
		int len = value.length();		
		if(len<=2) {
			value = value.substring(0,1)+"*";
		} else {
			StringBuilder sb = new StringBuilder(len);
			sb.append(value.substring(0,showLength));
			for(int i=0;i<len-showLength;i++){
				sb.append('*');
			}
			value = sb.toString();
		}	
		return value;
	}	
	///////////////////////////////////////////
	
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
















