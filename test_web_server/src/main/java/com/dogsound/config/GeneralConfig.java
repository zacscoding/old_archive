package com.dogsound.config;

import java.util.Arrays;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class GeneralConfig implements InitializingBean {
	public static String[] ACTIVE_PROFILES;
	public static String ACTIVE_DB_PROFILE;
	
	@Autowired 
	private Environment env;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		ACTIVE_PROFILES = env.getActiveProfiles();
		System.out.println("GeneralConfig.afterPropertiesSet : " + Arrays.toString(ACTIVE_PROFILES));
		for( String activeProfile : ACTIVE_PROFILES ) {
			if( !activeProfile.startsWith("db.") ) 
				continue;
			
			ACTIVE_DB_PROFILE = activeProfile;
		}
	}
}
