package com.mypet.service;

import java.util.List;
import java.util.Map;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.domain.ProductVO;

public interface ProductService {
	public void regist(ProductVO product)throws Exception;
	
	public ProductVO read(Integer product_no)throws Exception;
	
	public void updateHitCnt(Integer product_no)throws Exception;
	
	public void modify(ProductVO product)throws Exception;
	
	public void remove(Integer product_no)throws Exception;
	
	public Map<String, List<Object>> listSearchCriteria(ProductSearchCriteria cri) throws Exception;
	
	public int listSearchCount(ProductSearchCriteria cri)throws Exception;
	
	public List<Object> bestSearchCriteria(ProductSearchCriteria cri) throws Exception;
	
	public List<String> getFile(Integer pno_fk) throws Exception;
	
	public List<AnimalVO> getCategory() throws Exception;
}
