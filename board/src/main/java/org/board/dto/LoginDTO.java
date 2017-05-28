package org.board.dto;

/**
 * 로그인 관련 DTO 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 1.
 */
public class LoginDTO {
	
	/*=================================
	 * Member Fields
	 *================================= */	
	/**
	 * 로그인 아이디
	 */
	private String id;
	
	/**
	 * 로그인 암호
	 */
	private String password;
	
	/**
	 * 쿠키 로그인 사용 여부
	 */
	private boolean useCookie;
	
	
	
	/*=================================
	 * Setters , Getters, toString
	 *================================= */
	
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
	
	public boolean isUseCookie() {
		return useCookie;
	}
	
	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

	@Override
	public String toString() {
		return "LoginDTO [id=" + id + ", password=" + password + ", useCookie=" + useCookie + "]";
	}
}
