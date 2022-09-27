package kr.co.goodjobproject.socialLogin;

import java.util.HashMap;
import java.util.Map;



public class KakaoLogin implements SocialLogin{
	
	private Map<String, Object> kakaoAttributes;

	
	public KakaoLogin(Map<String, Object> kakaoAttributes) {
		this.kakaoAttributes = kakaoAttributes;
	}
	
	
	@Override
	public String getProvider() {
		// TODO Auto-generated method stub
		
		return "kakao";
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		HashMap<String, Object> account = (HashMap<String, Object>) kakaoAttributes.get("kakao_account");
		
		String memail = (String) account.get("email"); // 이메일 가져오기
//		System.out.println("memail : "+memail);
		
		return memail;
	}

	@Override
	public String getNickName() {
		// TODO Auto-generated method stub
		HashMap<String, Object> properties = (HashMap<String, Object>) kakaoAttributes.get("properties");
		String mname = (String) properties.get("nickname"); // 닉네임 가져오기
		
		return mname;
	}
}
