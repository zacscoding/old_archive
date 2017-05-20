package org.board.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 유틸 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 3.
 */
public class FileUtils {
	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
	
	/**
	 * 파일 저장 후 저장 된 경로 + 이름을 반환
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 3.
	 * @param uploadPath 저장 경로
	 * @param file 업로드 한 MultipartFile
	 * @return 저장 경로 + 파일 이름
	 * @throws Exception
	 */
	public static String uploadFile(String uploadPath, MultipartFile file, boolean makeThumbnail) throws Exception {
		// UUID를 이용한 고유 값 생성
		UUID uid = UUID.randomUUID();
		
		// UUID_originalName
		String savedName = StringUtils.combineStrings(uid.toString(), "_", file.getOriginalFilename());
		logger.info("savedName : " + savedName);
		
		// 날짜별 폴더 생성		
		String savedPath = StringUtils.getDatePath(Calendar.getInstance(),"00");
		logger.info("savedPath : " + savedPath);
		
		// 디렉터리 생성
		mkDirs(uploadPath + savedPath);
		
		// 파일 저장
		File target = new File(uploadPath + savedPath, savedName);		
		file.transferTo(target);
				
		// 확장자
		String formatName = StringUtils.getFileFormat(savedName);
		String uploadedFileName = null;

		if( makeThumbnail && MediaUtils.isMediaType(formatName) ) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);			
		} else {
			uploadedFileName = makeIcon(savedPath,savedName);
		}
		return uploadedFileName;		
	}
	
	/**
	 * URI요청을 위한 경로로 바꿔주는 메소드 <br>
	 * 
	 * File.separator 를 '/'로 변경
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 5.
	 * @param path 저장 경로
	 * @param fileName 파일 이름
	 * @return File.separator를 "/"로 변경한 문자열
	 * @throws Exception
	 */
	public static String makeIcon(String path, String fileName) {
		return StringUtils.combineStrings(path, File.separator, fileName).replace(File.separatorChar,'/');		
	}
	
	/**
	 * 썸네일 파일 생성 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 5.
	 * @param uploadPath 업로드 경로
	 * @param path 저장 경로
	 * @param fileName 저장 파일 이름
	 * @return path + "s_" + fileName  문자열 
	 * @throws Exception
	 */
	public static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception {
		// 파일 -> 버퍼 이미지 read
		BufferedImage sourceImg = ImageIO.read( new File( uploadPath + path , fileName) );
		
		// 썸네일 버퍼 이미지
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC,Scalr.Mode.FIT_TO_HEIGHT, 100);
		
		// 원본 파일 : filename.file -> 썸네일 : s_filename.file
		String thumbnailName = StringUtils.combineStrings(uploadPath, path, File.separator, "s_" , fileName);
		
		File saveFile = new File(thumbnailName);
		String formatName = StringUtils.getFileFormat(thumbnailName);
		
		ImageIO.write(destImg, formatName, saveFile);
		
		return thumbnailName.substring( uploadPath.length() ).replace(File.separatorChar,'/');		
	}
	
	
	/**
	 * pathname dir를 생성하는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 3.
	 * @param pathname 디렉터리를 생성할 pathname
	 */
	public static void mkDirs(String pathname) {
		File path = new File(pathname);
		if( !path.exists() ) {
			path.mkdirs();
		}
	}
	
	
	
}
