package org.board.domain;

import java.util.Date;

/**
 * 회원 관련 Domain 클래스
 * 
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
public class MemberVO {
	/**
	 * 유저 시퀀스
	 */
	private Integer userNo;
	
	/**
	 * 계정 아이디
	 */
	private String userId;
	
	/**
	 * 비밀 번호
	 */
	private String password;
		
	/**
	 * 이메일
	 */
	private String email;
	
	/**
	 * 프로필 사진
	 */
	private String profilePic;
	
	/**
	 * 등록일
	 */
	private Date regDate;
	
	
	/*		getters, setters , toString	*/
	public Integer getUserNo() {
		return userNo;
	}
	public void setUserNo(Integer userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userNo=" + userNo + ", userId=" + userId + ", password=" + password + ", email=" + email
				+ ", profilePic=" + profilePic + ", regDate=" + regDate + "]";
	}	
}
