package com.web.etc;

import java.lang.reflect.Field;

import com.web.domain.Oracle11g;
import org.junit.*;

public class DomainReflectionTest {
	
	@Test
	public void reflection() {
		Class<?> clazz = Oracle11g.class.getClass();
		Field[] fields = clazz.getFields();	
		for(Field field : fields) {
			System.out.println(field.getName());			
		}
	}
	
	

}
