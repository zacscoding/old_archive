package com.faceontalk.member;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.faceontalk.member.domain.FollowVO;
import com.faceontalk.member.persistence.MemberDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {
	
	@Inject
	private MemberDAO dao;
	
//	/**	Create member test code	 */
//	@Test
//	public void testCreate() throws Exception {
//		MemberVO vo = new MemberVO();
//		vo.setUser_name("hiva1");
//		vo.setUser_email("hiva1@naver.com");
//		vo.setPassword("hiva");
//		vo.setPhone("1234");
//		
//		dao.regist(vo);
//	}
	
//	/** Select by name test code	 */
//	@Test
//	public void testSelect() throws Exception {
////		//SearchByName
////		String value = "hiva1";
////		MemberVO vo = dao.searchByName(value);
//		
//		//SearchById
//		Integer value = 5;		
//		MemberVO vo = dao.searchById(value);
//		
//		if(vo!=null)
//			System.out.println(vo);
//		else
//			System.out.println(value+"존재하지 않음");
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
	
	
	/** Regist follow test*/
	@Test
	public void testFollower() throws Exception {
		FollowVO vo = new FollowVO();
		vo.setFollower(1);
		vo.setFollowing(2);
		
		//dao.registFollower(vo);
		
		dao.removeFollower(vo);
		
				
	}
	
	/** Remove follow test*/
	
	
	
	

}
