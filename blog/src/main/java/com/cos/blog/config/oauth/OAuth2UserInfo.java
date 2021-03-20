package com.cos.blog.config.oauth;

import java.util.Map;

public abstract class OAuth2UserInfo {
	protected Map<String, Object> attributes;

	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	// 추상클래스로 한 이유는 오버라이딩하여 강제성을 부여하고 싶어서
	public abstract String getUsername();
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageUrl();
}
