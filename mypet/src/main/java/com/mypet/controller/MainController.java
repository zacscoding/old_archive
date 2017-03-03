package com.mypet.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mypet.domain.CarouselVO;
import com.mypet.util.FileExtractorUtil;

@Controller
public class MainController {
	private static String carouselPath = "c:\\mypet\\Carousels";
	private static List<CarouselVO> carouselList = new ArrayList<>();
	
	static {		
		String[] carousels;
		//db로 변경하기		
		carousels = FileExtractorUtil.getFileNameLists(carouselPath,true);
		for(int i=0;i<carousels.length;i++) {
			CarouselVO vo = new CarouselVO();
			vo.setImage(carouselPath+carousels);
			vo.setContent(i+"번쨰");
			carouselList.add(vo);
		}
	}
	
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/",method=RequestMethod.GET)	
	public String mainGET(Model model) {
		logger.info("MainController...home()");		
		logger.info(String.valueOf(carouselList.size()) );
		model.addAttribute("carouselSize",carouselList.size());
		model.addAttribute("carouselList", carouselList);
		return "/index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void indexGET(Model model) {
		logger.info("MainController...indexGET()");
		model.addAttribute("carouselSize",carouselList.size());
		model.addAttribute("carouselList", carouselList);
		
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
	
	@RequestMapping("/header")
	public String testHeader() {
		return "include/header";
	}
	
	

}
