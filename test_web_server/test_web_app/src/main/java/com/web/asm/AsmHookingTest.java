package com.web.asm;

public class AsmHookingTest {
	public void service(String name, Integer age) {		
		System.out.println("## [AsmHookingTest::service()] name : " + name + ", age : " + age);
	}
}
