package com.mypet.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.CartVO;

@Repository
public class CartDAOImpl implements CartDAO{

	private static final String namespace = "com.mypet.mapper.CartMapper";	
	@Inject
	SqlSession session;

	/*장바구니 추가*/
	@Override
	public void cartAdd(CartVO vo) throws Exception {
		session.insert(namespace+".cartAdd", vo);
	}
	
	/*장바구니에서 삭제*/
	@Override
	public void cartDel(Integer cart_seq) throws Exception {
		session.delete(namespace+".cartDel", cart_seq);
	}
	
	/*장바구니 비우기(전체삭제)*/
	@Override
	public void cartDelAll(String id) throws Exception {
		session.delete(namespace+".cartDelAll", id);		
	}
	
	/*장바구니 수정*/
	@Override
	public void cartModify(Integer cart_seq,Integer delta) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("cart_seq",cart_seq);
		paramMap.put("delta", delta);
		session.update(namespace+".cartModify", paramMap);
	}
	
	/*장바구니 리스트*/
	@Override
	public List<CartVO> cartList(Integer user_seq_fk) throws Exception {
		List<CartVO> list=null;
		
		list = session.selectList(namespace+".cartList", user_seq_fk);	//입력 매개변수 userid, 태그 아이디는 cartList
		return list;
	}

	/*장바구니 찾기*/
	@Override
	public CartVO selectCart(CartVO vo) throws Exception {
		
		return session.selectOne(namespace+".selectCart", vo);
	}
	
}
