package org.board.member;

import java.util.Date;

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
public class LoginServiceTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceTest.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	LoginService loginService;
	
	
	@Test
	public void keepLoginTest() throws Exception {
		
		Integer userNo = Integer.valueOf(1);
		String sessionKey = "sessionKey";
		Date limit = new Date();
		
		loginService.keepLogin(userNo, sessionKey, limit);
		
	}

	

}
