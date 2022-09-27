package kr.co.goodjobproject.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dao.MemberDAO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.socialLogin.KakaoLogin;
import kr.co.goodjobproject.socialLogin.NaverLogin;
import kr.co.goodjobproject.socialLogin.SocialLogin;


// Oauth2 로 로그인 시 DefaultOAuth2UserService 아래의 loadUser 메서드가 실행됨
// 즉 Oauth2 로 로그인 후 후처리-회원가입 등 -에 관한 클래스와 메서드
@Service
public class principalOauth2UserService extends DefaultOAuth2UserService{
	
	@Autowired
	MemberDAO mdao;
	
	// SNS user 의 UserRequest 데이터에 대한 후처리 함수
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//		네이버 로그인 버튼 클릭 -> 네이버 로그인창 -> 로그인 완료 -> code 리턴(OAuth-client 라이브러리) -> AccessToken 요청
//		userRequest 정보 -> loadUser 함수 호출 -> 네이버 회원 프로필 받아오기
//		System.out.println("ClientRegistration : "+userRequest.getClientRegistration()); // ClientRegistration 정보
//		System.out.println("AccessToken : "+userRequest.getAccessToken().getTokenValue()); // accessToken 가져오기
		
//		userRequest 를 loadUser 를 실행해서 유저 정보가 담긴 oauth2user 로 변환
		OAuth2User oauth2user = super.loadUser(userRequest);
//		System.out.println("userRequest : "+userRequest);
//		System.out.println("oauth2user : "+oauth2user);
		
//		 code를 통해 구성한 정보
//		System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
		
//		 token을 통해 응답받은 회원정보
//		System.out.println("oAuth2User : " + oAuth2User);

		return oAuth2UserLogin(userRequest, oauth2user);
	}
	
	
	private OAuth2User oAuth2UserLogin(OAuth2UserRequest userRequest, OAuth2User oAuth2User) {
		
		// Attribute 를 파싱해서 공통 객체로 묶는다 => 관리를 위해
		SocialLogin loginUser = null;
		
		// provider 정보 => 어떤 SNS 로 로그인했는지
		String provider = userRequest.getClientRegistration().getRegistrationId();
		
		
		if(provider.equals("naver")) {
			loginUser = new NaverLogin(oAuth2User.getAttributes());
		}else if(provider.equals("kakao")) {
			loginUser = new KakaoLogin(oAuth2User.getAttributes());
		}
		
//		Map<String, Object> map = oAuth2User.getAttributes();
//		System.out.println("map : "+map);
		
		// 이메일 가져오기
		String memail = loginUser.getEmail();
		
		// sns 로그인 사용자의 회원정보 가져오기
		MemberDTO mdto = mdao.getSnsOne(memail, provider);
//		System.out.println("mdto : "+mdto);
		
		// 만약 meail 과 provider 를 사용해서 회원 정보를 불러왔을 때 
		// 가입된 회원이 없다면 sns 로 로그인한 사용자에 대해서 회원가입하는 로직
		// builder 패턴 사용
		if(mdto == null) {
			mdto = mdto.builder()
					.mid(memail)
					.tno(1)
					.mname(loginUser.getNickName())
					.mpay(0)
					.snsCheck(provider)
					.build();
			
			// build 된 정보로 SNS 회원 회원가입
			mdao.insertSNSMember(mdto);
			
			// 회원가입된 정보를 다시 불러와서 mdto 에 담음
			mdto = mdao.getSnsOne(memail, provider);
		}
		
		
		// return OAuth2User 인데 return new PrincipalDetails 가 가능한 이유는
		// PrincipalDetails 가 OAuth2User 를 구현한 구현 클래스이기 때문
		// 또한 oauth2user 를 같이 들고 가기 때문에 sns 유저인 것을 함께 확인 가능
		// 이 정보들은 SecuritySession 의 Authentication 안에 담김
		return new PrincipalDetails(mdto, oAuth2User.getAttributes(), "user");
	}
}
