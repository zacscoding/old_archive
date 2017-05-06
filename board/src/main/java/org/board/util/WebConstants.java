package org.board.util;

/**
 * 상수를 담고 있는 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 3.
 */
public class WebConstants {
	
	/**
	 * 세션에 담을 로그인 
	 */
	public static final String LOGIN_SESSION_NAME = "login";
	
	/**
	 * 기존 요청 경로
	 */
	public static final String URI_DESTINATION = "dest";
	
	/**
	 * 로그인 쿠키 
	 */
	public static final String LOGIN_COOKIE_NAME = "loginCookie";
	
	/**
	 * 파일 저장 패스	
	 */
	public static final String UPLOAD_ATTACH_PATH = "c:\\board\\attach";
	
	/**
	 * 이미지 업로드 패스
	 */
	public static final String UPLOAD_IMAGE_PATH = "c:\\board\\images";
	
	/**
	 * 파일 POST 전 임시 패스
	 */
	public static final String UPLOAD_TEMP_PATH = "c:\\board\\temp";
	
}
