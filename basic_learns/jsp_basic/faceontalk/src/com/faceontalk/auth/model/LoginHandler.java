package com.faceontalk.auth.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faceontalk.auth.action.LoginAction;
import com.faceontalk.auth.action.LoginFailedException;
import com.faceontalk.member.action.User;
import com.faceontalk.mvc.controller.CommandHandler;

public class LoginHandler implements CommandHandler {
	private static final String FORM_VIEW = "/WEB-INF/view/loginForm.jsp";
	private LoginAction loginAction = new LoginAction();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req,res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req,res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}	
	private String processSubmit(HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			User user = loginAction.login(request.getParameter("input_id"),request.getParameter("password"));
			HttpSession session = request.getSession();
			session.setAttribute("authUser",user);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			return null;			
		} catch(LoginFailedException e) {
			return FORM_VIEW;
		}
	}
}
