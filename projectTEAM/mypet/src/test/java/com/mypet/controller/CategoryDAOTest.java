package com.mypet.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.persistence.CategoryDAO;

/**
 * Test code : CategoryDAO
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class CategoryDAOTest {	
	Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Inject	
	CategoryDAO dao;
	
	@Test
	public void daoTest() {		
		try {
//			AnimalVO vo = new AnimalVO();
//			vo.setAnimal_name("dog");		
//			dao.registAnimal(vo);
			AnimalVO vo = dao.selectAnimalByName("dog");			
			
			CategoryVO cvo = new CategoryVO();
			cvo.setCate_name("shoes");
			cvo.setAnimal_no_fk(vo.getAnimal_no());
			cvo.setAnimal_name_fk(vo.getAnimal_name());
			
			dao.registProduct(cvo);
		} catch(Exception e) {
			e.printStackTrace();
		}				
	}

}
