package com.mypet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/")
	public String home() {
		logger.info("MainController...home()");
		return "/index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void indexGET() {
		logger.info("MainController...indexGET()");
		//empty
	}
	
	
	@RequestMapping(value="/errors/404")
	public void error404() {
		logger.info("MainController...error404()");
		//empty
	}
	
	@RequestMapping(value="/errors/403")
	public void error403() {
		logger.info("MainController...error403()");
		//empty
	}
	
	

}
