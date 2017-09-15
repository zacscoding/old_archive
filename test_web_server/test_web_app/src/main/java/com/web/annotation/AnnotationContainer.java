package com.web.annotation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.utils.ListUtil;

public class AnnotationContainer {
	private AnnotationContainer(){}
	
	private static Map<Class<?>, List<String>> fieldsMap = new HashMap<>();
	
	public static List<String> getColumns(Class<?> clazz) {
		List<String> list = fieldsMap.get(clazz);
		if(ListUtil.isValid(list)) {
			return list;
		}
		
		Field[] fields = clazz.getDeclaredFields();
		if(fields == null || fields.length==0 ) {
			return new ArrayList<String>(1);
		}
		
		
		list = new ArrayList<>();
		for(Field field : fields) {
			Columns annotation = field.getAnnotation(Columns.class);
			if( annotation != null ) {
				list.add(annotation.value());
			}
		}
		
		if(ListUtil.isValid(list)) {
			fieldsMap.put(clazz, list);
			return list;
		}
		
		return list;
	}	
}
