package com.datn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.datn.interceptor.GlobalInterceptor;
import com.datn.interceptor.MyInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
	private GlobalInterceptor globalInterceptor;
	
	@Autowired
	private MyInterceptor interceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(globalInterceptor)
			.addPathPatterns("/index", "/shop-details/*", "/shop-grid/**", "/about", "/contact", "/cart", "/search");
//			.excludePathPatterns("/rest/**", "/admin/**", "/user/**");
		
		registry.addInterceptor(interceptor)
			.addPathPatterns("/admin/**", "/cart");
	}
}
