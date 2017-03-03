package com.mypet.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mypet.persistence.TestDAOImpl;

@Service
public class TestServiceImpl implements TestService {	
		
	@Inject
	TestDAOImpl dao;
	
	@Transactional
	public void transTest() throws Exception {
			dao.test1();
			dao.test2();
			//dao.test3();
		
	}	
}

