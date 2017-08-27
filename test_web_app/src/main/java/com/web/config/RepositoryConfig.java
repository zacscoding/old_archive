package com.web.config;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.web.utils.PropertyUtil;

@Configuration
public class RepositoryConfig {
	private static final Logger logger = LoggerFactory.getLogger(RepositoryConfig.class);
	private final String PREFIX = "db.";
	private Properties dbProps;
	
	@PostConstruct
	public void setUp() {
		try {
			dbProps = PropertyUtil.loadProperties("properties/db.properties");			
		}
		catch(IOException e) {
			logger.error("## [failed to load database properties]",e);
			System.exit(-1);
		}
	}
	///////////////////////////////////////////////////////
	// DataSource
	///////////////////////////////////////////////////////
	@Bean
	public DataSource oracle11gDataSource() {
		DriverManagerDataSource dataSourceManager = new DriverManagerDataSource();				
		final String prefix = PREFIX + "oracle.11g.";
		
		setDataSourceAttr(prefix, dataSourceManager);
		
		return dataSourceManager;
	}
	
	private void setDataSourceAttr(String prefix, DriverManagerDataSource dataSource) {
		dataSource.setDriverClassName((String)dbProps.get(prefix + "driver"));
		dataSource.setUrl((String)dbProps.get(prefix + "url"));
		dataSource.setUsername((String)dbProps.get(prefix + "username"));
		dataSource.setPassword((String)dbProps.get(prefix + "password"));
	}
	
	
	
	
}
