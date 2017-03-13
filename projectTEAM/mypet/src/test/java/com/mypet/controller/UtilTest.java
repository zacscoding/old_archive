package com.mypet.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.util.UriComponentsBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class UtilTest {
	Logger logger = LoggerFactory.getLogger(UtilTest.class);
	
	@Test
	public void uriTest() {
		String uri="http://localhost:8080/user/confirm_verification";
		String str2 = UriComponentsBuilder.fromPath(uri)
		.queryParam("user_id","hiva")
		.queryParam("auth_token","1234")
		.build()
		.toUriString();
		
		String str = UriComponentsBuilder.newInstance()						
						.fragment(uri)
						.queryParam("user_id","hiva")
						.queryParam("auth_token","1234")
						.build()
						.toUriString();
		
		logger.info("str : "+str);
		logger.info("str2 : "+str2);
	}
}
