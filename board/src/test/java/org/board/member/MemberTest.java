package org.board.member;

import org.board.domain.MemberVO;
import org.board.mapper.MemberMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberTest.class);
	@Autowired
	private MemberMapper memberMapper;
	
	/**
	 * 멤버 등록 테스트 
	 * @date	: 2017. 4. 16.
	 * @throws Exception
	 */
	//@Test
	public void testInsert() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setUserId("hiva3");
		vo.setPassword("hiva3");
		vo.setEmail("hiva3@hiva.com");		
		memberMapper.regist(vo);
	}
	
	@Test
	public void testSelect() throws Exception {
		String userId = "hiva";
		MemberVO vo = memberMapper.selectById(userId);
		
		logger.info(vo.toString());
	}
	

}
