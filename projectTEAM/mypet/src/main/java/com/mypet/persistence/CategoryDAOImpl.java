package com.mypet.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;


@Repository
public class CategoryDAOImpl implements CategoryDAO {
	private static final String namespace="com.mypet.mapper.CategoryMapper";
	@Inject
	SqlSession session;
	
	
	/*		등록		*/
	@Override
	public void registAnimal(String animal_name) throws Exception {
		session.insert(namespace+".registerAnimal",animal_name);
	}

	@Override
	public void registProduct(CategoryVO vo) throws Exception {
		session.insert(namespace+".registerProductCategory",vo);
	}

	/*		수정		*/
//	@Override
//	public void modifyAnimal(AnimalVO vo) throws Exception {
//		session.update(namespace+".",vo);		
//	}

	@Override
	public void modifyProduct(CategoryVO vo) throws Exception {
		session.update(namespace+".updateProductCategory",vo);		
	}

	/*		삭제		*/
	@Override
	public void removeAnimal(AnimalVO vo) throws Exception {
		session.delete(namespace+".removeAnimal",vo);	
	}

	@Override
	public void removeProduct(CategoryVO vo) throws Exception {
		session.delete(namespace+".removeProductCategory",vo);
	}
	
	/*		리스트		*/
	@Override
	public List<AnimalVO> listAnimal() throws Exception {
		return session.selectList(namespace+".listAnimal");
	}

	@Override
	public List<CategoryVO> listCategory() throws Exception {
		return session.selectList(namespace+".listCategory");
	}
	
	
	/*		 검색		*/
	@Override
	public AnimalVO selectAnimalByName(String animal_name) throws Exception {
		return session.selectOne(namespace+".selectAnimalByName", animal_name);
	}

	@Override
	public AnimalVO selectAnimalByNum(Integer animal_no) throws Exception {
		return session.selectOne(namespace+".selectAnimalByNum", animal_no);
	}

	@Override
	public CategoryVO selectCategoryByName(String animal_name) throws Exception {
		return session.selectOne(namespace+".selectCategoryByName", animal_name);	
	}

	@Override
	public CategoryVO selectCategoryByNum(Integer animal_no) throws Exception {
		return session.selectOne(namespace+".selectCategoryByNum", animal_no);			
	}	

}












