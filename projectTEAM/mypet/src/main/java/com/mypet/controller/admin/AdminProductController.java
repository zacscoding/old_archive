package com.mypet.controller.admin;

import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.domain.PageMaker;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.domain.ProductVO;
import com.mypet.service.CategoryService;
import com.mypet.service.ProductService;

/**
 * 상품 등록/수정/삭제 등등 
 */
@Controller
@RequestMapping("/admin/goods/*")
public class AdminProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);
	
	@Inject
	private ProductService service;
	@Inject
	private CategoryService cSevice;
	
	@RequestMapping(value="/productRegister", method = RequestMethod.GET)
	public void registerGET(ProductVO product, Model model) throws Exception{

		model.addAttribute("categoryList", cSevice.listCategory());
		logger.info("register get .....");
	}
	
	@RequestMapping(value="/productRegister", method = RequestMethod.POST)
	public String registPOST(ProductVO product, RedirectAttributes rttr) throws Exception{
		logger.info("regist post....");
		logger.info(product.toString());
		
		service.regist(product);
			
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		
		// return "/board/success";
		return "redirect:/admin/goods/productList?productType=a";
	}
	
	@RequestMapping(value="/productList", method = RequestMethod.GET)
	public void listPage(@ModelAttribute("cri") ProductSearchCriteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		model.addAttribute("productList", service.listSearchCriteria(cri).get("allProduct"));
		PageMaker pageMaker = new PageMaker();
		cri.setPerPageNum(10);
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute("pageMaker", pageMaker);
	}
	
	@RequestMapping(value="/productRead", method = RequestMethod.GET)
	public void read(@RequestParam("product_no") int product_no, @ModelAttribute("cri") ProductSearchCriteria cri,
			Model model) throws Exception{
		model.addAttribute(service.read(product_no));
	}
	
	@RequestMapping("/getFile/{pno_fk}")
	@ResponseBody
	public List<String> getFile(@PathVariable("pno_fk")Integer pno_fk)throws Exception{
		return service.getFile(pno_fk);
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("product_no") int product_no, ProductSearchCriteria cri, RedirectAttributes rttr) throws Exception{
		
		service.remove(product_no);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/admin/goods/productList";
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("product_no") int product_no, @ModelAttribute("cri") ProductSearchCriteria cri, Model model) throws Exception{
		model.addAttribute(service.read(product_no));
	}
	
	@RequestMapping(value="/modifyPage", method = RequestMethod.POST)
	public String modifyPagingPOST(ProductVO product, ProductSearchCriteria cri, RedirectAttributes rttr) throws Exception{
		service.modify(product);
		
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/admin/goods/productList";
	}
	
	
}
