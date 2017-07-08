package com.dogsound.persistence;

import java.util.List;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.dogsound.domain.DatasVO;

public interface TestMapper {	
	/*===================
	 * Commons Custom Query
	 ====================*/	
	@SelectProvider(type=DynamicQueryProvider.class, method="checkQuery")
	public List<DatasVO> selectQuery(String query);
	
	@InsertProvider(type=DynamicQueryProvider.class, method="checkQuery")
	public int insertQuery(String query);
	
	@UpdateProvider(type=DynamicQueryProvider.class, method="checkQuery")
	public int updateQuery(String query);
	
	@DeleteProvider(type=DynamicQueryProvider.class, method="checkQuery")
	public int deleteQuery(String query);
	
	public int insertOne(DatasVO vo);	
	public int insertMultiple(List<DatasVO> list);	
	public int updateAll();
	public List<DatasVO> selectAll();	
	public int deleteAll();
	
	
	public int insertError();
}
