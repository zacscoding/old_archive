package org.board.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * 문자열 관련 유틸 클래스
 * 
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
public class StringUtil {
	
	/**
	 * 문자열 합치기 <br>
	 * 
	 * StringBuilder 내부에 동적으로 배열이 증가하지 못하게, <br>
	 * 초기 값을 할당 후 append() <br>
	 * 
	 * 이유 : <br>
	 * => StringBuilder ( AbstractStringBuilder ) 는 <br> 
	 * char[] value = new char[capacity]; <br>
	 * int count; // 현재 사용 된 length <br>
	 * append(String str) 일때,   <br>
	 * 
	 * 동적 증가 하는 경우 : <br>
	 * ( str.length() + count ) - value.length > 0 <br> 
	 * == (str.length() + count) > value.length <br>
	 * 
	 * ( 사실 크게 차이 없음 ) <br>
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
		return new StringBuilder(totalLength)
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
	 * 문자열 길이 반환 메소드 <br>
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
	 * 문자열 배열의 길이 합을 반환 하는 메소드<br>
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
	
	/**
	 * \년\월\일 과 같이 오늘 경로를 얻는 메소드 <br>
	 * 
	 * e.g) <br>
	 * \2017\05\03
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 3.
	 * @param cal 원하는 날을 담은 Calendar 인스턴스
	 * @param pattern DecimalFormat에 사용할 패턴
	 * @return Calendar 인스턴스에 담긴 날짜 -> \year\month\date 형태의 문자열 
	 */
	public static String getDatePath(Calendar cal, String pattern) {				
		DecimalFormat df = new DecimalFormat(pattern);
		
		return new StringBuilder()
				.append( File.separator ) 
				.append( cal.get(Calendar.YEAR) ) // 년
				.append( File.separator )
				.append( df.format( cal.get(Calendar.MONDAY) + 1 ) ) //월
				.append( File.separator )
				.append( df.format( cal.get(Calendar.DATE) ) ) //일
				.toString();
	}	
}











