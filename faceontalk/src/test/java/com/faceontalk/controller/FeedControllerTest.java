package com.faceontalk.controller;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.faceontalk.feed.domain.FeedVO;
import com.faceontalk.feed.domain.HashTagVO;
import com.faceontalk.feed.service.FeedService;
import com.faceontalk.feed.util.HashTagHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FeedControllerTest {	
	public static String content = "aaaabbb\n#해시#태그#테스트#ㅋㅋㅋㅋㅋㅋ";
	@Inject
	private FeedService feedService;
	private Logger logger = LoggerFactory.getLogger(FeedControllerTest.class);
	
	@Transactional
	@Rollback(false)
	@Test
	public void feedTest() throws Exception {
		FeedVO vo = new FeedVO();
		vo.setUser_id_fk(1);
		vo.setUser_name_fk("hiva1");
		vo.setContent(content);
		vo.setFile_name("none");
		
		feedService.register(vo);		
		List<String> hashTags = HashTagHelper.getAllHashTags(vo.getContent());
		
//		if(!hashTags.isEmpty()) { //exist hash tag
//			//새로 삽입된 피드 가져오기(필요한건 feed_no)
//			vo = feedService.getLastInserted();
//			for(String tag_name : hashTags) {
//				//search hashtag 
//				HashTagVO tag = feedService.selectTagByName(tag_name);
//				Integer tag_id = null;
//				if(tag == null) { //not exist					
//					feedService.registerTags(tag_name);		
//					tag_id = -1;
//				} else { //exist
//					tag_id = tag.getTag_id();
//				}				
//				feedService.registerRelation(vo.getFeed_no(), tag_id);
//			}
//		}	
		
		//sol2) --- > 임시 비효율 selectTagByName 2번 하게됨(처음 등록 시)
		if (!hashTags.isEmpty()) { // exist hash tag
			// 새로 삽입된 피드 가져오기(필요한건 feed_no)
			vo = feedService.getLastInserted();
			for (int i = 0; i < hashTags.size(); i++) {
				String tag_name = hashTags.get(i);
				logger.info("tag_name : " + tag_name);
				HashTagVO tag = feedService.selectTagByName(tag_name);
				if (tag == null) {
					feedService.registerTags(tag_name);
					i--;					
				} else {
					feedService.registerRelation(vo.getFeed_no(), tag.getTag_id());
				}
			}
		}
	}

}
