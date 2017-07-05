package com.dogsound.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class PropertiesUtils {
	
	public static Properties loadProperties(String filepath) {
		Properties prop = new Properties();		
		// try ( FileReader fr = new FileReader(filepath) ) {
		// try ( FileReader fr = new FileReader(new ClassPathResource(filepath).getFile()) ) { // war 배포 후 weblogic에 실행 시 에러
		InputStream is = null;
		try {
			is = new ClassPathResource(filepath).getInputStream();
			//prop.load(fr);
			prop.load(is);
			return prop;			
		} catch(IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(is != null ) try {is.close();}catch(Exception e){}
		}
	}
}
