package com.mypet.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


/**
 * Test code : DataSource and Connection 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DataSourceTest {
	Logger logger = LoggerFactory.getLogger(DataSourceTest.class);	
	@Inject
	DataSource ds;	
	@Test
	public void dataSourceTest() {
		try(Connection conn = ds.getConnection()) {
			logger.info(conn.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
