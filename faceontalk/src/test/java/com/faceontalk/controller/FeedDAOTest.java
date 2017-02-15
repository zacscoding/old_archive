package com.faceontalk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faceontalk.feed.persistence.FeedDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class FeedDAOTest {
	@Inject
	FeedDAO dao;
	
//	//test for regist & get last inserted feed_no &	
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void registerTest() throws Exception {
//		try {
//			FeedVO vo = new FeedVO();
//			vo.setUser_id_fk(1);
//			vo.setUser_name_fk("hiva1");
//			vo.setContent("test3333");
//			vo.setFile_name("none");
//			dao.register(vo);
//			
//			vo = dao.getLastInserted();
//			System.out.println(vo);
//			
//			//have to test again
//			//System.out.println(dao.getLastInserted());			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void relationTest() throws Exception {
		dao.registerRelation(13,null);
	}
	
	
	
//	// test for remove
//	@Test
//	public void removeTest() throws Exception {
//		Integer feed_no = Integer.valueOf(1);
//		dao.remove(feed_no);
//	}
	
	
}
