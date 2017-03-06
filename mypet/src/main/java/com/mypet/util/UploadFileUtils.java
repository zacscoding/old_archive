package com.mypet.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	/*	thumbnail 생성 파일(경로포함)	 반환		*/
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		// 파일 고유 아이디
		UUID uid = UUID.randomUUID();

		// uid_파일이름
		String savedName = uid.toString() + "_" + originalName;

		// 저장 경로 (년/월/일/)
		String savedPath = calcPath(uploadPath);
		logger.info("UploadFileUtils.uploadFile() : " + uploadPath + savedPath);
		File target = new File(uploadPath + savedPath, savedName);

		FileCopyUtils.copy(fileData, target);

		return makeThumbnail(uploadPath, savedPath, savedName);		
	}
	
	/*	이미지 크기 조정	*/
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		File originalFile = new File(uploadPath + path, fileName);
		
		BufferedImage originalImage = ImageIO.read(originalFile);
				
		int imgwidth = Math.min(originalImage.getHeight(),  originalImage.getWidth());
		
		int imgheight = imgwidth;
			
		BufferedImage scaledImage = Scalr.crop(originalImage, (originalImage.getWidth() - imgwidth)/2, (originalImage.getHeight() - imgheight)/2, imgwidth, imgheight, null);
			
		BufferedImage resizedImage = Scalr.resize(scaledImage, 466,320, null);
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		
		File newFile = new File(thumbnailName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		ImageIO.write(resizedImage, formatName.toUpperCase(), newFile);
		
		originalFile.delete();
		
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/'); //브라우저 경로 인식 위해 '\' -> '/'
	}
  
	
	/*	/년/월/일 경로 계산 & 생성	*/
	private static String calcPath(String uploadPath) {
		
		Calendar cal = Calendar.getInstance();
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info(datePath);

		return datePath;
	}
  
	/*	/년/월/일 dir생성	*/
	private static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	
}



















