package kr.co.goodjobproject.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{

	
	private MemberDTO mdto;
	private CompanyDTO cdto;
	
	private Map<String, Object> attributes;
	private String type;
	
	
	
	// 일반 유저 로그인 시 사용하는 생성자
	public PrincipalDetails(MemberDTO mdto, String type) {
		this.mdto = mdto;
		this.type = type;
	}

	// 기업 유저 로그인 시 사용하는 생성자
	public PrincipalDetails(CompanyDTO cdto, String type) {
		this.cdto = cdto;
		this.type = type;
	}

	// OAuth2User 를 사용한 SNS 유저 로그인 시 사용하는 생성자
	public PrincipalDetails(MemberDTO mdto, Map<String, Object> attributes, String type) {
		this.mdto = mdto;
		this.attributes = attributes;
		this.type = type;
	}
	
	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> role = new ArrayList<>();
		
		role.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				// TODO Auto-generated method stub
				if(type.equals("user")) {
					return mdto.getRole();
				}else {
					return cdto.getRole();
				}
			}
		});
//		System.out.println("role : "+role);
		return role;
	}

	// 해당 유저의 패스워드 리턴
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		if(type.equals("user")) {
			return mdto.getMpwd();

		}else {
			return cdto.getCpwd();
		}
	}

	// 해당 유저의 mid 리턴
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		if(type.equals("user")) {
			return mdto.getMid();
		}else {
			return cdto.getCid();
		}
	}

	// 계정 만료가 아니니?
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 잠긴게 아니니?
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	// 계정 정보 변경해야하는거 아니니?
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		if(mdto != null) {
			if(mdto.getIsNonExpired().equals("true")) {
				return true;
			}
			return false;
		}else {
			if(cdto.getIsNonExpired().equals("true")) {
				return true;
			}
			return false;
		}
	}

	// 계정 활성화 되어있니?
	@Override
	public boolean isEnabled() {
		// 예를 들어서 사이트에서 1년동안 회원이 로그인 안하면 
		// 해당 계정 휴면 계정으로 전환하는 규정같은 것들이 있을때 사용!!
		// 현재시간 - 로긴시간 => 1년 초과시 return false
		return true;
	}

	// OAuth2User 즉 sns 로그인 유저의 oauth2user 의 Attributes 정보를 확인하기 위한 메서드
	@Override
	public Map<String, Object> getAttributes() {
		// TODO Auto-generated method stub
		return attributes;
	}

	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
