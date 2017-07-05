package com.dogsound.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@ActiveProfiles("db.oracle.11gExp")
public class DataSourceTest {
	private static final Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Autowired DataSource dataSource;
	
	@Test
	public void dataSource () {
		assertNotNull(dataSource);
	}
	
	@Test
	public void connection() throws Exception {
		Connection conn = null;
		try {			
			conn = dataSource.getConnection();
			assertNotNull(dataSource);
			logger.info(conn.toString());
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	

}
