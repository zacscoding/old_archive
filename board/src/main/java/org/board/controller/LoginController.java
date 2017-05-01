package org.board.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.board.domain.MemberVO;
import org.board.dto.LoginDTO;
import org.board.service.LoginService;
import org.board.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

/**
 * 로그인 관련 컨트롤러 
 *  
 * @author zaccoding
 * @date 2017. 5. 1.
 */

@Controller
public class LoginController {
	
	private Logger logger = LoggerFactory.getLogger( LoginController.class );
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 로그인 GET 요청 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @return
	 */
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginGET() {
		logger.info( "login get..");		
		return "/users/login";
	}
	
	/**
	 * 로그인 POST 요청 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param dto 
	 * @param session
	 * @param model
	 * @param rttr
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/loginPOST", method=RequestMethod.POST)
	public String loginPOST(LoginDTO dto, HttpSession session, Model model,RedirectAttributes rttr) throws Exception {
		
		logger.info( "dto : "+dto.toString() );
		
		MemberVO loginVO = loginService.login(dto);
		
		//로긴 실패
		if( loginVO == null ) {
			logger.info("login failed");
			rttr.addFlashAttribute("message","Please confirm id & password");
			return "redirect:/login";
		}
		
		logger.info("login success");
		model.addAttribute("memberVO", loginVO);
		
		if( dto.isUseCookie() ) {
			int oneWeek = 60*60*24*7;
			Date sesseionLimit = new Date(System.currentTimeMillis() + (1000 * oneWeek) );
			loginService.keepLogin(loginVO.getUserNo(), session.getId(), sesseionLimit);
		}
		
		return "/users/loginPOST";
	}
	
	
	/**
	 * 로그 아웃 처리 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
		
		Object obj = session.getAttribute( LoginUtil.LOGIN_SESSION_NAME );
		
		if( obj != null && obj instanceof MemberVO ) {
			
			MemberVO vo = (MemberVO) obj;
			
			session.removeAttribute( LoginUtil.LOGIN_SESSION_NAME );
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie( request,  LoginUtil.LOGIN_COOKIE_NAME );
			
			if( loginCookie != null ) {
				loginCookie.setPath("/");
				loginCookie.setMaxAge(0);
				response.addCookie( loginCookie );
				loginService.keepLogin( vo.getUserNo() , session.getId() , new Date() );				
			}
		}
		
		return "redirect:/";
	}
	

}
