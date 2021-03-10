package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	// 403 뜸 : 인증 안 됨.
	@GetMapping("/user")
	public @ResponseBody String hello() { // @Controller + @ResponseBody = @RestController
		return "User";
	}
	
	// index.jsp를 찾게 됨.
	@GetMapping({"", "/"})
	public String home() {
		return "index"; // ViewResolver 발동
	}
}
