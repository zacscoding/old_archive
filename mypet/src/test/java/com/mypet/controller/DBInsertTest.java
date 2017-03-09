package com.mypet.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mypet.domain.ReviewVO;
import com.mypet.persistence.ReviewDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class DBInsertTest {
	Logger logger = LoggerFactory.getLogger(DBInsertTest.class);
	@Inject
	ReviewDAO dao;
	
	@Test
	public void insertReviews() throws Exception {
		ReviewVO vo = dao.readReview(2);
		
		for(int i=1;i<255;i++) {
			vo.setReview_title("review test"+i);
			dao.registerReview(vo);
		}
		
	}

}
