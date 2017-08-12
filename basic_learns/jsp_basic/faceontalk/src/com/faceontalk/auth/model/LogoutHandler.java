package com.faceontalk.auth.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.faceontalk.mvc.controller.CommandHandler;

public class LogoutHandler implements CommandHandler {
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		if(session != null)
			session.invalidate();
		res.sendRedirect(req.getContextPath()+"/index.jsp");
		return null;
	}
}
