package com.faceontalk.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDisplayHelper {
	private static final long DAY_TO_SEC = 60L * 60 * 24;
	private static final long HOUR_TO_SEC = 60L * 60;
	private static final long MILLISECONDS = 1L;
	
	/**
	 * @param date
	 * @return date format
	 * 1.다른 연도 => xx년 x월 xx일
	 * 2.24시간이 안지남 => xx시간 전 or xx분 전
	 * 3.그 외 => x월 xx일
	 */
	
	public static String getDisplayDate(Date date) {		
		if(date == null) 
			return null;
		
		Calendar reg = Calendar.getInstance();
		reg.setTime(date);
		
		Calendar now = Calendar.getInstance();
		
		if(reg.get(Calendar.YEAR) != now.get(Calendar.YEAR)) { //년도가 다름
			return new SimpleDateFormat("yy년 M월 dd일").format(date);
		} else { //같은 해
			return new SimpleDateFormat("M월 dd일").format(date);
			
			/*
			 * 에러 getTime()하면 현재보다, 이전 시간이 더 크게 나옴 수정해야됨
			long diffSec = (new Date().getTime() - date.getTime()) / MILLISECONDS;
			System.out.println(diffSec);
			
			if(diffSec < DAY_TO_SEC ) { //24시간이 지나지 않음 
				if(diffSec < HOUR_TO_SEC) //1시간이 지나지 않음
					return ( diffSec / 60 )+"분 전";
				else 
					return ( diffSec / 3600 )+"시간 전";				
			} else { //24시간이 지남
				return new SimpleDateFormat("M월 dd일").format(date);
			}
           */												
		}		
	}
}
