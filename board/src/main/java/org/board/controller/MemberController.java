package org.board.controller;

import org.board.domain.MemberVO;
import org.board.dto.SearchPairDTO;
import org.board.exception.DuplicateValueException;
import org.board.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users/**")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	
	/**
	 * 회원 가입 GET 요청 처리 메소드
	 * @date	: 2017. 4. 16.
	 * @return 가입 요청 폼
	 */
	@RequestMapping(value = "/register" , method=RequestMethod.GET)
	public String registerGET() throws Exception {
		logger.info("register GET");
		return "/users/register";		
	}
	
	/**
	 * 회원 가입 POST 요청 처리 메소드
	 * @date	: 2017. 4. 16.
	 * @param memberVO 회원 가입 요청 정보
	 * @return 가입 결과 뷰 페이지
	 */
	@RequestMapping(value = "/register" , method=RequestMethod.POST)
	public String registerPOST(@ModelAttribute MemberVO memberVO, Model model) throws Exception {
		logger.info("register POST");
		
		String viewName = null;
		
		try {
			memberService.register(memberVO);
			viewName = "/users/registerSuccess";
		} catch(DuplicateValueException e) {
			model.addAttribute("message", e.getMessage());
			model.addAttribute("memberVO",memberVO);
			return "/users/register";
		}
		
		return viewName;
	}
	
	/**
	 * ID/EMAIL 중복 확인 처리 메소드
	 * 
	 * @date	: 2017. 4. 16.
	 * @param 
	 * <ul>
	 *  <li>ID 중복 검사 	: searchType == "id"</li>
	 *  <li>EMAIL	검사 	: searchType == "email" </li>
	 * </ul>
	 * @return 가능여부 문자열
	 */
	@ResponseBody
	@RequestMapping(value="/confirmDuplicate", method=RequestMethod.POST)
	public ResponseEntity<String> confirmID(@RequestBody SearchPairDTO searchDTO) throws Exception {
		
		logger.info("confirmID.." + searchDTO);
		
		ResponseEntity<String> entity = null;				
		try {
			if( !memberService.checkExist(searchDTO) ) {
				entity = new ResponseEntity<>("사용이 가능합니다.",HttpStatus.OK);
			} else {
				entity = new ResponseEntity<>("사용이 불가능 합니다.",HttpStatus.OK);
			}			
		} catch(Exception ex) {
			ex.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}		
		return entity;
	}
	
	
	
	
	
	
	
	

}
