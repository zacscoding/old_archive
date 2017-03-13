package com.mypet.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.mypet.domain.CartVO;
import com.mypet.persistence.CartDAO;

@Service("CartService")	//현재 클래스를 스프링 서비스 빈으로 등록시킴
public class CartServiceImpl implements CartService {
	
	@Inject
	private CartDAO dao;	// CartDAOImpl 인스턴스를 생성하여 주입시킴
	
	/*장바구니에 추가*/
	@Override
	public void cartAdd(CartVO vo) throws Exception {	//매개변수의 CartVO의 vo는 요청값
		
		CartVO search = dao.selectCart(vo);	//CartVO의 search는 기존에 저장된 값에서 Cart_seq 값을 찾아옴
		if(search!=null) {//1)기존 카트 존재
			//vo.setCart_seq(search.getCart_seq());	//찾아온 Cart_seq 값을 매개변수 vo에 저장			
			dao.cartModify(search.getCart_seq(),vo.getCount());	//vo를 dao의 cartModify에 담아서 보내준다. 즉, 기존에 있으면 수정해줌
		} else {//2)새로운 추가
			dao.cartAdd(vo);
		}
	}

	/*장바구니 삭제*/
	@Override
	public void cartDel(Integer cart_seq) throws Exception {
		dao.cartDel(cart_seq);
	}
	
	/*장바구니에서 수정*/
	@Override
	public void cartModify(Integer cart_seq,Integer delta) throws Exception {
		dao.cartModify(cart_seq,delta);
	}
	
	/*장바구니 리스트*/
	@Override
	public List<CartVO> cartList(Integer user_no_fk) throws Exception {
		return dao.cartList(user_no_fk);
	}
	
}
