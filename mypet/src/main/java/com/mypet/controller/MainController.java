package com.mypet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value="/")
	public String home() {
		return "/index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void indexGET() {
		//empty
	}
	
	
	@RequestMapping(value="/errors/404")
	public void error404() {
		//empty
	}
	
	@RequestMapping(value="/errors/403")
	public void error403() {
		//empty
	}
	
	

}
