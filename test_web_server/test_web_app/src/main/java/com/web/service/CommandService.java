package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.mapper.oracle.v11g.Oracle11gMapper;

@Service
public class CommandService {
	@Autowired Oracle11gMapper oracle11gMapper;
	
	public void selectAndInsertTest() {
		// delete all
		String[] deleteQuery = new String[]{
				"delete from select_insert_test",
				"delete from select_insert_test2",
		};
		for(String query : deleteQuery) {
			oracle11gMapper.deleteQuery(query);
		}
		
		// insert select_insert_test table
		String[] insertQuery = new String[] {
				"insert into select_insert_test values(\'test1\', 1)"
				, "insert into select_insert_test values(\'test2\', 2)"
		};
		for(String query : insertQuery) {
			oracle11gMapper.insertQuery(query);
		}
		
		// select and insert
		oracle11gMapper.selectAndInsert();
	}
}
