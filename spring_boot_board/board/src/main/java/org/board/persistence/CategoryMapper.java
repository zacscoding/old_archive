package org.board.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.board.domain.CategoryVO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

/**
 * 카테고리 관련 Mapper 인터페이스
 * 
 * @author zaccoding
 * @date 2017. 5. 20.
 */
public interface CategoryMapper {
	
	/**
	 * 카테고리 리스트를 가져오는 메소드
	 * 
	 * @author 	zaccoding
	 * @date 	2017. 5. 20.
	 * @return 카테고리 리스트
	 * @throws Exception
	 */
	@Cacheable(value="categoryCache")
	@Select("select * from tbl_category order by cate_no")
	public List<CategoryVO> findAll() throws Exception;
	
	
//	@CacheEvict("categoryCache")
//	public int updateCategory(CategoryVO vo) throws Exception;
//	
//	@CacheEvict("categoryCache")
//	public int save(CategoryVO vo) throws Exception;
	
	
	
	

}
