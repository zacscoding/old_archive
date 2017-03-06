package com.mypet.controller;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mypet.util.MediaUtils;

@RestController
public class UploadController {
	
	private static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name = "uploadPath")
	private String uploadPath;	
	
	/**		display in view page	*/
	@RequestMapping("/displayImage")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {		
		ResponseEntity<byte[]> entity = null;
		
		logger.info("ImageController.displayFile() fileName = "+fileName);
		
		String formatName = fileName.substring(fileName.lastIndexOf('.')+1);
		MediaType mediaType = MediaUtils.getMediaType(formatName);
		HttpHeaders headers = new HttpHeaders();
		
		try(InputStream in = new FileInputStream(uploadPath+fileName)) {
			headers.setContentType(mediaType); // MIME 타입을 이미지 타입으로 생성
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
}
