package org.board.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.board.util.FileUtils;
import org.board.util.MediaUtils;
import org.board.util.StringUtils;
import org.board.util.WebConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	private static final Logger logger = LoggerFactory.getLogger( UploadController.class );
	
	/**
	 * 게시글 내부 이미지 업로드 요청 처리 메소드
	 *  
	 * @author 	zaccoding
	 * @date 	2017. 5. 6.
	 * @param file 게시글 내부 이미지
	 * @return 저장 된 이미지 이름
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadImage", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadImage(MultipartFile file) throws Exception {
		logger.info("upload image .." + file.getOriginalFilename());
		
		return new ResponseEntity<>( FileUtils.uploadFile(WebConstants.UPLOAD_IMAGE_PATH, file , false), HttpStatus.CREATED );		
	}
	
	
	/**
	 * 게시글 첨부파일 업로드 요청 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 6.
	 * @param file 게시글 첨부파일
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/uploadAttach", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAttach(MultipartFile file) throws Exception {
		
		logger.info("upload image .." + file.getOriginalFilename());
		
		return new ResponseEntity<>( FileUtils.uploadFile(WebConstants.UPLOAD_TEMP_PATH, file , true), HttpStatus.CREATED );		
	}
	
	
	
	/**
	 * 게시글 이미지 뷰 요청 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 6.
	 * @param fileName 저장 된 파일 이름
	 * @return 파일 데이터
	 * @throws Exception
	 */
	@RequestMapping("/displayImage")
	public ResponseEntity<byte[]> displayImage(String fileName) throws Exception {
		return getFileDatas( WebConstants.UPLOAD_IMAGE_PATH, fileName );
	}
	
	/**
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 6.
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/displayAttach")
	public ResponseEntity<byte[]> displayAttach(String type, String fileName) throws Exception {
		logger.info("displayAttach : " + type + "\n" + fileName);
		String uploadPath = null;
		if( type.equals("temp") ) {
			uploadPath = WebConstants.UPLOAD_TEMP_PATH;
		} else if (type.equals("uploaded")) {
			uploadPath = WebConstants.UPLOAD_ATTACH_PATH;
		} else {
			return null;
		}
		return getFileDatas( uploadPath, fileName );
	}
		
	/**
	 * 파일 -> byte[]로 변환하는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 5.
	 * @param uploadPath 저장 디렉터리
	 * @param fileName 저장 파일 이름
	 * @return 파일 데이터
	 * @throws Exception
	 */
	private ResponseEntity<byte[]> getFileDatas(String uploadPath, String fileName) throws Exception {
		ResponseEntity<byte[]> entity = null;
		
		try( InputStream in = new FileInputStream(uploadPath + fileName) ) {
			//파일 확장자 체크
			String formatName = StringUtils.getFileFormat(fileName);
			MediaType mType = MediaUtils.getMediaType(formatName);
			
			//MIME 타입 설정을 위한 헤더
			HttpHeaders headers = new HttpHeaders();
			
			if( mType != null ) { 
				// 이미지 이면
				headers.setContentType(mType);
			} else { 
				// 이미지가 아니면, 다운로드
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				
				//한글 인코딩
				String attachment = StringUtils.combineStrings("attachment;filename=\"",
						new String(fileName.getBytes("UTF-8"), "ISO-8859-1"), "\"" );
				headers.add("Content-Disposition", attachment);				
			}			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers,HttpStatus.CREATED);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	
	
	
	
	
	
	
	

}
