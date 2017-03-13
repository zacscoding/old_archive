package com.mypet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mypet.domain.OrderVO;
import com.mypet.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Inject
	OrderDAO dao;
	
	/*장바구니 리스트*/
//	@Override
//	public List<OrderVO> orderList(Integer user_seq_fk) throws Exception {
//		return dao.orderList(user_seq_fk);
//	}
	
	/**		주문 페이지 요청1) 상품 뷰에서 넘어온 경우	*/
	/*	
	 * 구매 페이지 에서 넘어온 경우
	 * 매개변수 : int : pno,qty
	 * Model : OrderVO , MemberVO
	 * return : 구매 페이지
	 */
	@Override
	public List<OrderVO> createOrderByOneProduct(int product_no_fk, int quantity) throws Exception {
		//상품 번호, 수량가지고 OrderVO를 만들고 반환
		return dao.createOrderByOneProduct(product_no_fk, quantity);
	}

	/**		주문 페이지 요청2) 카트에서 넘어온 경우		*/
	/*
	 * 카트 페이지에서 넘어온 경우
	 * 매개변수 : cart_no 배열
	 * Model : OrderVO, MemmberVO
	 * return : 구매페이지
	 * 
	 */
	@Override
	public List<OrderVO> createOrderByMultiProduct(int[] cartNumberArr) throws Exception {
		//cart_no 가지고 OrderVO를 만들고 반환
		return dao.createOrderByMultiProduct(cartNumberArr);
	}
}
