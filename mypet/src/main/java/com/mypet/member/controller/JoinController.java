package com.mypet.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mypet.domain.MemberVO;
import com.mypet.error.DuplicateIdException;
import com.mypet.member.service.JoinService;

/**
 * 회원 가입 관련
 */
@Controller
@RequestMapping("/user/*")
public class JoinController {
	//private static final Logger logger = LoggerFactory.getLogger(JoinController.this);
	private static final String USER_JOIN_SUCCESS = "/user/joinSuccess";
	private static final String USER_JOIN_FORM = "/user/joinForm";	
	
	@Inject
	private JoinService joinService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public void joinGET() {
		//return USER_JOIN_FORM;		
	}	
	
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String joinPOST(@ModelAttribute("MemberVO") MemberVO vo,Model model) {
		try {
			joinService.registMember(vo);
			return USER_JOIN_SUCCESS;
		} catch(DuplicateIdException ex) {
			model.addAttribute("msg","duplicated id");
		} catch(Exception e) {
			model.addAttribute("msg","runtime exception");
		}	
		return USER_JOIN_FORM;		
	}
	
	@RequestMapping(value="/login")
	public void loginGET() {
		//empty
	}
	
	@RequestMapping("/member/main")
	public void memberMainGET() {
		//empty
	}
}
