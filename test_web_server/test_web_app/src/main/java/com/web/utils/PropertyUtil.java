package com.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class PropertyUtil {
	private PropertyUtil(){}
	/**
	 * 프로퍼티 값을 로드하는 메소드
	 * 
	 * @author zaccoding
	 * @date 2017. 8. 27.
	 * @param filepath ClassPathResource 기준의 파일 경로
	 * @return 프로퍼티 값
	 * @throws IOException
	 */
	public static Properties loadProperties(String filepath) throws IOException {
		Properties prop = new Properties();
		try(InputStream is =  new ClassPathResource(filepath).getInputStream()) {
			prop.load(is);
			return prop;
		}
	}
}
