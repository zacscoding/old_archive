package com.mypet.controller.member;

import java.security.Principal;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.domain.MemberVO;
import com.mypet.dto.EmailAuthDTO;
import com.mypet.error.DuplicateIdException;
import com.mypet.error.ExceedPeriodException;
import com.mypet.security.SecurityUserVO;
import com.mypet.service.JoinService;
import com.mypet.service.MemberService;
import com.mypet.util.EmailSenderUtil;

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
	
	/**		login		*/
	@RequestMapping(value="/loginform", method=RequestMethod.GET)
	public String loginGET() {
		return "/user/loginForm";
	}
	
	/** 	join	*/
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public void joinGET() {
		//empty
	}
	
	@ResponseBody
	@RequestMapping(value="/check_id/{user_id}",method=RequestMethod.GET)
	public ResponseEntity<String> checkUserId(@PathVariable String user_id) throws Exception {
		logger.info("check user id.."+user_id);
		
		ResponseEntity<String> entity = null;
		String message = null;
		try {
			MemberVO vo = memberService.selectById(user_id);
			message = (vo==null) ? "POSSIBLE":"DUPLICATE";
			entity = new ResponseEntity<>(message,HttpStatus.OK);						
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}		
		return entity; 
	}
	
	
	@RequestMapping(value="/join",method = RequestMethod.POST)
	public String joinPOST(MemberVO vo, RedirectAttributes rttr) {
		logger.info("joinPOST.... POST"+vo.toString());
		String viewPage = null;
		try {
			joinService.registMember(vo);			
			viewPage = USER_JOIN_SUCCESS;
			String emailAddr = EmailSenderUtil.getEmailAddr(vo.getUser_email());
			rttr.addFlashAttribute("emailAddr",emailAddr);		
			logger.info("join result viewPage = "+viewPage);
		} catch(DuplicateIdException ex) {
			rttr.addFlashAttribute("msg","duplicated id");
			viewPage = USER_JOIN_FORM;
		} catch(Exception e) {
			rttr.addFlashAttribute("msg",e.getMessage());
			viewPage = USER_JOIN_FORM;
		}
		return "redirect:"+viewPage;		
	}
	
	/**		Join Success	*/
	@RequestMapping(value="/joinSuccess",method=RequestMethod.GET)
	public void joinSuccessGET() {
		//empty
	}
	
	/** 	authentication	*/
	// /confirm_verifination/{user_id}/auth_token으로 다시 하기
	@RequestMapping(value="/confirm_verification", method=RequestMethod.GET)
	public String confirmAuth(EmailAuthDTO dto,RedirectAttributes rttr) throws Exception {		
		logger.info("confirm_verifination...");
		logger.info("user_id : "+dto.getUser_id());
		logger.info("auth_token : "+dto.getAuth_token());
		String msg = null;
		try {
			joinService.confirmAuth(dto);
			msg = "Auth Success";
		} catch(ExceedPeriodException e) {
			msg = "Exceed Period...";
		}
		
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/index";
	}
	
	
	/** 	modify	*/
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET(Model model,Principal principal) throws Exception  {
		// 인증 유저 얻기 1) 
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String user_id = auth.getName(); // get logged in username

//		// 인증 유저 얻기 2)
//		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String user_id = user.getUsername();
					
		// 인증 유저 얻기 3)
//		String user_id = principal.getName();
		
		// 인증 유저 얻기 4)
		SecurityUserVO user = (SecurityUserVO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String user_id = user.getUsername();
		
		//user.getMemberVO()로 로그인 유저 정보를 얻을 수 있음
		logger.info("로그인 정보 : "+user.getMemberVO().toString());
		
		model.addAttribute("vo",memberService.selectById(user_id));
	}
	
	@RequestMapping(value="modify",method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo,RedirectAttributes rttr) throws Exception {		
		logger.info("/user/modify..POST : " + vo.toString());
		memberService.modifyUser(vo);
		
		rttr.addFlashAttribute("msg","success");
		return "redirect:/user/modify";		
	}	
	
	
	
	
	
	/* security authority test*/
	@RequestMapping("/main")
	public void memberMainGET() {
		//empty
	}
	
	
	
	
	
	
	
	
	
}
