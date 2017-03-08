package com.mypet.service;

import java.util.List;

import com.mypet.domain.CartVO;

public interface CartService {
	/*장바구니 추가*/
	public void cartAdd(CartVO vo) throws Exception;
	
	/*장바구니에서 삭제*/
	public void cartDel(Integer cart_seq) throws Exception;
	
	/*장바구니 수정*/
	public void cartModify(Integer cart_seq,Integer delta) throws Exception;
	
	/*장바구니 리스트*/
	public List<CartVO> cartList(Integer user_seq_fk) throws Exception;

}
