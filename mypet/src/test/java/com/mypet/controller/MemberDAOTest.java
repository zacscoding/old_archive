package com.mypet.controller;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.MemberVO;
import com.mypet.persistence.MemberDAO;


/**
 * Test code : MemberDAO , PasswordEncoder
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={
		"file:src/main/webapp/WEB-INF/spring/**/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/**/security-context.xml"})
public class MemberDAOTest {
	Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
	
	@Inject
	MemberDAO dao;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
	/* regist test*/
//	@Transactional
//	@Test
//	public void registMemberTest() {
//		MemberVO vo = new MemberVO();
//		vo.setUser_id("hiva3");
//		vo.setUser_password("hiva1");
//		vo.setUser_name("hiva1");
//		vo.setUser_email("hiva.com");
//		vo.setUser_phone("010");
//		vo.setPostcode_fk("123");
//		vo.setAddress("zzz");
//		try {			
//			dao.registerMember(vo);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}		
//	}
	
	
	/* encrypt test*/
	@Test
	public void encryptTest() {
		String original = "aaa";
		String encrypted = passwordEncoder.encode(original);
		logger.info("------------------------------------------");
		logger.info("original : "+original);
		logger.info("encrypted : "+encrypted);
		logger.info("matched : "+passwordEncoder.matches(original,encrypted));
		logger.info("------------------------------------------");		
	}
	
}
