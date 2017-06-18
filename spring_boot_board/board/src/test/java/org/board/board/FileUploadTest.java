package org.board.board;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.board.util.StringUtils;
import org.imgscalr.Scalr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileUploadTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadTest.class);
	
	@Test
	public void thumbnailTest() throws Exception {
		String uploadPath = "c:\\temp";
		String path = "\\2017\\05\\06";
		String fileName = "1d575ce4-6bda-472e-990f-4165b5f1659f_Penguins.jpg";
		// 파일 -> 버퍼 이미지 read
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

		// 썸네일 버퍼 이미지
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

		// 원본 파일 : filename.file -> 썸네일 : s_filename.file
		String thumbnailName = StringUtils.combineStrings(uploadPath, path, File.separator, "s_", fileName);

		File saveFile = new File(thumbnailName);
		String formatName = StringUtils.getFileFormat(thumbnailName);

		ImageIO.write(destImg, formatName, saveFile);
		
		String ret = thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
		logger.info(ret);
		
		
	}

}
