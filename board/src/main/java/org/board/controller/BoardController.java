package org.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/home")
	public String testHome(Model model) {
		model.addAttribute("msg","HOME!");
		return "home";
	}

}
