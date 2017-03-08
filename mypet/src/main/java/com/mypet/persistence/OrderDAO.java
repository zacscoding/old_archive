package com.mypet.persistence;

import com.mypet.domain.OrderVO;

public interface OrderDAO {	
	
	public OrderVO createOrderByOneProduct(int product_no,int quantity);
	
}
