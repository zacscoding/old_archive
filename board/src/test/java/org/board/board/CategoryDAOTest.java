package org.board.board;

import java.util.List;

import org.board.domain.CategoryVO;
import org.board.persistence.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDAOTest {	
	private static final Logger logger = LoggerFactory.getLogger(CategoryDAOTest.class);
	
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Test	
	public void selectTest() throws Exception {
		List<CategoryVO> categoryList = categoryMapper.listAll();
		
		if( categoryList == null || categoryList.isEmpty() ) {
			logger.info("there is no category record");
			return;
		}
		
		for( CategoryVO vo : categoryList ) {
			logger.info( vo.toString() );
		}
		
	}
	
	
	
}
