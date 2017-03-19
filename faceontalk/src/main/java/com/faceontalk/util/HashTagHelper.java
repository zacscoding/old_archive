package com.faceontalk.util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
 * TEMP hash tag helper.....
 */
public class HashTagHelper {	
	public static String contents1 ="ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ\n해시 태그 테스트\n#해시#태그#테스트#ㅋㅋㅋㅋㅋㅋ";
	public static String contents2= "kkkk";		
	
	public static List<String> getAllHashTags(String content) {
		//해시태그 리스트
		List<String> hashTagsList = new LinkedList<>();
		// 중복 태그 방지 HashSet
		HashSet<String> set = new HashSet<>();
		int curIdx = 0;
		int startIdx = 0;

		while (curIdx < content.length()) {
			if (content.charAt(curIdx) == '#') {
				startIdx = curIdx++;
				while (curIdx < content.length() && content.charAt(curIdx) != ' '
						&& content.charAt(curIdx) != '#'&& content.charAt(curIdx) != '\n') {
					curIdx++;
				}

				String tag = content.substring(startIdx + 1, curIdx);
				if (!tag.isEmpty() && set.add(tag)) { // 빈 문자열이 아니고, 중복 태그가 아니면
					hashTagsList.add(tag);
				}
			} else {
				curIdx++;
			}
		}
		
		return hashTagsList;
		
		
		//Queue<String> hashTagQue = new LinkedList<>();
		//Set<String> hashTagsSet = new LinkedHashSet<>();
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
	}
	
	
	
	
	
}
