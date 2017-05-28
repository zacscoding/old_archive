package org.board.util;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
public class ContentsUtilsTest {
	
	private String contents;
	
	@Before
	public void setUp() {
		contents="<p><img src=\"/displayImage?fileName=/2017/05/28/6ac35f22-8529-4300-86cb-988479c4abb8_Hydrangeas.jpg\" style=\"height:550px; width:550px\" /><br />&nbsp;</p>";
	}
	
	@Test
	public void extractImagesTest() {
		System.out.println("--contents--");
		System.out.println(contents);
		System.out.println("----------------");
		
		
	}

}
