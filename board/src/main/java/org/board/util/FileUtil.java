package org.board.util;

import java.io.File;
import java.util.Calendar;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 관련 유틸 클래스
 * 
 * @author zaccoding
 * @date 2017. 5. 3.
 */
public class FileUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
	
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
	public static String uploadFile(String uploadPath, MultipartFile file) throws Exception {
		// UUID를 이용한 고유 값 생성
		UUID uid = UUID.randomUUID();
		
		// UUID_originalName
		String savedName = uid.toString() + "_" + file.getOriginalFilename();
		logger.info(savedName);
		
		// 날짜별 폴더 생성		
		String savedPath = StringUtil.getDatePath(Calendar.getInstance(),"00");
		logger.info(savedPath);
		
		mkDirs(uploadPath + savedPath);
		
		File target = new File(uploadPath + savedPath, savedName);		
		file.transferTo(target);	
		return null;		
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
