package com.faceontalk.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServerInfoUtil {	
	@Autowired
	private ServletContext servletContext;
	
	public String getContextPath() {
		return servletContext.getContextPath();
	}	
}
