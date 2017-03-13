package com.mypet.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mypet.domain.PageMaker;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.service.ProductService;

/**
 * 상품 등록/수정/삭제 등등 
 */
@Controller
@RequestMapping("/goods/*")
public class CliProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(CliProductController.class);
	
	@Inject
	private ProductService service;
	
	@RequestMapping(value="/cliProductList", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") ProductSearchCriteria cri, Model model) throws Exception{
	
		logger.info(cri.toString());
		
		getAnimal(model);
		
		Map<String, List<Object>> listMap = (Map<String, List<Object>>) service.listSearchCriteria(cri);
		
		model.addAttribute("cliProductList", listMap.get("product"));
		model.addAttribute("bestCliProductList", listMap.get("bestProduct"));
		
		PageMaker pageMaker = new PageMaker();
		cri.setProductType("n");
		cri.setPerPageNum(12);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("cateType", cri.getCateType());
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/cliBestProductList", method = RequestMethod.GET)
	public void bestListPage(@ModelAttribute("cri") ProductSearchCriteria cri, Model model) throws Exception{
		
		logger.info(cri.toString());
		
		getAnimal(model);
		
		model.addAttribute("cateType", cri.getCateType());
		model.addAttribute("bestCliProductList", service.bestSearchCriteria(cri));
	}
	
	@RequestMapping(value="/cliProductRead", method = RequestMethod.GET)
	public void read(@RequestParam("product_no") int product_no, @ModelAttribute("cri") ProductSearchCriteria cri,
			Model model) throws Exception{
		getAnimal(model);
		service.updateHitCnt(product_no);
		model.addAttribute(service.read(product_no));
	}
	
	@RequestMapping("/cliGetFile/{pno_fk}")
	@ResponseBody
	public List<String> getFile(@PathVariable("pno_fk")Integer pno_fk)throws Exception{
		return service.getFile(pno_fk);
	}
	
	@RequestMapping(value="/cliAnimalSearch", method = RequestMethod.GET)
	public String getAnimal(Model model) throws Exception{
		logger.info("animal number get....");
		
		model.addAttribute("categoryList", service.getCategory());
		
		return "redirect:/goods/cliProductList";
	}
	
}
