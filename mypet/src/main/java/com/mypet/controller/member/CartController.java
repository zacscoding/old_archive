package com.mypet.controller.member;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mypet.domain.CartVO;
import com.mypet.domain.MemberVO;
import com.mypet.persistence.CartDAO;
import com.mypet.service.CartService;
import com.mypet.service.MemberService;
import com.mypet.util.LoginUserInfoUtil;

@Controller
public class CartController {
	private final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject		// Repository로 등록된 CartDAOImpl bean을 주입
	CartDAO dao;
	
	@Inject		// CartServiceImpl 주입
	CartService service;
	
	/*장바구니 추가*/
	@ResponseBody	//Ajax
	@RequestMapping(value="/cart/", method=RequestMethod.POST)
	public ResponseEntity<String> registerCart(@RequestBody CartVO vo) throws Exception {
		logger.info("cart_add Call... ");
		
		ResponseEntity<String> entity=null;
		try{
			service.cartAdd(vo);
			entity=new ResponseEntity<>("Success", HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/*장바구니 삭제*/
	@ResponseBody
	@RequestMapping("/cart/{cart_seq}")
	public ResponseEntity<String> removeCart(@PathVariable Integer cart_seq) throws Exception {
		logger.info("cart_del Call.... ");
		
		ResponseEntity<String> entity=null;
		try{
			service.cartDel(cart_seq);
			entity=new ResponseEntity<>("Success", HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/*장바구니 수정*/
	@ResponseBody
	@RequestMapping(value="/cart/{cart_seq}/{delta}", method=RequestMethod.POST)
	public ResponseEntity<String> updateCart(@PathVariable Integer cart_seq,
											@PathVariable Integer delta) throws Exception {		
		logger.info("cart_modify Call... ");
		
		ResponseEntity<String> entity=null;
		try{
			
			entity=new ResponseEntity<>("Success", HttpStatus.OK);
		}catch(Exception e){
			entity=new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
//	/*장바구니 리스트*/
//	@RequestMapping(value="/cart/cartList", method=RequestMethod.GET)
//	public void cart_list(@RequestBody CartVO vo, Model model) throws Exception {
//		logger.info("cart list...");
////		logger.info("cart_list Call... ");
////
////		Integer user_no = LoginUserInfoUtil.getUserNo();
////		
////		model.addAttribute("cartList", service.cartList(user_no));
//		
//	}
	
	
//	
//	//컨트롤러 맵핑
//	@RequestMapping("shop/cart_list")
//	public String cart_list(HttpSession session, Model model) throws Exception {
//		//세션변수에 저장된 사용자의 id
//		String userid=(String)session.getAttribute("id");
//		//장바구니 목록을 리스트에 저장
////		List<CartVO> list=cartDao.cartList("id");
//		List<CartVO> list=service.listCart("id");
//		//cart_list.jsp로 포워딩하여 출력시키기 위해서 모델에 자료 저장
//		model.addAttribute("list", list);
//		
//		//컨트롤러에서 합계와 배송료를 계산해서 처리하는 방법. 계산해서 view에서 값을 호출하는 방법
//			//다른 방법으로는 sql문으로 처리하는 것이 효율적임
//		int sum=0;
//		int fee=0;//배송료
//		for(CartVO vo : list){
//			sum += vo.getMoney();
//		}
//		fee = sum>=50000 ? 0 : 2500;
//		model.addAttribute("sum", sum);
//		model.addAttribute("fee", fee);
//		
//		return "shop/cart_list";	//cart_list.jsp로 포워딩
//	}
//	
	
	@RequestMapping("/cart/cart_del")
	public String cart_del(@RequestParam int idx) throws Exception {
		logger.info("cart_del Call.... ");
		// dao에 delete 요청
		dao.cartDel(idx);
		// 리스트로 이동
		return "redirect:cart/cart_list";
	}
	
	@RequestMapping("/cart/cart_del_all")
	public String cart_del_all(HttpSession session) throws Exception {//id가 세션에 있으므로 세션을 조회
		logger.info("cart_add_all Call.... ");
		String id=(String)session.getAttribute("id");
		// dao에 삭제 요청
		dao.cartDelAll(id);
		// 리스트로 이동
		return "redirect:/cart/cart_list";		
	}
	
}

