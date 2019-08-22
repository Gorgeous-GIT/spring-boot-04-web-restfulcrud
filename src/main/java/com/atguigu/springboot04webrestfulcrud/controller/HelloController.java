package com.atguigu.springboot04webrestfulcrud.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	
//	@RequestMapping({"/","index.html"})
//	public String index() {
//		return "index";
//	}
	
	@ResponseBody
	@RequestMapping("/hello")
	public String hello() {
		return "Hello world";
	}
	//查出一些数据，在页面展示
	@RequestMapping("/success")
	public String success(Map<String,Object> map) {
		map.put("hello", "<h1>你好</h1>");
		map.put("users", Arrays.asList("zhangsan","lisi","wangwu"));
		return "success";
	}
}