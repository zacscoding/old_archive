package com.dogsound.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 문자열 관련 유틸 클래스
 * 
 * @author zaccoding
 * 
 */
public class StringUtils {
	static String TAB = "\t";
	static String NEW_LINE="\r\n";
	
	/**
	 * 문자열 유효성 체크
	 * null or empty => false
	 * 그 외 true
	 * @param value 체크할 값
	 * @return 
	 */
	public static boolean isValid(String value) {
		return (value != null && !value.isEmpty() );
	}
	
	 public static <T> Set<T> getSetFromList(List<T> list, String type) {		 
		 Set<T> retSet = null;
		 if( type.equalsIgnoreCase("tree") ) {
			 retSet = new TreeSet<T>();
		 } else if( type.equalsIgnoreCase("hash") ) {			 
			 retSet = new HashSet<T>();
		 }
		 
		 for( T elts : list ) {
			 retSet.add(elts);
		 }
		 
		 return retSet;
	 }
	 
	 public static <T> Set<T> getSetFromArrays(T[] list, String type) {		 
		 Set<T> retSet = null;
		 if( type.equalsIgnoreCase("tree") ) {
			 retSet = new TreeSet<T>();
		 } else if( type.equalsIgnoreCase("hash") ) {			 
			 retSet = new HashSet<T>();
		 }
		 
		 for( T elts : list ) {
			 retSet.add(elts);
		 }
		 
		 return retSet;
	 }
	 
	 public static String getJsonFormatString(String oneLine) {
		 StringBuilder sb = new StringBuilder();
			int tabSize = 1;		
			for(int i=0; i<oneLine.length(); i++) {
				char ch = oneLine.charAt(i);
				switch(ch) {
				case '{':
				case '[':
					if(i==0) {
						sb.append(ch);
						sb.append(NEW_LINE);
						for(int j=1;j<=tabSize;j++){
							sb.append(TAB);
						}
					}
					else {
						sb.append(NEW_LINE);
						for(int j=1;j<=tabSize;j++){
							sb.append(TAB);
						}
						sb.append(ch);
						sb.append(NEW_LINE);
						tabSize++;
						for(int j=1;j<=tabSize;j++){
							sb.append(TAB);
						}
					}								
					break;
				case ',':
					sb.append(ch);
					sb.append(NEW_LINE);
					for(int j=1;j<=tabSize;j++){
						sb.append(TAB);
					}
					break;
				case '}':
				case ']':
					tabSize--;
					sb.append(NEW_LINE);
					for(int j=1;j<=tabSize;j++){
						sb.append(TAB);
					}
					sb.append(ch);				
					break;
				default :				
					sb.append(ch);
				}
			}
			
			return sb.toString();
	 }
	 
	 
	 
	 

}
