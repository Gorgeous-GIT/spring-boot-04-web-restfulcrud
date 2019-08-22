package com.atguigu.springboot04webrestfulcrud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.atguigu.springboot04webrestfulcrud.component.LoginHandlerInterceptor;
import com.atguigu.springboot04webrestfulcrud.component.MyLocaleResolver;
//使用webMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc
@SuppressWarnings("deprecation")
@Configuration
public class MyMvcConfig  extends WebMvcConfigurerAdapter{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//浏览器发送 /atguigu 请求到 success
		registry.addViewController("/atguigu").setViewName("success");
		
	}
	//所有的webMvcConfigurerAdapter组件都会一起
	@Bean
	public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
		WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				// TODO Auto-generated method stub
				registry.addViewController("/").setViewName("login");
				registry.addViewController("/index.html").setViewName("login");
				registry.addViewController("/main.html").setViewName("dashboard");
			}
			
			//注册拦截器
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				// TODO Auto-generated method stub
				//静态资源；	*.css,*.js
				registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/webjars/**","/asserts/**");
				
			}
	 };
	 return adapter;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new MyLocaleResolver();
	}
}
