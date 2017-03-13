package com.mypet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.domain.AnimalVO;
import com.mypet.domain.ProductSearchCriteria;
import com.mypet.domain.ProductVO;
import com.mypet.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Inject
	private ProductDAO dao;	
	
	@Override
	@Transactional
	public void regist(ProductVO product) throws Exception{
		
		String[] files = product.getFiles();
		
		String content = product.getContent();
		
		for(String file : files){
			System.out.println("=============== 확인용" + file);
		}
		
		product.setFilesMain(files[0]);
			
		dao.create(product);
		
		System.out.println("=====================" + files[1]);
		dao.addFile(files[1]);
	
		dao.addContent(content);
	}

	@Override
	public ProductVO read(Integer product_no) throws Exception {
		return dao.read(product_no);
	}
	
	@Override
	public void updateHitCnt(Integer product_no) throws Exception {
		dao.updateHitCnt(product_no);
	}

	@Transactional
	@Override
	public void modify(ProductVO product) throws Exception {
		dao.update(product);
		
		Integer pno_fk = product.getProduct_no();
	
		dao.deleteFile(pno_fk);
		
		String[] files = product.getFiles();
		
		if(files == null){return;}

		
		for(String fileName : files){
			dao.replaceFile(fileName, pno_fk);
		}
	}

	@Transactional
	@Override
	public void remove(Integer product_no) throws Exception {
		
		dao.deleteFile(product_no);
		dao.deleteContent(product_no);
		dao.delete(product_no);
	}

	@Override
	public Map<String, List<Object>> listSearchCriteria(ProductSearchCriteria cri) throws Exception {
		
		Map<String, List<Object>> productMap = new HashMap<String, List<Object>>();
		
		if(cri.getProductType() == null || cri.getProductType().equals("")){
			// 클라이언트 상품리스트 일반 상품, 베스트 상품 구분해서 얻어오기
			cri.setPerPageNum(12);
			cri.setProductType("n");
			productMap.put("product", dao.listSearch(cri));
			productMap.put("bestProduct", bestSearchCriteria(cri));
		}else if(cri.getProductType().equals("a")){
			// 어드민 상품리스트 전체 상품 목록 얻어오기
			cri.setPerPageNum(10);
			productMap.put("allProduct", dao.listSearch(cri));
		}
		return productMap;
	}
	
	public List<Object> bestSearchCriteria(ProductSearchCriteria cri) throws Exception{
		return dao.bestSearch(cri);
	}
	
	@Override
	public int listSearchCount(ProductSearchCriteria cri) throws Exception {
		return dao.listSearchCount(cri);
	}

	@Override
	public List<String> getFile(Integer pno_fk) throws Exception {
		return dao.getFile(pno_fk);
	}
	
	@Transactional
	@Override
	public List<AnimalVO> getCategory() throws Exception {
		List<AnimalVO> list = dao.getAnimal();
		
		for(AnimalVO vo : list) {
			vo.setCateList(dao.getCategory(vo.getAnimal_no()));
		}
		
		return list;
	}

}
