package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails{

	private User user;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 해당 계정이 만료 됐는지
	@Override
	public boolean isAccountNonExpired() {
		// true : 영원히 만료 안 됨.
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// lock 안함.
		return true;
	}

	//  계정의 패스워드가 만료되지 않았는지
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 되어있는지
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(() -> "ROLE_" + user.getRole().toString());	
		return collectors;
	}
}
