package org.board.controller;

import org.board.util.FileUtil;
import org.board.util.WebConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 관련 컨트롤러 클래스
 *  
 * @author zaccoding
 * @date 2017. 5. 3.
 */

@RestController
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	/**
	 * 파일 업로드 처리 메소드 <br>
	 * 
	 *  
	 * @author 	zaccoding
	 * @date 	2017. 5. 3.
	 * @param file 업로드 파일
	 * @return 저장 된 파일 이름
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(MultipartFile file) throws Exception {
		
		logger.info("uploaded original name = " + file.getOriginalFilename() );
		
		FileUtil.uploadFile(WebConstant.tempPath, file);
				
		//FileUtil.uploadFile(WebConstant.tempPath, file.getOriginalFilename(), file.getBytes() );
		
		return null;
	}
	
	
	
	
	
	

}
