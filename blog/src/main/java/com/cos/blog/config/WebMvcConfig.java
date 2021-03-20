package com.cos.blog.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cos.blog.domain.user.User;
import com.cos.blog.handler.MyAuthenticationException;

@Configuration
//개발자들은 View 컨트롤, Model 컨트롤이 가능, Controller는 불가능
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HandlerInterceptor() {
			
			// 컨트롤러 실행 직전에 동작
			// 반환 값이 true일 경우 정상적 진행.
			// 반환 값이 false이면 컨트롤러 진입 안 함. 
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
					throws Exception {
				// 스프링 안에 들어와야지 throw 가능
				// 바깥 필터에서는 제어가 안 됨.
				HttpSession session = request.getSession();
				System.out.println("session : " + session);
				User principal = (User)session.getAttribute("principal");
				System.out.println("session.getAttribute(\"principal\") : " + session.getAttribute("principal"));
				System.out.println("(User)session.getAttribute(\"principal\") : " + (User)session.getAttribute("principal"));
				String reqMethod = request.getMethod();
				System.out.println("reqMethod : " + reqMethod);
				System.out.println("principal : " + principal);
				
				if(principal == null && (reqMethod.equals("PUT") ||  reqMethod.equals("POST") || reqMethod.equals("DELETE") )) {
					throw new MyAuthenticationException();
				} else {
					return true;
				}
			}
			// /user, /post 일 때 동작
		}).addPathPatterns("/user/*").addPathPatterns("/post/*");
	}
}