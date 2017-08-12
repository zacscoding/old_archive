package com.faceontalk.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter{
	@Override
	public void doFilter(ServletRequest req,ServletResponse res,FilterChain chain) throws IOException ,ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpSession session = request.getSession();
		if(session==null || session.getAttribute("authUser")==null) {
			
		} else {
			
		}
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		//empty
	}	
	@Override
	public void destroy() {
		//empty
	} 

}
