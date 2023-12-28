package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * http://localhost:8080/HighRailProject/mvc/hello
 */
@Controller
@RequestMapping("/hello")
public class HelloController {
    
	@GetMapping()
	@ResponseBody
	public String sayHello() {
		return "hello";
	}
}
