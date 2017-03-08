package com.mypet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mypet.util.MediaUtils;
import com.mypet.util.UploadFileUtils;

/**
 * 
 * 서버 컴퓨터에 저장 된 이미지 파일을 
 * MIME 타입 설정해서 view
 *  
*/

@RestController
public class ImageController {
	
	private static Logger logger = LoggerFactory.getLogger(ImageController.class);
	
	private static final String uploadReviewPath = "c:\\mypet\\reviews";
	private static final String uploadProductPath = "c:\\mypet\\";

	
	/**		display in view page	*/
	@RequestMapping("/displayReviews")
	public ResponseEntity<byte[]> displayFile(String type,String fileName) throws Exception {		
		ResponseEntity<byte[]> entity = null;
		
		
		logger.info("ImageController.displayFile() fileName = "+fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf('.')+1);
		MediaType mediaType = MediaUtils.getMediaType(formatName);
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream(uploadReviewPath+fileName)) {
			headers.setContentType(mediaType); // MIME 타입을 이미지 타입으로 생성
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	/**		*/
	private ResponseEntity<byte[]> getFileData(String path,String fileName) throws Exception {
		
		ResponseEntity<byte[]> entity = null;
		
		String formatName = fileName.substring(fileName.lastIndexOf('.')+1);
		MediaType mediaType = MediaUtils.getMediaType(formatName);
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream(path+fileName)) {
			headers.setContentType(mediaType); // MIME 타입을 이미지 타입으로 생성
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	
	/**	리뷰 이미지 관련	*/	
	//리뷰 이미지 파일 드랍
	@RequestMapping(value="/reviews/uploadPic", method=RequestMethod.POST,  produces="test/plain;charset=UTF-8")
	public ResponseEntity<String> uploadReviewImage(MultipartFile file) throws Exception {
		
		ResponseEntity<String> entity = null;		
		//이미지 타입인지 체크
		String fileName = file.getOriginalFilename();
		logger.info("uploadReviewImage fileName : "+fileName);
		
		
		MediaType mediaType = MediaUtils.getMediaType(fileName.substring(fileName.lastIndexOf('.')+1));		
		if(mediaType == null) { //이미지가 아니면
			entity = new ResponseEntity<String>("notMatchedTypes",HttpStatus.OK);
		} else { //이미지 이면
			entity = new ResponseEntity<String>(
					UploadFileUtils.uploadFile(uploadReviewPath, file.getOriginalFilename(), file.getBytes()),
					HttpStatus.CREATED );
		}		
		return entity;
	}
	
	//리뷰 이미지 삭제(x버튼 클릭)
	@RequestMapping(value="/reviews/deleteImage")
	public ResponseEntity<String> deleteImage(String fileName) throws Exception {		
				
		File file = new File(uploadReviewPath, fileName.replace('/', File.separatorChar));
		
		if(file.exists()) { //파일이 존재하면
			file.delete(); //삭제
		}   
		
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}		
	
}
