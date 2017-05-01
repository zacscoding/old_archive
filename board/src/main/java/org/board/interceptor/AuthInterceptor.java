package org.board.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.board.domain.MemberVO;
import org.board.service.LoginService;
import org.board.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

/**
 * 권한 체크 인터셉터
 * 
 * @author zaccoding
 * @date 2017. 5. 1.
 */

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Autowired
	private LoginService loginService;

	/**
	 * 권한 체크 메소드
	 * 
	 * @return true : 로그인
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("AuthInterceptor .. preHandle..");
		
		HttpSession session = request.getSession();
		
		// 로그인 안 한 경우
		if( session.getAttribute(LoginUtil.LOGIN_SESSION_NAME) == null ) {
			logger.info("current user is not logined");
			
			Cookie loginCookie = WebUtils.getCookie( request, LoginUtil.LOGIN_COOKIE_NAME );
			//로그인 쿠키 존재
			if( loginCookie != null ) {				
				MemberVO vo = loginService.checkLoginBefore( loginCookie.getValue() );				
				// 로그인 쿠키가 유효한 경우
				if( vo != null ) {
					logger.info("use previous Cookie for login");
					session.setAttribute( LoginUtil.LOGIN_SESSION_NAME, vo );
					return true;
				}				
			}	
			response.sendRedirect("/login");
			return false;			
		}
		// 로그인 한 경우
		else {
			return true;
		}		
	}
	
	/**
	 * 권한 전 요청 경로를 세션에 저장하는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 1.
	 * @param request 요청 경로를 담고 있는 request
	 */
	public void saveUriDest(HttpServletRequest request) {
		String uri = request.getRequestURI();
		String query = request.getQueryString();
		
		if( query == null || query.equals("null") ) 
			query = "";
		else 
			query = "?" + query;
		
		if( request.getMethod().equalsIgnoreCase("GET") ) {
			logger.info("dest : " + (uri + query));
			request.getSession().setAttribute( LoginUtil.URI_DESTINATION  , (uri + query) );
		}		
	}
	
	
}
