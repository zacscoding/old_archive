package com.dogsound.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dogsound.domain.DatasVO;

public interface OracleTestMapper extends TestMapper {
	/*===================
	 * INSERT
	 ====================*/
	public int insertOne(DatasVO vo);	
	public int insertMultiple(List<DatasVO> list);
	
	/*===================
	 * UPDATE
	 ====================*/
	@Update("update test_table set id = 1")
	public int updateAll();
		
	/*===================
	 * SELECT
	 ====================*/
	@Select("select * from test_table")
	public List<DatasVO> selectAll();	
	
	
	/*===================
	 * DELETE
	 ====================*/
	@Delete("delete from test_table")
	public int deleteAll();
}
