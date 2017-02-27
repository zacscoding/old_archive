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

import com.mypet.persistence.MemberDAO;
import com.mypet.service.MemberService;


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
	MemberService service;
	
	@Inject
	private PasswordEncoder passwordEncoder;
	
//	/* regist test*/
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void registMemberTest() {
//		try {			
//			MemberVO vo = new MemberVO();
//			vo.setUser_id("hiva5");
//			vo.setUser_password("hiva");
//			vo.setUser_name("hiva5");
//			vo.setUser_email("hiva4.com");
//			vo.setUser_phone("010");
//			vo.setPostcode_fk("123");
//			vo.setAddress("zzz");
//			dao.registerMember(vo);
//			// 인증 토큰 생성
//			String auth_token = EmailSenderUtil.createToken();
//			int amount = 60*60*24; //하루
//			Date auth_limit = new Date(System.currentTimeMillis()+(1000*amount));	
//			dao.registerAuthToken(vo.getUser_id(),auth_token,auth_limit);
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
////		for(int i=200;i<250;i++) {
////			try {			
////			} catch(Exception e) {
////				e.printStackTrace();
////			}		
////		}
//	}
	
	
//	/* encrypt test*/
//	@Test
//	public void encryptTest() {
//		String original = "aaa";
//		String encrypted = passwordEncoder.encode(original);
//		logger.info("------------------------------------------");
//		logger.info("original : "+original);
//		logger.info("encrypted : "+encrypted);
//		logger.info("matched : "+passwordEncoder.matches(original,encrypted));
//		logger.info("------------------------------------------");		
//	}
	
//	/*	select test	*/
//	@Test
//	public void listTest() {
//		SearchCriteria cri = new SearchCriteria();
//		try {			
//			logger.info("count : "+dao.listSearchCount(cri));
//			List<MemberVO> memberList = dao.listSearchCriteria(cri);
//			for(MemberVO vo : memberList) {
//				logger.info(vo.toString());
//			}			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
//	@Test
//	public void selectTest() {
//		//1.id
//		String id = "hiva1";		
//		//2.user_no
//		Integer user_no = 1;		
//		try {
//			MemberVO vo1 = dao.selectById(id);
//			MemberVO vo2 = dao.selectByNum(user_no);
//			
//			logger.info(vo1.toString());
//			logger.info(vo2.toString());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}			
//	}
	
//	@Transactional
//	@Test
//	public void modifyTest() {
//		try {
//			MemberVO vo = dao.selectById("hiva1");
//			logger.info("변경 전 : "+vo.toString());
//			
//			
//			vo.setAddress("hiva@city");
//			dao.modify(vo);
//			vo = dao.selectById("hiva1");
//			logger.info("변경 후 : "+vo.toString());
//		} catch(Exception e) {
//			e.printStackTrace();
//		}			
//	}
	
	@Test
	public void testScheduler() throws Exception {
		try {
			dao.removeExceedAuthMember();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
}













