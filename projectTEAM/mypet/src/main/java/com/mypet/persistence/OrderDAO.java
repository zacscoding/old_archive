package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.OrderVO;

public interface OrderDAO {	
	
/*주문서 리스트*/
	/**		주문 페이지 요청1) 상품 뷰에서 넘어온 경우	*/
	public List<OrderVO> createOrderByOneProduct(int product_no_fk,int quantity);
	
	/**		주문 페이지 요청2) 카트에서 넘어온 경우		*/
	public List<OrderVO> createOrderByMultiProduct(int[] cartNumberArr);
}
