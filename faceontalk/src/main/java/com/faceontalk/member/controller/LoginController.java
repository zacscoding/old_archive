package com.faceontalk.member.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faceontalk.member.domain.MemberVO;
import com.faceontalk.member.dto.LoginDTO;
import com.faceontalk.member.service.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {	
	@Inject
	private LoginService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET() {
		//empty
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(LoginDTO dto,HttpSession session, Model model) throws Exception {
		//check user
		MemberVO vo = service.login(dto);
		
		if(vo == null || !vo.isEnabled()) //login failed			
			return;
		
		model.addAttribute("memberVO",vo);		
		
		//check keep login
		if(dto.isUserCookie()) {
			System.out.println("user Cookie");
			int amount = 60*60*24*7; //1week
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));	
			service.keepLogin(vo.getUser_no(), session.getId(),sessionLimit);			
		}		
		model.addAttribute("msg","success to login");		
	}
	
	
	
	

}
