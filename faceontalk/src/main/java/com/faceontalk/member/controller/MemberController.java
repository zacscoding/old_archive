package com.faceontalk.member.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.service.MemberService;

@Controller
@RequestMapping("/accounts")
public class MemberController {	
	private static final Logger logger
		= LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService service;	
	/*
	 * Join us  
	 */	
	//GET
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public void registGET() throws Exception {
		//empty
	}
	//POST	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String registPOST(MemberVO vo, RedirectAttributes rttr) throws Exception {
		logger.info("/accounts/join (POST)");		
		service.regist(vo);
		
		rttr.addFlashAttribute("message","Successed to join us :D");
		
		return "redirect:/";
	}
	
	
	/*
	 * edit account
	 */
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public void editGet() throws Exception {
		//1)Ajax이면 반환형 달라짐
		//2)아니면 MemberVO로 반환		
	}
	
	@Transactional //?????필요??????
	@RequestMapping(value="/edit", method = RequestMethod.POST)
	public void editGetPOST(MemberVO vo) throws Exception {
		MemberVO anotherUser = service.searchByName(vo.getUser_name());
		
		//user_name변경 
		if(anotherUser != null) {
			//예외처리(멤버 존재)
		}
		
		service.edit(vo);
		
		//1)페이지 이동이면 redirect
		
		//2)Ajax이면 다르게		
	}
	
	
	
	

}
