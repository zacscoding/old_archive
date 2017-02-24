package com.faceontalk.feed.util;

import java.util.LinkedList;
import java.util.List;

/*
 * TEMP hash tag helper.....
 */
public class HashTagHelper {	
	public static String contents1 ="ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ\n해시 태그 테스트\n#해시#태그#테스트#ㅋㅋㅋㅋㅋㅋ";
	public static String contents2= "kkkk";		
	
	public static List<String> getAllHashTags(String content) {
		//Queue<String> hashTagQue = new LinkedList<>();
		//Set<String> hashTagsSet = new LinkedHashSet<>();
		List<String> hashTagsList = new LinkedList<>();
		int curIdx = 0;
		int startIdx = 0;
		while(curIdx < content.length()) {
			if(content.charAt(curIdx) == '#') {				
				startIdx = curIdx++;
				/** have to change statement */
				while(curIdx < content.length() 
						&& content.charAt(curIdx) != ' ' && content.charAt(curIdx) != '#' && content.charAt(curIdx) !='\n'){
					curIdx++;
				}				
				//hashTagQue.offer(content.substring(startIdx+1, curIdx));
				hashTagsList.add(content.substring(startIdx+1, curIdx));
			} else {
				curIdx++;
			}						
		}	
		
//		while(!hashTagQue.isEmpty()) {
//			System.out.println(hashTagQue.poll());
//		}
		
//		//null || empty로 보낼지 고민
//		if(hashTagsList.isEmpty())
//			return null;		
//		List<String> tagsList = new ArrayList<>(hashTagsSet.size());
//		Iterator<String> itr = hashTagsSet.iterator();
//		while(itr.hasNext()) {
//			//System.out.println(itr.next());
//			tagsList.add(itr.next());
//		}
//		return tagsList;
		
		return hashTagsList;
	}
	
	
	
	
	
}
