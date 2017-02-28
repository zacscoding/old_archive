package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;

public interface CategoryDAO {
	
	/*카테고리 검색*/
	public AnimalVO selectAnimalByName(String animal_name)throws Exception;
	public AnimalVO selectAnimalByNum(Integer animal_no) throws Exception;
	
	public CategoryVO selectCategoryByName(String animal_name)throws Exception;
	public CategoryVO selectCategoryByNum(Integer animal_no) throws Exception;
	
	/*카테고리 리스트*/
	public List<AnimalVO> listAnimal() throws Exception;
	public List<CategoryVO> listCategory() throws Exception;
	
	/*카테고리 등록*/	
	public void registAnimal(String animal_name) throws Exception;
	public void registProduct(CategoryVO vo) throws Exception;
	
	/*카테고리 수정*/
	//public void modifyAnimal(AnimalVO vo) throws Exception;
	public void modifyProduct(CategoryVO vo) throws Exception;	
		
	/*카테고리 삭제*/
	public void removeAnimal(AnimalVO vo) throws Exception;
	public void removeProduct(CategoryVO vo) throws Exception;
	
	
	
	
}
