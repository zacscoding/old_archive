package com.faceontalk.controller.member;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.service.member.MemberService;
import com.facontalk.errors.DuplicateIdException;
import com.facontalk.errors.ExceedPeriodException;

@Controller
@RequestMapping("/accounts")
public class MemberController {	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;	
	
	
	/** Join us (AJAX)  */	
	//POST
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST)	
	public ResponseEntity<String> registPOST(@RequestBody MemberVO vo) throws Exception {
		logger.info("/accounts/join (POST)");
		logger.info(vo.toString());		
		ResponseEntity<String> entity = null;
		
		try {			
			service.regist(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(DuplicateIdException ex) {
			entity = new ResponseEntity<String>("DUPLICATED_ID",HttpStatus.OK);
		} catch(Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
		}		
		return entity;		
	}	
		
	/** 	edit account	*/
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public void editGet() throws Exception {
		//1)Ajax이면 반환형 달라짐
		//2)아니면 MemberVO로 반환		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public void editGetPOST(MemberVO vo) throws Exception {
		MemberVO anotherUser = service.searchById(vo.getUser_id());
		
		//user_name변경 
		if(anotherUser != null) {
			//예외처리(멤버 존재)
		}
		
		service.edit(vo);
		
		//1)페이지 이동이면 redirect
		
		//2)Ajax이면 다르게		
	}
	
	/**	Auth Email */
	@RequestMapping(value="/confirm_verification", method=RequestMethod.GET)
	public String confirmAuth(EmailAuthVO dto,RedirectAttributes rttr) throws Exception {		
		logger.info("confirm_verifination...");
				
		String msg = null;
		try {
			service.confirmAuth(dto);
			msg = "Auth Success";
		} catch(ExceedPeriodException e) {
			msg = "Exceed Period...";
		}		
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/";
	}
	
	
	

}
