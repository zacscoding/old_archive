package com.mypet.controller.member;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mypet.domain.OrderVO;
import com.mypet.service.OrderService;
import com.mypet.util.LoginUserInfoUtil;

/*
https://escrow.gmarket.co.kr/ko/order?orderIdx=10EBBF294&itemno=875440849
*/

/**		주문 관련 컨트롤러	*/

@Controller
public class OrderController {
	private final String ORDER_PAGE = "/order/orderList";
	
	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject
	OrderService orderService;	
	
	/**		주문 페이지 요청1) 상품 뷰에서 넘어온 경우		*/
	/*	
	 * 구매 페이지 에서 넘어온 경우
	 * 매개변수 : int : pno,qty
	 * Model : OrderVO , MemberVO
	 * return : 구매 페이지
	 */
	@RequestMapping("/")
	public String createOrderByOneProduct(Integer product_no, Integer quantity, Model model) throws Exception {
		List<OrderVO> vo = orderService.createOrderByOneProduct(product_no, quantity);
		return createOrder(vo,model);
	}
	
	
	/**		주문 페이지 요청2) 카트에서 넘어온 경우		*/
	/*
	 * 카트 페이지에서 넘어온 경우
	 * 매개변수 : cart_no 배열
	 * Model : OrderVO, MemmberVO
	 * return : 구매페이지
	 * 
	 */
	@RequestMapping("/orderByCart")
	public String createOrderByMultiProduct(int[] cartNumberArr, Model model) throws Exception {
		List<OrderVO> vo = orderService.createOrderByMultiProduct(cartNumberArr);		
		return createOrder(vo,model);
	}
		
	//private method
	private String createOrder(List<OrderVO> vo,Model model) {		
		model.addAttribute("orderVO",vo);
		model.addAttribute("memberVO",LoginUserInfoUtil.getMemberVO());
		return "redirect:" + ORDER_PAGE;
	}
	
}

