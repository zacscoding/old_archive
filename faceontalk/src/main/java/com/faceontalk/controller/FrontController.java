package com.faceontalk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void getFront(HttpServletRequest request) {
	}
	

}
