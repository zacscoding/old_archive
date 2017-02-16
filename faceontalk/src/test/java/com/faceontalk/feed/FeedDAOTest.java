package com.faceontalk.feed;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faceontalk.feed.domain.HashTagVO;
import com.faceontalk.feed.persistence.FeedDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FeedDAOTest {
	@Inject
	private FeedDAO dao;
	private Logger logger = LoggerFactory.getLogger(FeedDAOTest.class);
	
	
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
	
	@Test
	public void getTagsMapTest() throws Exception {
		Integer feed_no = 1;
		Map<String,HashTagVO> tagsMap = dao.selectTagsByFeedNum(feed_no);
		logger.info("검색된 태그들 사이즈 : "+tagsMap.size());
		Iterator<String> keyItr = tagsMap.keySet().iterator();
		int i = 1;
		while(keyItr.hasNext()) {
			logger.info((i++)+"번째 : "+keyItr.next());
		}		
	}
	
	
	
	
	
//	// test for remove
//	@Test
//	public void removeTest() throws Exception {
//		Integer feed_no = Integer.valueOf(1);
//		dao.remove(feed_no);
//	}
	
	
}
