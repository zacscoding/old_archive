package com.web.controller;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.web.asm.AsmHookingTest;
import com.web.asm.AsmMethodTestClass;
import com.web.asm.AsmTestClass;
import com.web.asm.AsmTimerTestClass;

@Controller
@RequestMapping("/asm/**")
public class AsmController {
	private static final Logger logger = LoggerFactory.getLogger(AsmController.class);
	
	@RequestMapping(value="remove-method", method=RequestMethod.GET)
	public String removeMethod() {
		logger.info("## [display field]");
		Field[] fields = AsmTestClass.class.getDeclaredFields();
		for(Field field : fields) {
			logger.info("## [in AsmTestClass] field name : {}", field.getName());
		}
		
		logger.info("## [display methods]");
		Method[] methods = AsmTestClass.class.getDeclaredMethods();
		for(Method method : methods) {
			logger.info("## [in AsmTestClass] method name : {}", method.getName());
		}
		
		return "index";
	}
	
	@RequestMapping(value="visit-method", method=RequestMethod.GET)
	public String displayMethodVisitors() {
		AsmMethodTestClass domain = new AsmMethodTestClass();				
		return "index";
	}
	
	@RequestMapping(value="timer-method", method=RequestMethod.GET)
	public String timerMethodVisitors() {
		try {
			new AsmTimerTestClass().m();
			Field[] fields = AsmTimerTestClass.class.getDeclaredFields();
			for(Field field : fields) {
				if( field.getName().equals("timer") ) {
					System.out.println("## timer : " + field.get(null));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping(value="display-method", method=RequestMethod.GET)
	public String displayMethod() {
		new AsmHookingTest().service("hiva", 999);		
		return "index";
	}
	
	
	
	
	
	

}
