package com.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.annotation.AnnotationContainer;
import com.web.domain.Oracle11g;
import com.web.dto.DomainDTO;

@Controller
@RequestMapping("/crud-test/**")
public class BasicCrudController {
	private static final Logger logger = LoggerFactory.getLogger(BasicCrudController.class);
	
	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model) {		
		List<DomainDTO> dtoList = new ArrayList<>();
		
		// Oracle 11g Mapping
		DomainDTO oracleDTO = new DomainDTO();
		oracleDTO.setName("oracle.11g");
		oracleDTO.setColumns(AnnotationContainer.getColumns(Oracle11g.class));
		dtoList.add(oracleDTO);
		
		model.addAttribute("dtoList", dtoList);
		
		return "crudTest";
	}
	
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insert() {		
		
		return null;
	}
	
	
	
	
	
	
}
