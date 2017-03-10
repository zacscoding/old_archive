package com.faceontalk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDisplayHelper {
	private static final int YEAR_TO_SEC = 10; 
	private static final int DAY_TO_SEC = 60*60*24;
	
	public static String getDisplayDate(Date regDate) {
		return new SimpleDateFormat("yyyy.MM.dd").format(regDate);
		
		/*long curTime = System.currentTimeMillis();
		
		int[] reg = getDateFormat(regDate);
		int[] now = getDateFormat(new Date(curTime));
		
		if(reg[0] != now[0] || reg[1] != now[1]) { //월,연도가 다름
			return new SimpleDateFormat("yyyy.MM.dd").format(regDate);
		} else { //연도,월이 같음			
			if(curTime -regDate.getTime() < 0) { //하루가 안지남
				
			} else { //하루가 지남
				
			}
		}*/
	}
	
	private static int[] getDateFormat(Date date) {
		int[] ret = new int[3];
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		String dateVal =  sdf.format(date);
		
		ret[0] = Integer.parseInt(dateVal.substring(0, 2));
		ret[1] = Integer.parseInt(dateVal.substring(2, 4));
		ret[2] = Integer.parseInt(dateVal.substring(4));
		
		return ret;
	}
	
}
