package com.faceontalk.feed;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faceontalk.domain.feed.FeedVO;
import com.faceontalk.persistence.feed.FeedDAO;
import com.faceontalk.service.feed.FeedService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FeedDAOTest {
	@Inject
	private FeedDAO dao;
	private Logger logger = LoggerFactory.getLogger(FeedDAOTest.class);
	
	@Inject 
	FeedService feedService;
	
//	//test for regist feed , tag 	
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void registerTest() throws Exception {
//		try {
//			FeedVO vo = new FeedVO();
//			vo.setUser_id_fk(1);
//			vo.setUser_name_fk("hiva1");
//			vo.setContent("test111#tag");
//			vo.setFile_name("none");
//			
//			dao.register(vo); //insert feed
//			dao.registerTag("#tag"); //insert tag
//						
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void relationTest() throws Exception {
//		dao.registerRelation(8,8);
//	}
	
//	@Test
//	@Transactional
//	public void transactionalTest() throws Exception {
//		//register
//		FeedVO vo = new FeedVO();
//		vo.setUser_id_fk(1);
//		vo.setUser_name_fk("hiva1");
//		vo.setContent("test");
//		vo.setFile_name("none");
//		dao.register(vo);
//		logger.info("등록 : "+vo);
//		
//		//get
//		vo = dao.getLastInsertedFeed();
//		logger.info("조회 : "+vo);
//		
//		//update
//		vo.setContent(null);
//		logger.info("변경 : "+vo);
//		dao.update(vo);
//	}
	
//	@Test
//	public void getTagsMapTest() throws Exception {
//		Integer feed_no = 1;
//		Map<String,HashTagVO> tagsMap = dao.selectTagsByFeedNum(feed_no);
//		logger.info("검색된 태그들 사이즈 : "+tagsMap.size());
//		Iterator<String> keyItr = tagsMap.keySet().iterator();
//		int i = 1;
//		while(keyItr.hasNext()) {
//			logger.info((i++)+"번째 : "+keyItr.next());
//		}		
//	}
	
//	@Transactional
//	@Test
//	public void lastInsertedFeedNumTest() throws Exception {
//		try {
//			FeedVO vo = new FeedVO();
//			vo.setUser_no_fk(1);
//			vo.setUser_id_fk("hiva1");
//			vo.setContent("test111");
//			vo.setFile_name("none");			
//			dao.register(vo); //insert feed
//			
//			int feed_no = dao.getLastInsertedFeedNum();
//			logger.info("feed_no : "+feed_no);
////			FeedVO newVO = dao.getLastInsertedFeed();
////			logger.info("new feed = "+newVO);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}		
//	}
	
	
	
	
	
//	// test for remove
//	@Test
//	public void removeTest() throws Exception {
//		Integer feed_no = Integer.valueOf(1);
//		dao.remove(feed_no);
//	}
	
	@Test
	public void insertDB() throws Exception {
		FeedVO vo = new FeedVO();
		vo.setUser_no_fk(1);
		vo.setUser_id_fk("testUser1");
		vo.setContent("#desert #사막 테스트");
		vo.setFile_name("/2017/03/07/s_d66fd3ef-1c29-4df9-a6fc-12e5a206a20e_Desert.jpg");
		
		for(int i=1;i<15;i++) {
			feedService.register(vo);
		}
		
		
		
	}
	
	
}
