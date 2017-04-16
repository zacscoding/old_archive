package org.board.board;

import org.board.mapper.BoardMapper;
import org.board.member.MemberTest;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTest {
	private static final Logger logger = LoggerFactory.getLogger(MemberTest.class);
	
	@Autowired
	private BoardMapper boardMapper;
		
	
}
