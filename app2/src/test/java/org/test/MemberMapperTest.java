package org.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.test.domain.MemberVO;
import org.test.mapper.MemberMapper;

public class MemberMapperTest extends App2ApplicationTests {
	
	@Autowired
	private MemberMapper mapper;
	
//	@Test
//	public void testInsert() throws Exception {
//		MemberVO vo = new MemberVO();
//		vo.setUserid("user544");
//		vo.setUserpw("user544");
//		vo.setUsername("Billy Kang");
//		vo.setEmail("zerockcode@gmail.com");
//		mapper.create(vo);
//	}
	
	@Test
	public void testLogin() throws Exception {
		MemberVO vo = mapper.login("user543","user543");
		System.out.println(vo);
	}
	

}
