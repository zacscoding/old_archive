package com.web.mapper;


import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { 
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/persistence-context.xml" })
public class DataSourceTest {
	@Autowired DataSource ds;
	
	@Test
	public void test() {
		assertNotNull(ds);
		try(Connection conn = ds.getConnection()) {
			System.out.println(conn.toString());
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
