package com.mypet.service;

import java.util.List;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.Criteria;
import com.mypet.domain.ProductVO;
import com.mypet.domain.SearchCriteria;

public interface ProductService {
	public void regist(ProductVO product)throws Exception;
	
	public ProductVO read(Integer product_no)throws Exception;
	
	public void modify(ProductVO product)throws Exception;
	
	public void remove(Integer product_no)throws Exception;
	
	public List<ProductVO> listCriteria(Criteria cri)throws Exception;
	
	public int listCountCriteria(Criteria cri)throws Exception;
	
	public List<ProductVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri)throws Exception;
	
	public List<String> getFile(Integer pno_fk) throws Exception;
	
	public List<AnimalVO> getCategory() throws Exception;
}
