package com.cos.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.service.AuthService;
import com.cos.blog.web.auth.dto.AuthJoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {
	
	private final AuthService authService;
	
		// form이 있는 페이지는 Form을 붙여줌
		// 주소 : 인증이 안 되었을 때 /user, /post, 인증이 되든 말든 무조건 온다 : /loginForm
		@GetMapping("/loginForm")
		public String loginForm() {
			return "auth/loginForm"; // ViewResolver 발동
		}
		
		@GetMapping("/joinForm")
		public String joinForm() {
			return "auth/joinForm"; 
		}
		
		@PostMapping("/join")
		public String join(AuthJoinReqDto authJoinReqDto) {
			authService.회원가입(authJoinReqDto.toEntity());
			return "redirect:/loginForm"; // loginForm 만들어 놓은 거 재활용
		}
}