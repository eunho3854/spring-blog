package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration // 설정, IoC 등록
@EnableWebSecurity
// WebSecurityConfigurerAdapter 어댑터가 붙었다는 건 함수를 걸러준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// IoC 등록만 하면 AuthenticationManager가 BCrypt로 자동 검증해줌.
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		
		// "/user", "/post" 빼고 다 열어준다는 뜻
		http.authorizeRequests()
			.antMatchers("/user", "/post").access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')") // 유저나 어드민 권한이 있어야지만 가능. ROLE_는 강제성이 있음. 롤 검증시
			.antMatchers("/admin").access("hasRole('ROLE_ADMIN')") // ADMIN만 접속이 가능하게
			.anyRequest().permitAll()
			.and()
			.formLogin() // x-www-form-urlencoded
			.loginPage("/loginForm")
			.loginProcessingUrl("/login"); // 스프링 시큐리티가 /login(POST) 방식으로 들어오면 낚아챈다.
	}
}