package com.mypet.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/*")
public class AdminMainController {
	
	@RequestMapping(value="/main",method = RequestMethod.GET)
	public void mainGET() {
		//empty		
	}

}
