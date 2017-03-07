package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.domain.Criteria;
import com.mypet.domain.ProductVO;
import com.mypet.domain.SearchCriteria;

public interface ProductDAO {
	public void create(ProductVO vo)throws Exception;
	
	public ProductVO read(Integer product_no)throws Exception;
	
	public void update(ProductVO vo)throws Exception;
	
	public void delete(Integer product_no)throws Exception;
	
	public List<ProductVO> listCriteria(Criteria cri) throws Exception;
	
	public int countPaging(Criteria cri)throws Exception;
	
	public List<ProductVO> listSearch(SearchCriteria cri)throws Exception;
	
	public int listSearchCount(SearchCriteria cri)throws Exception;
	
	public void addFile(String fullName) throws Exception;
	
	public void addContent(String content) throws Exception;
	
	public List<String> getFile(Integer pno_fk) throws Exception;
	
	public void deleteFile(Integer pno_fk) throws Exception;
	
	public void deleteContent(Integer pno_fk) throws Exception;
	
	public void replaceFile(String fullName, Integer pno_fk) throws Exception;
	
	public List<CategoryVO> getCategory(Integer animal_no) throws Exception;
	
	public List<AnimalVO> getAnimal() throws Exception;
}
