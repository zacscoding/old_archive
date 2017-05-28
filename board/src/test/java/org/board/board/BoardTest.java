package org.board.board;

import org.board.domain.BoardVO;
import org.board.member.MemberDAOTest;
import org.board.persistence.BoardMapper;
import org.board.service.BoardService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOTest.class);
	private static BoardVO vo;	
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private BoardService boardService;
	
	@Before
	public void setUp() {
		vo = new BoardVO();
		vo.setCateNo(1);
		vo.setContent("contentscontents");
		vo.setUserNo(1);
		String[] files = new String[1];
		files[0]="attach1";
		vo.setFiles(files);		
	}
	
	@Test
	public void insertAttachTest() {
		try {
			boardService.regist(vo);			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}		
	
}
