package org.board.category;

import static org.junit.Assert.*;

import java.util.List;

import org.board.domain.CategoryVO;
import org.board.persistence.CategoryMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryCacheTest {
	@Autowired CategoryMapper mapper;
	
	@Test
	public void cacheTest() throws Exception {
		List<CategoryVO> list = mapper.findAll();
		List<CategoryVO> anotherList = mapper.findAll();	
		assertTrue(isEqualsRefValue(list,anotherList));
	}
	
	private boolean isEqualsRefValue(List<?> list1, List<?> list2) {
		if( list1.size() != list2.size() ) 
			return false;
		
		for (Object obj1 : list1) {
			boolean existEqualDomain = false;

			for (Object obj2 : list2) {
				if (obj1 == obj2) {
					existEqualDomain = true;
					break;
				}
			}
			if (!existEqualDomain) {
				return false;
			}
		}
		return true;
	}
	
}
