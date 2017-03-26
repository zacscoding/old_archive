package org.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.test.domain.MemberVO;
import org.test.mapper.MemberMapper;

@RestController
public class SampleController {
	
	@Autowired
	private MemberMapper mapper;
	
	@RequestMapping("/hello")
	public String hello() throws Exception {
		
		return "Hellow Spring Boot World";
	}

}
