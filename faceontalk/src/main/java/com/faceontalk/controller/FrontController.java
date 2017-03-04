package com.faceontalk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FrontController {
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getFront(HttpServletRequest request) {
		return "/feed/list";
	}
	
	/**
	 *  View Page test methods
	 */
	/*	test page	*/
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void testGET() throws Exception {
		
	}
	@RequestMapping(value="/test2",method=RequestMethod.GET)
	public void test2GET() throws Exception {
		
	}
	@RequestMapping(value="/test4",method=RequestMethod.GET)
	public void test4GET() throws Exception {
		
	}
	
	/*	test header	*/
	@RequestMapping("/header")
	public String test() {
		return "/include/header";
	}
	

}
