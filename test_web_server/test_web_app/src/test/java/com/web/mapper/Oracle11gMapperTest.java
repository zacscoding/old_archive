package com.web.mapper;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.web.mapper.oracle.v11g.Oracle11gMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/persistence-context.xml" })
public class Oracle11gMapperTest {
	@Autowired Oracle11gMapper oracleMapper;
	
	@Test
	public void selectAndInsert() {
		int applied = oracleMapper.selectAndInsert();
		assertTrue(applied == 2);
	}
	
}
