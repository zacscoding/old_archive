package com.faceontalk.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class NullTest {
	
	@Inject
	SqlSession session;
	
	
	@Test
	public void nullTest() {		
		String namespace="com.faceontalk.mapper.TestMapper";
		
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("num",1);
		
		//session.insert(namespace+".test1",paramMap);
		//session.insert(namespace+".test2",paramMap);
		session.insert(namespace+".test3",paramMap);
		
		
	}
	
}
