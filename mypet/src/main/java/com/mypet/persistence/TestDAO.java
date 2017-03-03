package com.mypet.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class TestDAO {	
	
	private static final String namespace="com.mypet.mapper.TestMapper";
	
	@Inject
	SqlSession session;
	
	
	public void test1() throws Exception {		
		session.insert(namespace+".test1");
	}
	
	public void test2() throws Exception {
		session.insert(namespace+".test2");
	}
	

}
