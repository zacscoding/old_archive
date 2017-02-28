package com.mypet.service;

import java.util.List;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;

public interface CategoryService {
	
	/*등록*/
	public void registerAnimal(String animal_name) throws Exception;
	public void registerProduct(CategoryVO vo) throws Exception;
	
	/*수정*/
//	public void modifyAnimal(AnimalVO vo) throws Exception;
	public void modifyProduct(CategoryVO vo) throws Exception;
	
	/*삭제*/
	public void removeAnimal(AnimalVO vo) throws Exception;
	public void removeProduct(CategoryVO vo) throws Exception;
	
	/*리스트*/
	public List<AnimalVO> listAnimal() throws Exception;
	public List<CategoryVO> listCategory() throws Exception;

}
