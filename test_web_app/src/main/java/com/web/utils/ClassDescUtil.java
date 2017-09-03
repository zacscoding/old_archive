package com.web.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassDescUtil {
	private static final Logger logger = LoggerFactory.getLogger(ClassDescUtil.class);
	
	public static void displayClass(Class<?> clazz) {
		String className = clazz.getName();
		
		logger.info("## [display fields]");
		Field[] fields = clazz.getDeclaredFields();
		if(fields == null || fields.length == 0) {
			logger.info("## [fields is empty]");
		}
		else {
			for(Field field : fields) {				
				logger.info("## [ in {} ] field name : {}, type : {}", new Object[]{className, field.getName(), field.getType().getName()});
			}
		}		
		logger.info("## [----------------------------------]");
		
		
		logger.info("## [display methods]");
		Method[] methods = clazz.getDeclaredMethods();
		if(methods == null || methods.length == 0) {
			logger.info("## [methods is empty]");
		}
		else {
			for(Method method : methods) {
				logger.info("## [ in {} ] method name : {} {} ", new Object[]{className, method.getReturnType().getName(),
						method.getName()});
			}			
		}		
		logger.info("## [----------------------------------]");
	}
}
