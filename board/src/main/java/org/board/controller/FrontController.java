package org.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 프런트 컨트롤러
 * 
 * @author zaccoding
 * @date 2017. 4. 30.
 */

@Controller
public class FrontController {
	
	@RequestMapping("/")
	public String frontHandler() throws Exception {		
		return "index";		
	}
	
}
