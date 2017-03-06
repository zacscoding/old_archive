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

@Controller
public class MainController {
	/*private static String carouselPath = "/resources/bootstrap/imgs/";
	private static List<CarouselVO> carouselList = new ArrayList<>();	
	static {		
		String[] carousels = {
				"0523_Q9000_MainKV.jpg",
				"20140820_UHDTV_Curved_KV.jpg",
				"GalaxyS5_LTEA_KV_0623.jpg",
				"M9000_Store_MainKV_140819.jpg",
				"",
				"MainKV_Galaxy_Tab_S.jpg",
		};
		String[] content = {
				"<h1>이렇게 또 한번 바람을 일으키다. <br> 삼성 스마트 에어컨 Q9000 </h1>",
				"<h1>곡면화질이 만든 압도적 몰입감 <br> 삼성 커브드 UHD TV </h1>",
				"<h1>LTE보다 3배 빠른 세계 최초 광대역 LTE-A폰 <br>Samsung GALAXY S5</h1>",
				"",
				"<h1 class=\"black\">S 아몰레드로 세상을 다시 보다 <br> Samsung GALAXY Tab S</h1>"
		};		
		//db로 변경하기		
		carousels = FileExtractorUtil.getFileNameLists(carouselPath,true);
		String imgPath = carouselPath.replace(File.separatorChar,'/') + "/";
		//System.out.println("img : "+imgPath);
		for(int i=0;i<carousels.length;i++) {
			CarouselVO vo = new CarouselVO();
			vo.setImage(imgPath+carousels[i]);
			vo.setContent(i+"번쨰");
			carouselList.add(vo);
		}
		
		for(int i=0;i<carousels.length;i++) {
			CarouselVO vo = new CarouselVO();
			vo.setImage(carouselPath+carousels[i]);
			vo.setContent(content[i]);
			carouselList.add(vo);
		}		
	}*/
	
	Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="/",method=RequestMethod.GET)	
	public String mainGET(Model model) {
		
		return "redirect:/index";
	}
	
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public void indexGET(Model model) {
		logger.info("MainController...indexGET()");
		
		String carouselPath = "/resources/bootstrap/imgs/";
		List<CarouselVO> carouselList = new ArrayList<>();	
			String[] carousels = {
					"0523_Q9000_MainKV.jpg",
					"20140820_UHDTV_Curved_KV.jpg",
					"GalaxyS5_LTEA_KV_0623.jpg",
					"M9000_Store_MainKV_140819.jpg",
					"MainKV_Galaxy_Tab_S.jpg",
			};
			String[] content = {
					"<h1>이렇게 또 한번 바람을 일으키다. <br> 삼성 스마트 에어컨 Q9000 </h1>",
					"<h1>곡면화질이 만든 압도적 몰입감 <br> 삼성 커브드 UHD TV </h1>",
					"<h1>LTE보다 3배 빠른 세계 최초 광대역 LTE-A폰 <br>Samsung GALAXY S5</h1>",
					"",
					"<h1 class=\"black\">S 아몰레드로 세상을 다시 보다 <br> Samsung GALAXY Tab S</h1>"
			};		
			for(int i=0;i<carousels.length;i++) {
				CarouselVO vo = new CarouselVO();
				vo.setImage(carouselPath+carousels[i]);
				vo.setContent(content[i]);
				carouselList.add(vo);
			}		
		
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
	
	
	/*	Transaction Test	 */
	/*@Inject
	TestService testService;	
	@RequestMapping(value="/test",method=RequestMethod.GET)
	public void test() throws Exception {
		logger.info("test.get()...");
	}	
	@RequestMapping(value="/test",method=RequestMethod.POST)
	public void testPOST(Model model) throws Exception {
		logger.info("test..post()..");
		testService.transTest();
		model.addAttribute("msg","success");		
	}*/
	
	/*	Review Modal Test	*/
	@RequestMapping(value="/reviewForm",method=RequestMethod.GET)
	public void reviewTest() throws Exception {		
	}
	
	@RequestMapping(value="/reviewRead",method=RequestMethod.GET)
	public void reviewViewTest() throws Exception {		
	}
	
	
	
	
	
	
	
}

