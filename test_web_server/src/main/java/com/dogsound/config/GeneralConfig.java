package com.dogsound.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	public static String getSqlQuery() {
		String activeProfile = GeneralConfig.ACTIVE_DB_PROFILE.substring(3);
		String fileName = null;
		if(activeProfile.startsWith("oracle")) {
			fileName = "oracle_table.sql";
		}
		else if(activeProfile.startsWith("sqlserver")) {
			fileName = "sqlserver_table.sql";
		}		
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {		
			br = new BufferedReader(new InputStreamReader(GeneralConfig.class.getResourceAsStream("/sql/"+fileName)));
			while(true) {
				String readLine = br.readLine();
				if(readLine == null)
					break;
				if(readLine.startsWith("--") || readLine.startsWith("#"))
					continue;
				if(readLine.endsWith(",") || readLine.endsWith("(") || readLine.endsWith(")"))
					readLine += "<br>";
				sb.append(readLine);
			}		
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(br!=null) try{br.close();}catch(IOException e){}
		}
		return sb.toString();
	}
	
}
