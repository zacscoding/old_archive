package com.faceontalk.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {
		
	private static final String LOGIN = "login";
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/*	 intercept before execution of method /user/login 	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("LoginInterceptor.preHandle...");
		HttpSession session = request.getSession();
		if(session.getAttribute(LOGIN) != null) { //check prev data
			logger.info("clear before login data");
			session.invalidate(); //remove
		}
		return true;
	}
	
	/*	intercept after return*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("LoginInterceptor post...");
		
		HttpSession session = request.getSession();
		ModelMap modelMap = modelAndView.getModelMap();
		Object memberVO = modelMap.get("memberVO");		
		if(memberVO != null) {
			logger.info("new login success");
			
			session.setAttribute(LOGIN,memberVO);
			
			if(request.getParameter("useCookie") != null) { //자동 로그인 체크
				logger.info("remember me..");				
				Cookie loginCookie = new Cookie("loginCookie",session.getId());
				loginCookie.setPath("/");
				loginCookie.setMaxAge(60*60*24*7); //1week
				response.addCookie(loginCookie);				
			}
			//기존 경로 URI 처리
			Object dest = session.getAttribute("dest");
			if(dest != null) 
				logger.info("dest : "+dest.toString());				
			response.sendRedirect(dest != null ? (String)dest:"/feed/list");			
		}
	}
}
