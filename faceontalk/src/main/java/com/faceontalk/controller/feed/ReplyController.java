package com.faceontalk.controller.feed;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.faceontalk.domain.feed.ReplyVO;
import com.faceontalk.service.feed.ReplyService;

@RestController
@RequestMapping("/replies")
public class ReplyController {	
	@Inject
	private ReplyService replyService;
	private Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	
	/**		list all	*/
	@RequestMapping(value="/all/{feed_no_fk}", method=RequestMethod.GET)
	public ResponseEntity<String> list(@PathVariable("feed_no_fk") Integer feed_no_fk) {
		
		logger.info("/all/"+feed_no_fk);
		
		ResponseEntity<String> entity = null;		
		try {
			
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/**		register	*/	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<String> register(@RequestBody ReplyVO vo) {
		logger.info("/replies ...POST");
		ResponseEntity<String> entity = null;		
		try {
			replyService.register(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	/**		modify		*/
	@RequestMapping(value="/{rno}", method={RequestMethod.PUT,RequestMethod.PATCH})
	public ResponseEntity<String> modify(@PathVariable("rno") Integer rno,@RequestBody ReplyVO vo) {
		logger.info("/replies/{rno} ...PUT OR PATCH");
		ResponseEntity<String> entity = null;		
		try {
			vo.setRno(rno);
			replyService.modify(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);			
		}
		return entity;
	}
	
	
	/**		remove		*/
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable("rno") Integer rno) {
		logger.info("/replies/{rno} ...DELETE");
		ResponseEntity<String> entity = null;		
		try {			
			replyService.remove(rno);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);			
		}
		return entity;
	}
}
