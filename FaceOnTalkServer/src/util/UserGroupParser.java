package util;

import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * user group parsing class
 * 1,2,10 이라는 그룹이 있으면 1:2:10 // 2:1:10 그룹을 구별하기 위해
 * 1:2:10 으로 오름차순 정렬 후 구분자 : 을 넣어줌으로써 group_id를 고유하게 만들어줌
 */

public class UserGroupParser {	
	private UserGroupParser(){}	
	//"1:2:3" user group -> [1,2,3] 배열로
	public static int[] getUserInteger(String group_id) throws NumberFormatException {
		StringTokenizer token = new StringTokenizer(group_id, ":");
		int[] users = new int[token.countTokens()];
			
		for(int i=0;i<users.length;i++){
			users[i] = Integer.parseInt(token.nextToken());
		}		
		Arrays.sort(users);		
		return users;		
	}	
	
	//[1,2,3] 배열을 -> "1:2:3" string으로
	public static String getUserString(int[] users){
		Arrays.sort(users);
		StringBuffer buff = new StringBuffer();
		buff.append(users[0]);
		for(int i=1;i<users.length;i++){
			buff.append(":"+users[i]);
		}
		return buff.toString();	
	}
	
	public static String getUserStringSort(String group_id) {
		return getUserString(getUserInteger(group_id));
	}
	
}
