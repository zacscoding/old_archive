package com.mypet.admin.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.service.CategoryService;

/**
 * 카테고리 등록/수정/삭제 등등..
 */
@Controller
@RequestMapping("/admin/categories/*")
public class AdminCategoryController {	
	private static final Logger logger = LoggerFactory.getLogger(AdminCategoryController.class);
	
	@Inject
	CategoryService service;
		
	/*	카테 고리 등록*/	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public void registerGET(Model model) throws Exception {
		model.addAttribute("animalList",service.listAnimal());
	}
	
	@RequestMapping(value="/register/animal",method=RequestMethod.POST)
	public String registerAnimalPOST(AnimalVO vo,RedirectAttributes rttr) throws Exception {
		service.registerAnimal(vo);		
		rttr.addFlashAttribute("msg","SUCCESS");		
		return "redirect:/admin/categories/list";	
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerCategoryPOST(CategoryVO vo,RedirectAttributes rttr) throws Exception{
		logger.info("/register...POST : "+vo.toString());
		service.registerProduct(vo);
		rttr.addFlashAttribute("msg","SUCCESS");		
		return "redirect:/admin/categories/list";
	}
	
	
	/*	카테 고리 수정*/
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public void modifyGET() {		
		//empty
	}
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public void modifyPOST() {
		//empty
	}	
	
	/*	카테 고리 삭제*/	
	//json으로 바꾸기
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public void removePOST() {
		//empty
	}
	
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@Transactional
	public void listGET(Model model) throws Exception {		
		model.addAttribute("animalList",service.listAnimal());
		model.addAttribute("categoryList",service.listCategory());		
	}	
}



















