package com.web.util;

import org.junit.Test;

import com.web.asm.AsmTestClass;
import com.web.utils.ClassDescUtil;

public class ClassDescUtilTest {
	
	@Test
	public void display() {
		ClassDescUtil.displayClass(AsmTestClass.class);		
	}
}
