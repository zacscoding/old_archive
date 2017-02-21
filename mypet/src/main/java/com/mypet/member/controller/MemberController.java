package com.mypet.member.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.domain.MemberVO;
import com.mypet.error.DuplicateIdException;
import com.mypet.member.service.JoinService;
import com.mypet.service.MemberService;

/**
 * 가입 회원 관련
 * (예외처리 어떻게 할지 결정하기)
 */
@Controller
@RequestMapping("/user/*")
public class MemberController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String USER_JOIN_SUCCESS = "/user/joinSuccess";
	private static final String USER_JOIN_FORM = "/user/join";	
	
	@Inject
	private JoinService joinService;
	@Inject
	private MemberService memberService;
	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public void joinGET() {
		//return USER_JOIN_FORM;		
	}		
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String joinPOST(@ModelAttribute("MemberVO") MemberVO vo, RedirectAttributes rttr) {
		logger.info("joinPOST.... POST");
		String viewPage = null;
		try {
			joinService.registMember(vo);			
			viewPage = USER_JOIN_SUCCESS;
		} catch(DuplicateIdException ex) {
			rttr.addFlashAttribute("msg","duplicated id");
			viewPage = USER_JOIN_FORM;
		} catch(Exception e) {
			rttr.addFlashAttribute("msg",e.getMessage());
			viewPage = USER_JOIN_FORM;
		}	
		logger.info("join result viewPage = "+viewPage);
		return "redirect:"+viewPage;		
	}
	
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginGET() {
		return "loginForm";
	}
	
	@RequestMapping(value="modify",method=RequestMethod.GET)
	public void modifyGET(@RequestParam ("user_no") Integer user_no,Model model) throws Exception  {
		
		model.addAttribute("vo",memberService.selectByNum(user_no));		
	}
	
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo) throws Exception {
		
		logger.info("/user/modify..POST : " + vo.toString());
		memberService.modifyUser(vo);
		
		return "redirect:/user/modify?user_no="+vo.getUser_id(); //다시		
	}
	
	
	/* security authority test*/
	@RequestMapping("/main")
	public void memberMainGET() {
		//empty
	}
	
	
	
	
	
	
	
	
	
}
