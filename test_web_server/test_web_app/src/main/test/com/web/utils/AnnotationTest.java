package com.web.utils;

import java.util.List;

import org.junit.Test;

import com.web.annotation.AnnotationContainer;
import com.web.domain.Oracle11g;

public class AnnotationTest {
	@Test
	public void annotation() {
		List<String> columns = AnnotationContainer.getColumns(Oracle11g.class);
		for(String column : columns) {
			System.out.println(column);
		}
	}

}
