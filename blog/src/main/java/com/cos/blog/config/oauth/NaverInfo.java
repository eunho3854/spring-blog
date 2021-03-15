package com.cos.blog.config.oauth;

import java.util.Map;

// google은 이렇게 하면 되고
// facebook, naver 등등 만들면 됨
public class NaverInfo extends OAuth2UserInfo{

	public NaverInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("id");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return "";
	}

	// 유일한 아이디를 만듦
	@Override
	public String getUsername() {
		return "Naver_" + (String) attributes.get("id");
	}
	
}