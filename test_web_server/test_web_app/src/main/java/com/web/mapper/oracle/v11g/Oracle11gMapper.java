package com.web.mapper.oracle.v11g;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.web.mapper.DynamicQueryProvider;

public interface Oracle11gMapper {	
	@SelectProvider(type=DynamicQueryProvider.class, method="getQuery")
	public void selectQuery(String query);
	
	@InsertProvider(type=DynamicQueryProvider.class, method="getQuery")
	public int insertQuery(String query);
	
	@UpdateProvider(type=DynamicQueryProvider.class, method="getQuery")
	public int updateQuery(String query);
	
	@DeleteProvider(type=DynamicQueryProvider.class, method="getQuery")
	public int deleteQuery(String query);
	
	@Insert("insert into select_insert_test2(name,age) select name, age from select_insert_test")
	public int selectAndInsert();
	
	
	
	
	
	
	
	
}
