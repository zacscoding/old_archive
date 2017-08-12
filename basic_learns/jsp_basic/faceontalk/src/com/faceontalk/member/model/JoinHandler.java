package com.faceontalk.member.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.faceontalk.member.action.DuplicateIdException;
import com.faceontalk.member.action.JoinAction;
import com.faceontalk.mvc.controller.CommandHandler;

public class JoinHandler implements CommandHandler {
	private static final String FORM_VIEW = "";
	private JoinAction joinAction = new JoinAction();
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
	private String processSubmit(HttpServletRequest request,HttpServletResponse response) {
		try {
			joinAction.join(request);
			return "/WEB-INF/view/joinSuccess.jsp";
		} catch(DuplicateIdException e) {
			request.setAttribute("message","동일한 아이디가 존재합니다.");
			return FORM_VIEW;
		}
	}
}
