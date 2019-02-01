package com.faceontalk.feed;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.domain.feed.HashTagVO;
import com.faceontalk.service.feed.FeedService;
import com.faceontalk.util.HashTagHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FeedControllerTest {
	
	public static String content = "aaaabbb\n#해시#태그#테스트#ㅋㅋㅋㅋㅋㅋ";
	public static String modifiedContent = "cccc\n#해시#테스트#aaa#bbbb";
	@Inject
	private FeedService feedService;
	private Logger logger = LoggerFactory.getLogger(FeedControllerTest.class);
	
//	@Transactional	
//	@Rollback(false)
//	@Test
//	public void registFeedTest() throws Exception {
//		FeedVO vo = new FeedVO();
//		vo.setUser_id_fk(1);
//		vo.setUser_name_fk("hiva1");
//		vo.setContent(content);
//		vo.setFile_name("none");
//		
//		feedService.register(vo);		
//		List<String> hashTags = HashTagHelper.getAllHashTags(vo.getContent());
//		
////		if(!hashTags.isEmpty()) { //exist hash tag
////			//새로 삽입된 피드 가져오기(필요한건 feed_no)
////			vo = feedService.getLastInserted();
////			for(String tag_name : hashTags) {
////				//search hashtag 
////				HashTagVO tag = feedService.selectTagByName(tag_name);
////				Integer tag_id = null;
////				if(tag == null) { //not exist					
////					feedService.registerTags(tag_name);		
////					tag_id = -1;
////				} else { //exist
////					tag_id = tag.getTag_id();
////				}				
////				feedService.registerRelation(vo.getFeed_no(), tag_id);
////			}
////		}	
//		
//		//sol2) --- > 임시 (last_insert_id() 함수에 따라 비효율)
//		if(!hashTags.isEmpty()) { //exist hash tag
//			//새로 삽입된 피드 가져오기(필요한건 feed_no)
//			vo = feedService.getLastInsertedFeed();			
//			for(String tag_name : hashTags) {
//				registFeedAndTag(vo.getFeed_no(),tag_name);
//			}				
//		}				
//		
//		
////		//sol2) --- > 임시 비효율? 
////		if (!hashTags.isEmpty()) { // exist hash tag
////			// 새로 삽입된 피드 가져오기(필요한건 feed_no)
////			vo = feedService.getLastInsertedFeed();
////			for (String tag_name : hashTags) {
////				logger.info("tag_name : " + tag_name);
////				HashTagVO tag = feedService.selectTagByName(tag_name);
////
////				if (tag == null) {
////					feedService.registerTags(tag_name);
////					tag = feedService.getLastInsertedTag();
////				}
////				
////				if(tag == null) {
////					logger.info("tag 다시 널");
////				} else {
////					logger.info(tag.toString());
////					feedService.registerRelation(vo.getFeed_no(), tag.getTag_id());
////				}
////				
////			}
////		}
//	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void modifyFeedTest() throws Exception {
		FeedVO vo = new FeedVO();
		vo.setFeed_no(1);
		vo.setUser_no_fk(1);
		vo.setUser_id_fk("hiva1");
		vo.setContent(modifiedContent);
		vo.setFile_name("none");
		//tags
		Map<String, HashTagVO> prevTagsMap = feedService.getTagsMap(vo.getFeed_no());
		List<String> modifiedTagsList = HashTagHelper.getAllHashTags(vo.getContent());
		List<String> newTagsList = new LinkedList<>();
		for (int i = 0; i < modifiedTagsList.size(); i++) {
			String tag_name = modifiedTagsList.get(i);
			if (prevTagsMap.remove(tag_name) == null) {
				newTagsList.add(tag_name);
			}
		}
		// 새로운 관계 생성 == newTagsList
		for (String tag_name : newTagsList) {
			registFeedAndTag(vo.getFeed_no(), tag_name);
		}
		// 기존 관계 삭제 == prevTagsMap
		Iterator<String> keyItr = prevTagsMap.keySet().iterator();
		while (keyItr.hasNext()) {
			HashTagVO tag = prevTagsMap.get(keyItr.next());
			feedService.removeRelation(vo.getFeed_no(), tag.getTag_id());
		}
		// 2.content
		feedService.modify(vo);
	}
	
	
	private void registFeedAndTag(int feed_no,String tag_name) throws Exception {
		HashTagVO tag = feedService.selectTagByName(tag_name);				
		if(tag == null) {
			feedService.registerTags(tag_name);
			tag = feedService.getLastInsertedTag();
		}				
		feedService.registerRelation(feed_no,tag.getTag_id());
	}

}
