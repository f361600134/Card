package com.fatiny.cardlogin.controller;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatiny.cardlogin.util.CommonUtil;

@RestController
public class HelloWorldController {

	@RequestMapping("/index")
	public String index() {
		return "Hello World";
	}

	@RequestMapping("/param1")
	public String param1(Stu stu) {
		System.out.println(stu);
		return stu.toString();
	}

	@RequestMapping(value = "/param2")
	public String param2(@RequestParam Map<String, String> parameter) {
		System.out.println(parameter);
		return parameter.toString();
	}

	@RequestMapping("/param")
	public String param(HttpServletRequest request) {
		Map<String, String[]> map = request.getParameterMap();
		try {
			Stu stu = CommonUtil.convertMap(Stu.class, map);
			System.out.println("==>" + stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (String key : map.keySet()) {
			System.out.println(key + ":" + Arrays.toString(map.get(key)));
		}
		return "Hello World";
	}

}
