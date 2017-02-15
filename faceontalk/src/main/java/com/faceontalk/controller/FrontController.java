package com.faceontalk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {
	
	@RequestMapping(value="/kk",method=RequestMethod.GET)
	public String getFront(HttpServletRequest request) {
		
		String uri = request.getRequestURI();
		System.out.println("uri : "+uri);
		request.setAttribute("str",uri);
		
		return "test";
	}
	

}
