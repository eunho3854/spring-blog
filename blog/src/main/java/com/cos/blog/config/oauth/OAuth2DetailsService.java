package com.cos.blog.config.oauth;

import java.util.Map;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.cos.blog.config.auth.PrincipalDetails;
import com.cos.blog.domain.user.RoleType;
import com.cos.blog.domain.user.User;
import com.cos.blog.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class OAuth2DetailsService extends DefaultOAuth2UserService{

	private final UserRepository userRepository;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("OAuth 로그인 진행중............");
		System.out.println(userRequest.getAccessToken().getTokenValue());
		
		// 회원정보 요청 -> ya29.a0AfH6SMCfFG8rB6IJLsOmxzwZvnUnbjQjsgJR2i_8AfP8DiEsyQnv-0gA3_MxOwIwHBhRCC61IfKT5rh5U5bleTQRe_gnfcEIYxArzemdT2PNvNNCi605SkLVb4vV9J5xI4xQd_TKPix04zmYnE5CoZ36Z5Ed
		// 1. AccessToken으로 회원정보를 받았다는 의미
		// userRequest -> 액세스 토큰 정보
		// oAuth2User -> 유저정보
		OAuth2User oAuth2User = super.loadUser(userRequest);
		
		// 레트로핏 https://www.googleapis.com/drive/v2/file?access_token=userRequest.getAccessToken().getTokenValue()
		// https://www.googleapis.com/drive/v2/file
		// google, facebook, git 등은 위의 주소가 OAuth2AuthenticationException안에 내장되어 있음.
		System.out.println(oAuth2User.getAttributes());
		
		return processOAuth2User(userRequest, oAuth2User);
	}
	
	
	// 구글 로그인 프로세스
	private OAuth2User processOAuth2User(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		// 1번 통합 클래스를 생성
		// 구글은 name이고 카카오는 name_user이고 이런식으로 이름이 다를 수 있기 때문에 통합 클래스를 생성
		System.out.println("뭐로 로그인 했지? " +userRequest.getClientRegistration().getClientName());
		
		OAuth2UserInfo oAuth2UserInfo = null;
		if(userRequest.getClientRegistration().getClientName().equals("Google")) {
			oAuth2UserInfo = new GoogleInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getClientName().equals("Facebook")) {
			oAuth2UserInfo = new FacebookInfo(oAuth2User.getAttributes());
		} else if (userRequest.getClientRegistration().getClientName().equals("Kakao")) {
			oAuth2UserInfo = new KakaoInfo((Map)(oAuth2User.getAttributes()));
		} else if (userRequest.getClientRegistration().getClientName().equals("Naver")) {
			oAuth2UserInfo = new NaverInfo((Map)(oAuth2User.getAttributes().get("response")));
		}
		
		// 2번 최초 : 회원가입 + 로그인 최초 X : 로그인
		User userEntity = userRepository.findByUsername(oAuth2UserInfo.getUsername());
		
		UUID uuid = UUID.randomUUID() ;
		String enPassword = new BCryptPasswordEncoder().encode(uuid.toString()); 
		
		// null이면 회원가입이 아직 안 됨
		// 패스워드를 안 넣는 이유는 시큐리티가 아이디만 입력한 로그인을 막아주기 때문
		if(userEntity == null) {
			System.out.println("로그인 되지 않은 사용자입니다. 자동 회원가입을 진행합니다.");
			User user = User.builder()
					.username(oAuth2UserInfo.getUsername())
					.password(enPassword)
					.email(oAuth2UserInfo.getEmail())
					.role(RoleType.USER)
					.build();
			userEntity = userRepository.save(user);
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		} else { // 회원가입이 완료 됐다는 뜻 (원래는 구글 정보가 변경될 수 있기 때문에 update 해야하는데 지금은 안 하겠음)
			System.out.println("회원정보가 있습니다. 바로 로그인 합니다.");
			return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
		}
	}
}