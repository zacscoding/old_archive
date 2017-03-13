package com.mypet.persistence;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.CategoryVO;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.domain.ProductVO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Inject
	private SqlSession session;
	
	private static String namespace = "com.mypet.mapper.ProductMapper";
	
	@Override
	public void create(ProductVO vo) throws SQLException {
		session.insert(namespace+".create", vo);
	}
	
	@Override
	public ProductVO read(Integer product_no) throws Exception {
		return session.selectOne(namespace+".read", product_no);
	}
	
	public void updateHitCnt(Integer product_no) throws Exception{
		session.update(namespace+".updateHitCnt", product_no);
	}

	@Override
	public void update(ProductVO vo) throws Exception {
		session.update(namespace+".update", vo);
	}

	@Override
	public void delete(Integer product_no) throws Exception {
		session.delete(namespace+".delete", product_no);
	}

	@Override
	public List<Object> listSearch(ProductSearchCriteria cri) throws Exception {
		return session.selectList(namespace+".listSearch", cri);
	}
	
	@Override
	public List<Object> bestSearch(ProductSearchCriteria cri) throws Exception {
		return session.selectList(namespace+".bestSearch", cri);
	}

	@Override
	public int listSearchCount(ProductSearchCriteria cri) throws Exception {
		return session.selectOne(namespace+".listSearchCount", cri);
	}

	@Override
	public void addFile(String fullName) throws Exception {
		session.insert(namespace+".addFile", fullName);
	}

	@Override
	public void addContent(String content) throws Exception {
		session.insert(namespace+".addContent", content);
	}

	@Override
	public List<String> getFile(Integer pno_fk) throws Exception {
		return session.selectList(namespace+".getFile", pno_fk);
	}

	@Override
	public void deleteFile(Integer pno_fk) throws Exception {
		session.delete(namespace+".deleteFile", pno_fk);
	}
	
	@Override
	public void deleteContent(Integer pno_fk) throws Exception {
		session.delete(namespace+".deleteContent", pno_fk);
	}

	@Override
	public void replaceFile(String fullName, Integer pno_fk) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("pno_fk", pno_fk);
		paramMap.put("fullName", fullName);
		
		session.insert(namespace+".replaceFile", paramMap);
	}

	@Override
	public List<CategoryVO> getCategory(Integer animal_no) throws Exception {
		return session.selectList(namespace+".getCategory", animal_no);
	}

	@Override
	public List<AnimalVO> getAnimal() throws Exception {
		return session.selectList(namespace+".getAnimal");
	}
	
	
}
