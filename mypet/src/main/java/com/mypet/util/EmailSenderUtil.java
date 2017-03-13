package com.mypet.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class EmailSenderUtil {	
	private EmailSenderUtil(){};	
	
	//check...token..
	public static String createToken() {		
		String prefix = getTodayDate();
		UUID uid = UUID.randomUUID();
		return prefix+"_"+uid.toString().replaceAll("-","");
		//return prefix+"_"+uid.toString();
	}
	public static String getTodayDate() {
		return new SimpleDateFormat("yyMMdd")
					.format(Calendar.getInstance().getTime());
	}	
	public static String getEmailAddr(String user_email) {
		int atIdx = user_email.lastIndexOf('@');
		return "www."+user_email.substring(atIdx+1);
	}
	
}
