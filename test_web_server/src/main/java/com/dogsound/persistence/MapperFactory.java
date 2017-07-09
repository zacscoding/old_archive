package com.dogsound.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import com.dogsound.config.GeneralConfig;

public class MapperFactory {	
	@Autowired
	OracleTestMapper oracleTestMapper;
	@Autowired
	SqlServerTestMapper sqlServerTestMapper;
	
	public TestMapper getMapper() {
		String profile = GeneralConfig.ACTIVE_DB_PROFILE;
		
		if( profile.startsWith("db.oracle") ) 
			return oracleTestMapper;
		else if(profile.startsWith("db.sqlserver"))
			 return sqlServerTestMapper;
		
		/*	TEMP CODE */
		// profile 별로 설정 해야 됨
		return oracleTestMapper;		
	}
	
}
