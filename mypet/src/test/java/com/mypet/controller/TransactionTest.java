package com.mypet.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.mypet.service.TestServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class TransactionTest {
	
	Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	
	@Inject
	TestServiceImpl service;
	
	@Test
	public void test() throws Exception {
		service.transTest();
	}
	
}
