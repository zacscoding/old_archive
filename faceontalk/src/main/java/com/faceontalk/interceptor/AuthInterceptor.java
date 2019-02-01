package com.faceontalk.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.faceontalk.domain.member.MemberVO;
import com.faceontalk.service.member.LoginService;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Inject
	private LoginService loginService;
		
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		HttpSession session = request.getSession();
				
		if(session.getAttribute("login") == null) {
			logger.info("current user is not logined");
			//기존 요청 경로 저장
			saveDest(request);
			//쿠키 가져오기
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) { //로긴 쿠키 존재
				MemberVO vo = loginService.checkLoginBefore(loginCookie.getValue()); //쿠키 시간 검사
				if(vo != null) {
					logger.info("use previout Cookie for login");
					session.setAttribute("login",vo);
					return true;
				}
			}
			response.sendRedirect("/user/login");
			return false;
		} else {
			logger.info("current user is logined");
			return true;
		}
	}
	
	public void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?"+query;
		}		
		if(req.getMethod().equals("GET")) {
			logger.info("dest : "+(uri+query));
			req.getSession().setAttribute("dest",uri+query);
		}
	}
	

}
