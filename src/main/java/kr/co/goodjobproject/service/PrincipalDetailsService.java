package kr.co.goodjobproject.service;

import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dao.CompanyDAO;
import kr.co.goodjobproject.dao.MemberDAO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.MemberDTO;

// 시큐리티 설정에서 loginProcessingUrl 가 실행될때 
// "/login" 요청이 오면 자동으로 UserDetailsService 타입으로 IoC 되어있는
// loadUserByUsername 함수가 실행됨
// 일반, 회사 전용
@Service
public class PrincipalDetailsService implements UserDetailsService {

	@Autowired
	private MemberDAO mdao;
	
	@Autowired
	private CompanyDAO cdao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// username 으로 유저 가져오기
		//System.out.println("username : "+username);
		StringTokenizer st = new StringTokenizer(username);
		username = st.nextToken();
		String type = st.nextToken();
		
		if(type.equals("user")) {
			MemberDTO mdto = mdao.getOne(username);
//			System.out.println("userDetails : "+mdto);
			
			if(mdto != null) { 
				// mdto 가 null 이 아닌 경우 해당 유저가 있는것이고
				// PrincipalDetails 에 mdto 를 넣어 return
				// SecuritySession(Authentication(mdto))
//				System.out.println("빈 값이 아님");
//				System.out.println("mdto : "+mdto);
				return new PrincipalDetails(mdto, type);
			}
		}else if(type.equals("com")) {
			CompanyDTO cdto = cdao.getCompany(username);
			
			if(cdto !=null) {
//				System.out.println("cdto : "+cdto);
				return new PrincipalDetails(cdto, type);
			}
		}
		

		return null;
	}

}
