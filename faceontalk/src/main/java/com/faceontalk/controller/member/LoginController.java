package com.faceontalk.controller.member;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.dto.LoginDTO;
import com.faceontalk.service.member.LoginService;

@Controller
@RequestMapping("/user")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Inject
	private LoginService service;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET() {
		//empty
	}
	
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public String loginPOST(LoginDTO dto,HttpSession session,Model model) throws Exception {
		
		logger.info("LoginController.. loginPOST"+dto.toString());
		
		//check users id and password
		MemberVO vo = service.login(dto);		
		if(vo == null || !vo.isEnabled()) {//check email auth
			logger.info("memberVo.. is null");
			model.addAttribute("msg","Please confirm user id");
			return "/user/login";
		}		
		
		logger.info("memberVO is not null");
		model.addAttribute("memberVO",vo);
		
		//check keep login
		if(dto.isUseCookie()) {
			System.out.println("user Cookie");
			int amount = 60*60*24*7; //1week
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000*amount));	
			service.keepLogin(vo.getUser_no(), session.getId(),sessionLimit);			
		}
		
		return "/user/loginPost";
		
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request,
					HttpServletResponse response, HttpSession session) throws Exception {		
		
		Object obj = session.getAttribute("login");
		
		if(obj != null) {
			MemberVO vo = (MemberVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			
			if(loginCookie != null) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUser_no(),session.getId(),new Date());
			}
		}		
		return "redirect:/";
	}
	
	

}
