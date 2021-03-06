package com.cos.blog.config.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("/login 이 호출되면 자동 실행되어 username이 DB에 있는지 확인한다.");
		User principal = userRepository.findByUsername(username);
		System.out.println("프린시펄 : " + principal);
		if(principal == null) {
			// 인증 실패
			return null;
		} else {
			// 내부적으로 세션이 생김
			// 권한 체크를 다 해줌
			return new PrincipalDetails(principal);
		}
	}
}
