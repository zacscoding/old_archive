package com.faceontalk.controller.member;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.faceontalk.domain.SearchCriteria;
import com.faceontalk.domain.member.EmailAuthVO;
import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.email.EmailSenderUtil;
import com.faceontalk.exception.DuplicateIdException;
import com.faceontalk.exception.ExceedPeriodException;
import com.faceontalk.service.feed.FeedService;
import com.faceontalk.service.member.MemberService;

@Controller
@RequestMapping("/accounts")
public class MemberController {	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	private MemberService memberService;
	@Inject
	private FeedService feedService;
	
	/** Join us (AJAX)  */	
	//POST
	@ResponseBody
	@RequestMapping(value="/join", method=RequestMethod.POST)	
	public ResponseEntity<Map<String,String>> registPOST(@RequestBody MemberVO vo) throws Exception {
		logger.info("/accounts/join (POST)");
		logger.info(vo.toString());		
		ResponseEntity<Map<String,String>> entity = null;				
		Map<String,String> retMap = new HashMap<>();
		try {
			memberService.regist(vo);
			retMap.put("result","SUCCESS");
			retMap.put("mail",EmailSenderUtil.getEmailAddr(vo.getUser_email()));
			entity = new ResponseEntity<>(retMap,HttpStatus.OK);
		} catch(DuplicateIdException ex) {
			retMap.put("result","DUPLICATED_ID");
			entity = new ResponseEntity<>(retMap,HttpStatus.OK);
		} catch(Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
		MemberVO anotherUser = memberService.searchById(vo.getUser_id());
		
		//user_name변경 
		if(anotherUser != null) {
			//예외처리(멤버 존재)
		}
		
		memberService.edit(vo);
		
		//1)페이지 이동이면 redirect
		
		//2)Ajax이면 다르게		
	}
	
	/**	Auth Email */
	@RequestMapping(value="/confirm_verification", method=RequestMethod.GET)
	public String confirmAuth(EmailAuthVO dto,RedirectAttributes rttr) throws Exception {		
		logger.info("confirm_verifination...");
				
		String msg = null;
		try {
			memberService.confirmAuth(dto);
			msg = "Auth Success";
		} catch(ExceedPeriodException e) {
			msg = "Exceed Period...";
		}		
		rttr.addFlashAttribute("msg",msg);
		return "redirect:/";
	}
	
	
	/**	Search Member	*/	
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String getUserDetail(String user_id, Model model) throws Exception {
		logger.info("getUserDetail..get user_id = "+user_id);
		
		MemberVO vo = memberService.searchById(user_id);
		
		//유저가 없으면 db에서 %연산으로 유저 리스트로 보냄
		if(vo == null) {
			return "redirect:/user/list?keyword="+user_id;
		}		
		model.addAttribute("memberVO",vo);
		model.addAttribute("feedList",feedService.listUsersFeedPics(vo.getUser_no()));
		return "/user/detail";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String getUserList(@ModelAttribute SearchCriteria cri) throws Exception {
		
		return null;
	}
	
	
	

}
