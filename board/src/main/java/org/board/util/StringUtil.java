package org.board.util;

/**
 * 문자열 관련 유틸 클래스
 * 
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
public class StringUtil {
	
	/**
	 * 문자열 합치기
	 * 
	 * StringBuilder 내부에 동적으로 배열이 증가하지 못하게,
	 * 초기 값 + 1 를 할당 후 append()
	 * 
	 * 이유 : 
	 * => StringBuilder ( AbstractStringBuilder ) 는 
	 * char[] value = new char[capacity];
	 * int count; // 현재 사용 된 length
	 * append(str) 일때,  
	 * 
	 * ( str + count ) - value.length > 0 면 동적 증가
	 * 
	 * ( 사실 크게 차이 없음 )
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param prefix 접두사
	 * @param body 내용
	 * @param suffix 접미사
	 * @return 합친 문자열
	 */
	public static String combineStrings(String prefix, String body, String suffix) {		
		int totalLength = getTotalLength(prefix,body,suffix);		
		return new StringBuilder(totalLength + 1)
						.append(prefix)
						.append(body)
						.append(suffix)
						.toString();
	}
		
	
	/**
	 * 문자열 유효성 체크 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param value 체크할 문자
	 * @return false : null || "" , 그 외 true 
	 */
	public static boolean isValid(String value) {
		return ( value != null && !value.isEmpty() );
	}
	
	/**
	 * 문자열 길이 반환 메소드 
	 * ( null 이면 0을 반환한다 )
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param value 길이를 구할 String 문자열
	 * @return 문자열 길이 / null 이면 0
	 */
	public static int getLength(String value) {
		if( value == null )
			return 0;
		return value.length();
	}
	
	/**
	 * 문자열 배열의 길이 합을 반환 하는 메소드
	 * @author 	zaccoding
	 * @date 	2017. 4. 22.
	 * @param values
	 * @return
	 */
	public static int getTotalLength(String ... values) {
		int ret = 0;
		for(String value : values) {
			ret += getLength(value);
		}
		return ret;
	}
	
	
	
}
