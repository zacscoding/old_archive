package com.faceontalk.member;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faceontalk.domain.member.FollowVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.persistence.member.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	private static Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Inject
	private MemberDAO dao;
	@Inject
	private BCryptPasswordEncoder passwordEncoder;	
	
//	/**	Create member test code	 */
//	@Test
//	public void testCreate() throws Exception {
//		for(int i=1;i<100;i++) {
//			MemberVO vo = new MemberVO();
//			vo.setUser_id("user_"+i);
//			vo.setUser_email("user@naver.com");
//			vo.setPassword(passwordEncoder.encode("test"));
//			vo.setPhone("010-111-1111");		
//			dao.regist(vo);
//		}		
//	}
//	
	
//	/** Encrypt password test	*/
//	@Test
//	public void encryptTest() {
//		String original = "rkarkarkarkarkarka";
//		String encrypted = passwordEncoder.encode(original);		
//		logger.info("original : "+original);
//		logger.info("encrypted : " +encrypted );
//		logger.info(original.length()+" : "+encrypted.length());
//		
//	}
	
//	/** Select by name test code	 */
//	@Test
//	public void testSelect() throws Exception {
//		//SearchByName
//		String value = "hiva1";
//		MemberVO vo = dao.searchById(value);
//		
////		//SearchById
////		Integer value = 5;		
////		MemberVO vo = dao.searchByNum(value);
//		
//		if(vo!=null) {
//			System.out.println(vo);
//			String password = "hivahivahivahiva";			
//			if(passwordEncoder.matches(password,vo.getPassword())) 
//				logger.info("match password");
//			else
//				logger.info("not match password");
//		} else {
//			System.out.println(value+"존재하지 않음");
//		}
//		
//		
//	}

	
//	/**	Update test code	*/
//	@Test
//	public void testUpdate() throws Exception {
//		MemberVO vo = new MemberVO();
//		vo.setUser_id(5);
//		vo.setUser_name("hiva(update)");
//		vo.setPassword("hivaupdate");
//		vo.setPhone("1234update");		
//		dao.update(vo);		
//	}
	
	
//	/** Regist follow test*/
//	@Test
//	public void testFollower() throws Exception {
//		FollowVO vo = new FollowVO();
//		vo.setFollower(1);
//		vo.setFollowing(2);
//		
//		//dao.registFollower(vo);
//		
//		dao.removeFollower(vo);
//		
//				
//	}
//	
//	/** Remove follow test*/
	
	@Test
	public void insert() throws Exception {
		List<MemberVO> list = dao.searchListById("user");
		FollowVO fVo = new FollowVO();
		for(MemberVO vo : list ) {			
			fVo.setFollower(vo.getUser_no());
			fVo.setFollowing(114);
			dao.registFollower(fVo);
		}
	}
	

}
