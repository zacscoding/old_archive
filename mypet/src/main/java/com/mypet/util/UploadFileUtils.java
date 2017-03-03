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
	
	/**			*/
	public static String uploadFile(String uploadPath,String originalName,byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		
		String savedPath = calcPath(uploadPath);
		
		File target = new File(uploadPath + savedPath, savedName);
		
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);		
		String uploadedFileName = null;		
		if(MediaUtils.getMediaType(formatName) != null) { //이미지 타입이면
			uploadedFileName = makeThumbnail(uploadPath,savedPath,savedName); //썸네일 이미지 생성
		} else { //일반 파일이면
			uploadedFileName = makeIcon(uploadPath,savedPath,savedName); //생성된 파일 이름 반환
		}		
		return uploadedFileName;
	}
	
	private static String makeIcon(String uploadPath,String path,String fileName) throws Exception {
		String iconName = uploadPath + path + File.separator + fileName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar,'/');
	}
	
	// uploadPath/년/월/일 path얻기
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath,yearPath,monthPath,datePath);
		
		logger.info(datePath);
		
		return datePath;
	}
	
	// uploadPath/year/month/date 디렉터리 생성
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()) { //date폴더까지 있으면 리턴
			return;
		}
		
		for (String path : paths ) {
			File dirPath = new File(uploadPath+path);
			if(!dirPath.exists())
				dirPath.mkdir();
		}
	}
	
	private static String makeThumbnail (String uploadPath,String path,String fileName) throws Exception {
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath+path,fileName)); 
		//BufferedImage는 실제 이미지가 아닌 메모상의 이미지
		//=>원본 파일을 메모리상으로 로딩 & 정해진 크기에 맞게 작은 이미지 파일에 원본 이미지 복사
		
		BufferedImage destImg = Scalr.resize(sourceImg,Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT,100);
		//Scalr.Mode.FIT_TO_HEIGHT => 썸네일 이미지 파일의 높이를 뒤에 지정된 100px로 동일하게 만들어줌
		
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		//thumbnailName = 경로/s_파일이름
		//'s_'는 썸네일 이미지, 's_'를 제외하면 원본 이미지
		
		File newFile = new File(thumbnailName);
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1); //format이름
		
		ImageIO.write(destImg, formatName, newFile);//thumbnail로 복사
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
		// 브라우저에서 윈도으의 경로로 사용하는 '\' 문자가 정상적이 ㄴ경로로 인식되지 않기 때문에 /로 치환
	}
}



















