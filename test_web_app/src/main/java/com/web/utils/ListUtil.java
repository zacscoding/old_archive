package com.web.utils;

import java.util.List;

public class ListUtil {
	private ListUtil(){}
	
	public static boolean isValid(List<?> list) {
		return (list != null) && (list.size() != 0);		
	}
}
