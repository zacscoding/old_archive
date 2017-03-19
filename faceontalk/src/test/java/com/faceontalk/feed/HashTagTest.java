package com.faceontalk.feed;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class HashTagTest {
	
		
	@Test
	public void getAllHashTags() {
		String content ="ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ\n해시 태그 테스트\n#해시#태그#테스트#ㅋㅋㅋㅋㅋㅋ# #zz#zz#    #haha";
		String endChar = " #\n";
		//해시태그 리스트
		List<String> hashTagsList = new LinkedList<>();
		//중복 태그 방지 HashSet
		HashSet<String> set = new HashSet<>();
		int curIdx = 0;
		int startIdx = 0;
		
		while(curIdx < content.length()) {			
			if(content.charAt(curIdx) == '#') {				
				startIdx = curIdx++;
				while(curIdx < content.length() && content.charAt(curIdx) != ' '
						&& content.charAt(curIdx) != '#' && content.charAt(curIdx) !='\n') {
					curIdx++;
				}	
				
				String tag = content.substring(startIdx+1, curIdx);				
				if(!tag.isEmpty() && set.add(tag)) { //빈 문자열이 아니고, 중복 태그가 아니면 										
					hashTagsList.add(tag);
				}
			} else {
				curIdx++;
			}						
		}	
		
		for(String tag : hashTagsList)
			System.out.println(tag);
	}
	
	
	
	
}
