package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.domain.ProductVO;

public interface ProductDAO {
	public void create(ProductVO vo)throws Exception;
	
	public ProductVO read(Integer product_no)throws Exception;
	
	public void updateHitCnt(Integer product_no)throws Exception;
	
	public void update(ProductVO vo)throws Exception;
	
	public void delete(Integer product_no)throws Exception;
	
	public List<Object> listSearch(ProductSearchCriteria cri)throws Exception;
	
	public int listSearchCount(ProductSearchCriteria cri)throws Exception;
	
	public List<Object> bestSearch(ProductSearchCriteria cri) throws Exception;
	
	public void addFile(String fullName) throws Exception;
	
	public void addContent(String content) throws Exception;
	
	public List<String> getFile(Integer pno_fk) throws Exception;
	
	public void deleteFile(Integer pno_fk) throws Exception;
	
	public void deleteContent(Integer pno_fk) throws Exception;
	
	public void replaceFile(String fullName, Integer pno_fk) throws Exception;
	
	public List<CategoryVO> getCategory(Integer animal_no) throws Exception;
	
	public List<AnimalVO> getAnimal() throws Exception;
}
