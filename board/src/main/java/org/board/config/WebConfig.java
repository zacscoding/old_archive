package org.board.config;

import org.board.interceptor.AuthInterceptor;
import org.board.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Autowired
	private AuthInterceptor authInterceptor;
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(authInterceptor)
				.addPathPatterns("/");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/loginPOST");
		
	}	
}
