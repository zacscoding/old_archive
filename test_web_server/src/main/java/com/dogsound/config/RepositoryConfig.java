package com.dogsound.config;

import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.dogsound.persistence.MapperFactory;
import com.dogsound.util.PropertiesUtils;
import com.dogsound.util.StringUtils;

@Configuration
public class RepositoryConfig {	
	private static final Logger logger = LoggerFactory.getLogger(RepositoryConfig.class);
	
	@Bean
	public DataSource dataSource() {
		logger.info("dataSource()");
		String[] activeProfiles = GeneralConfig.ACTIVE_PROFILES;
		
		/*	TEMP CODE	*/
		System.out.println("profiles : " + Arrays.toString(activeProfiles));
		//Properties dbProperties = PropertiesUtils.loadProperties("src/main/resources/db.properties");
		
		Properties dbProperties = PropertiesUtils.loadProperties("db.properties");
		String prefix = null;
		
		for( String profile : activeProfiles ) {
			if( !profile.startsWith("db.") ) 
				continue;
			prefix = profile;
		}
		
		if( !StringUtils.isValid(prefix) ) {
			throw new RuntimeException("there is no active profiles");
		}
		
		DriverManagerDataSource dataSourceManager = new DriverManagerDataSource();		
		dataSourceManager.setDriverClassName((String)dbProperties.getProperty( prefix + ".driver" ));
		dataSourceManager.setUrl((String)dbProperties.get( prefix + ".url" ));
		dataSourceManager.setUsername((String)dbProperties.get(prefix + ".username" ) );
		dataSourceManager.setPassword((String)dbProperties.get(prefix + ".password") ); 
		
		return dataSourceManager;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() {
		logger.info("sqlSessionFactory()");
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource( dataSource() );
		factoryBean.setConfigLocation( new ClassPathResource("mybatis-config.xml") );
//		factoryBean.setMapperLocations(new ClassPathResource[] {
//				new ClassPathResource("classpath:mappers/**/*Mapper.xml")
//		});		
		try {
			factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**/*Mapper.xml"));
			return factoryBean.getObject();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() {
		logger.info("sqlSession()");
		return new SqlSessionTemplate( sqlSessionFactory() );
	}
	
	@Bean
	public MapperFactory mapperFactory() {
		return new MapperFactory();
	}
}
