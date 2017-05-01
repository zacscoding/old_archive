package org.board.member;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.board.domain.MemberVO;
import org.board.dto.LoginDTO;
import org.board.dto.SearchPairDTO;
import org.board.service.LoginService;
import org.board.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceTest.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	LoginService loginService;
		
	List<MemberVO> users = new ArrayList<>();	
	
	//@Before
	public void setUp() {		
		for(int i=1; i<5; i++) {
			MemberVO vo = new MemberVO();
			vo.setUserId("test"+i);
			vo.setPassword("password"+i);
			vo.setEmail("mail"+i+"@mail.com");
			users.add(vo);
			logger.info(vo.toString());
		}
	}
	
	
	//@Test
	public void insertTest() throws Exception {		
		for(MemberVO user : users) {
			memberService.register(user);
		}
		
		SearchPairDTO dto = new SearchPairDTO();
		dto.setSearchType("id");
		for(int i=1; i<5; i++) {
			dto.setKeyword("test1");
			assertThat( memberService.existMember(dto) , is(true) );
		}
	}
	
	@Test
	public void selectTest() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setId("test1");
		dto.setPassword("test");
		if(loginService == null) {
			logger.info("login service null");
		}
		MemberVO vo = loginService.login(dto);
		if( vo == null ) 
			logger.info("vo is null");
		else
			logger.info(vo.toString());
	}
	
	
	
	

}
