package util;

import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * users group parsing class
 */

public class UserGroupParser {
	//"1:2:3" user group -> [1,2,3] 배열로
	public static int[] getUserInteger(String group_id) throws NumberFormatException {
		StringTokenizer token = new StringTokenizer(group_id, ":");
		int[] users = new int[token.countTokens()];			
		for(int i=0;i<users.length;i++) {
			users[i] = Integer.parseInt(token.nextToken());
		}		
		Arrays.sort(users);		
		return users;		
	}	
	
	//[1,2,3] 배열을 -> "1:2:3" string으로
	public static String getUserString(int[] users) {
		Arrays.sort(users);
		StringBuffer buff = new StringBuffer();
		buff.append(users[0]);
		for(int i=1;i<users.length;i++){
			buff.append(":"+users[i]);
		}
		return buff.toString();	
	}
	
	public static String getSortedUserId(String group_id) {
		return getUserString(getUserInteger(group_id));
	}
	
	//private constructor
	private UserGroupParser(){}	
}
