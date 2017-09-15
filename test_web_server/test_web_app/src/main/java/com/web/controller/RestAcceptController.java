package com.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.service.CommandService;

@RestController
@RequestMapping("/rest/**")
public class RestAcceptController {
	private static final Logger logger = LoggerFactory.getLogger(RestAcceptController.class);
	private static final String SELECT_AND_INSERT = "selectAndInsert";
	
	@Autowired
	CommandService commandService;
	
	@RequestMapping(value= "/command/{cmd}")
	public ResponseEntity<String> executeByCommand(@PathVariable("cmd") String cmd) {
		logger.info("## [request for command action] command : {}", cmd);
		try {
			if(cmd.equals(SELECT_AND_INSERT)) {
				selectAndInsertTest();
			}
			else {
				throw new RuntimeException();
			}
			
			return new ResponseEntity<>("SUCCESS", HttpStatus.BAD_REQUEST);
		}
		catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
	}
		
	private void selectAndInsertTest() {
		commandService.selectAndInsertTest();
	}
}
