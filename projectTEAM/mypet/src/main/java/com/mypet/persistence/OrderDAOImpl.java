package com.mypet.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO {
	
	private static final String namespace = "com.mypet.mapper.OrderMapper";
	@Inject
	SqlSession session;
	
/*주문서 리스트*/
//	@Override
//	public List<OrderVO> orderList(Integer user_seq_fk) throws Exception {
//		List<OrderVO> list=null;
//		
//		list = session.selectList(namespace+".orderList", user_seq_fk);
//		return list;
//	}
	
	/**		주문 페이지 요청1) 상품 뷰에서 넘어온 경우	*/
	@Override
	public List<OrderVO> createOrderByOneProduct(int product_no_fk, int quantity) {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("product_no",product_no_fk);
		paramMap.put("quantity",quantity);
		return session.selectList(namespace+".orderList",paramMap);
	}

	/**		주문 페이지 요청2) 카트에서 넘어온 경우		*/
	@Override
	public List<OrderVO> createOrderByMultiProduct(int[] cartNumberArr) {
		List<OrderVO> list=null;
		
		list = session.selectList(namespace+".orderList", cartNumberArr);	//입력 매개변수 userid, 태그 아이디는 cartList
		return list;
	}
	
}
