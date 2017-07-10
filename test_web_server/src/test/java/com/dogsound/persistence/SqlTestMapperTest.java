package com.dogsound.persistence;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.dogsound.domain.DatasVO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
//@ActiveProfiles("db.oracle.11gExp")
@ActiveProfiles("db.sqlserver.2008")
public class SqlTestMapperTest {	
	@Autowired
	TestMapper testMapper;
	DatasVO vo;
	
	@Before
	public void setUp() {
		vo = new DatasVO();
		vo.setStringCol("string");
	}
	
	@Test
	public void insert() {
		testMapper.insertOne(vo);
	}
	

}
