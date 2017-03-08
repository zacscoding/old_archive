package com.mypet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mypet.domain.Criteria;
import com.mypet.domain.PageMaker;
import com.mypet.domain.ReviewVO;
import com.mypet.service.ReviewService;
import com.mypet.util.LoginUserInfoUtil;



@RestController
@RequestMapping("/reviews/*")
public class ReviewController {
	Logger logger = LoggerFactory.getLogger(ReviewController.class);
	
	@Inject
	ReviewService service;
	
	
	/**
	 * 
	 * Ajax
	 * 
	 */
	//상품별 리뷰 리스트 		
	//리뷰 쓰기
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> registPOST(@RequestBody ReviewVO vo) throws Exception {
		
		logger.info("regist post ...........");
		logger.info(vo.toString());
		
		vo.setReview_writer(LoginUserInfoUtil.getUserId()); //로그인 사용자 아이디를 등록
		
		ResponseEntity<String> entity = null;
		try {			
			service.registerReview(vo);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;		
	}
	
	//리뷰 리스트
	@RequestMapping(value="/{product_no_fk}/{page}",method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>> listReviewPage(
			@PathVariable("product_no_fk") Integer product_no_fk,
			@PathVariable("page") Integer page )throws Exception {
		
		ResponseEntity<Map<String,Object>> entity = null;
		try {
			//뷰페이지에서 사용될 인스턴스를 담는 맵
			Map<String,Object> map = new HashMap<>();
			
			//Criteria 생성 및 설정
			Criteria cri = new Criteria();
			cri.setPage(page);						
			map.put("reviewList",service.listReviewPage(product_no_fk, cri));

			//PageMaker 생성 및 설정
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);			
			int reviewCount = service.count(product_no_fk);
			pageMaker.setTotalCount(reviewCount);			
			map.put("pageMaker",pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;		
	}
	
	//리뷰 삭제
	@RequestMapping(value = "/{review_no}", method = RequestMethod.DELETE)
	public ResponseEntity<String> remove(@PathVariable Integer review_no) throws Exception {
		
		logger.info("review delete..");
		
		ResponseEntity<String> entity = null;
		try {			
			service.deleteReview(review_no);
			entity = new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;		
	}
	
	
	
	
	/**
	 * 
	 * 페이지 이동
	 * 
	 */
	// 리뷰 리스트
	@RequestMapping(value = "/reviews/listAllReview", method = RequestMethod.GET)
	public void listAllReview(Model model) throws Exception {
		logger.info("show all list.........");
		model.addAttribute("list", service.listAllReview());
	}

	// 리뷰 조회(클릭시)
	@RequestMapping(value = "/reviews/readReview", method = RequestMethod.GET)
	public void readReview(@RequestParam("review_no") int review_no, Model model) throws Exception {
		model.addAttribute(service.readReview(review_no));

	}

	// 리뷰 삭제
	@RequestMapping(value = "/reviews/deleteReview", method = RequestMethod.POST)
	public String deleteReview(@RequestParam("review_no") int review_no, RedirectAttributes rttr) throws Exception {
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/reviews/listAllReview"; // 삭제성공시 리뷰 리스트 이동
	}

	// 리뷰 수정
	@RequestMapping(value = "/reviews/modifyReview", method = RequestMethod.GET)
	public void modifyGET(int review_no, Model model) throws Exception {
		model.addAttribute(service.readReview(review_no));
	}

	@RequestMapping(value = "/reviews/modifyReview", method = RequestMethod.POST)
	public String modifyPOST(ReviewVO vo, RedirectAttributes rttr) throws Exception {

		logger.info("mod post............");
		service.modifyReview(vo);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/reviews/listAllReview"; // 수정성공시 리뷰리스트 이동
	}

	// 리뷰 페이징처리
	@RequestMapping(value = "/reviews/listCriReview", method = RequestMethod.GET)
	public void listAllReview(Criteria cri, Model model) throws Exception {

		logger.info("show Page with Criteria............");

		model.addAttribute("list", service.listCriteriaReview(cri));

	}

	// 리뷰 페이징처리(하단부분)
	@RequestMapping(value = "/reviews/listPageReview", method = RequestMethod.GET)
	public void listPageReview(Criteria cri, Model model) throws Exception {
		logger.info(cri.toString());

		model.addAttribute("list", service.listCriteriaReview(cri));
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);

		pageMaker.setTotalCount(service.listCountCriteriaReview(cri));

		model.addAttribute("pageMaker", pageMaker);
	}

	@RequestMapping(value = "/reviews/readPageReview", method = RequestMethod.GET)
	public void readReview(@RequestParam("review_no") int review_no, @ModelAttribute("cri") Criteria cri, Model model)
			throws Exception {
		model.addAttribute(service.readReview(review_no));
	}

	@RequestMapping(value = "deletePageReview", method = RequestMethod.POST)
	public String deleteReview(@RequestParam("review_no") int review_no, Criteria cri, RedirectAttributes rttr)
			throws Exception {
		service.deleteReview(review_no);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/reviews/listPageReview";
	}

	@RequestMapping(value = "/reviews/modifyPageReview", method = RequestMethod.GET)
	public void modifyPagingGET(@RequestParam("review_no") int review_no, @ModelAttribute("cri") Criteria cri,
			Model model) throws Exception {
		model.addAttribute(service.readReview(review_no));
	}

	@RequestMapping(value = "/reviews/modifyPageReview", method = RequestMethod.POST)
	public String modifyPagingPOST(ReviewVO review, Criteria cri, RedirectAttributes rttr) throws Exception {
		service.modifyReview(review);

		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/reviews/listPageReview";
	}
		
}













