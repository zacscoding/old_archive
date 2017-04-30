package org.board.member;

import java.util.ArrayList;
import java.util.List;

import org.board.domain.MemberVO;
import org.board.persistence.MemberMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberDAOTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	@Autowired
	private MemberMapper memberMapper;	
	private List<MemberVO> users;
		
	@Before
	public void setUp() {
		users = new ArrayList<MemberVO>();		
		for(int i=1; i<3; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserId("hiva"+i);
			vo.setPassword("hiva"+i);
			vo.setEmail("hiva"+i+"@hiva.com");
			users.add(vo);
		}		
	}
	
	@Test
	public void testInsertAndSelect() throws Exception {
		memberMapper.removeAll();
		memberMapper.initAutoInc();
		
		for(MemberVO user : users) {
			memberMapper.regist(user);
		}		
		displayAll();		
	}
	
	@Test
	public void updateTest() throws Exception {
		for(MemberVO user : users) {
			user = memberMapper.selectById(user.getUserId());
			user.setEmail("modify@email.com");
			user.setPassword("modifyPassword");
			memberMapper.modify(user);
			memberMapper.modifyPassword(user);
		}
		displayAll();
	}
	
	
	private void displayAll() throws Exception {
		for(MemberVO user : users) {
			MemberVO vo = memberMapper.selectById(user.getUserId());
			if(vo != null)
				logger.info( vo.toString() );
		}
	}
	
	
	
	

}
