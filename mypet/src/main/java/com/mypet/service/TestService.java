package com.mypet.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.persistence.TestDAO;

@Service
public class TestService {	
		
	@Inject
	TestDAO dao;
	
	@Transactional
	public void transTest() throws Exception {
			dao.test1();
			//dao.test2();
			dao.test3();
		
	}	
}

