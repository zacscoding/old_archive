package org.board.util;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class FileMoveTest {
	
	@Test
	public void moveTest() {
		String tempDir = WebConstants.UPLOAD_TEMP_PATH;
		String saveDir = WebConstants.UPLOAD_ATTACH_PATH;
		
		FileUtils.mkDirs(saveDir);
		
					
		
		
		
		
	}

}
