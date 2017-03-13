package com.mypet.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.persistence.CategoryDAO;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Inject
	CategoryDAO dao;

	/*		등록		*/
	@Override
	public void registerAnimal(String animal_name) throws Exception {
		dao.registAnimal(animal_name);
	}

	@Override
	public void registerProduct(CategoryVO vo) throws Exception {
		dao.registProduct(vo);
	}
	
	/*		수정		*/
//	@Override
//	public void modifyAnimal(AnimalVO vo) throws Exception {
//		dao.modifyAnimal(vo);
//	}

	@Override
	public void modifyProduct(CategoryVO vo) throws Exception {
		dao.modifyProduct(vo);
	}

	/*		삭제		*/
	@Override
	public void removeAnimal(AnimalVO vo) throws Exception {
		dao.removeAnimal(vo);
	}

	@Override
	public void removeProduct(CategoryVO vo) throws Exception {
		dao.removeProduct(vo);		
	}

	/*		리스트		*/
	@Override
	public List<AnimalVO> listAnimal() throws Exception {
		return dao.listAnimal();
	}

	@Override
	public List<CategoryVO> listCategory() throws Exception {
		return dao.listCategory();
	}	
}
