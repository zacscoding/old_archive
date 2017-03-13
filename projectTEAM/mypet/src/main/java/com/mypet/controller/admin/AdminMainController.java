package com.mypet.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminMainController {
	
	@RequestMapping(value="/admin/main",method = RequestMethod.GET)
	public void mainGET() {
		//empty		
	}

}
