package org.board.service;

import java.util.List;

import org.board.domain.CategoryVO;
import org.board.persistence.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {	
	private final static Logger logger = LoggerFactory.getLogger(CategoryService.class);
	
	@Autowired
	CategoryMapper categoryMapper; 
	
	public List<CategoryVO> listAll() throws Exception {
		return categoryMapper.findAll();
	}
	
	
	
	
}
