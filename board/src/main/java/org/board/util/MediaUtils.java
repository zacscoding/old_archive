package org.board.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

/**
 * Media 관련 유틸 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 5.
 */
public class MediaUtils {
	private static Map<String,MediaType> mediaMap;
	static {
		mediaMap = new HashMap<String,MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF",MediaType.IMAGE_GIF);
		mediaMap.put("PNG",MediaType.IMAGE_PNG);
	}
	
	/**
	 * MediaType을 구하는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 5.
	 * @param type 구하고자 하는 타입 문자열
	 * @return MediaType의 IMAGE_ 상수 , or null (이미지 타입이 아니면)
	 */
	public static MediaType getMediaType(String type) {
		return mediaMap.get( type.toUpperCase() );
	}
	
	/**
	 * MediaType인지 체크하는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 5.
	 * @param type 미디어 타입 체크할 문자열
	 * @return true (미디어 타입) or null (미디어 타입X)
	 */
	public static boolean isMediaType(String type) {
		return ( getMediaType(type) != null );
	}
}
