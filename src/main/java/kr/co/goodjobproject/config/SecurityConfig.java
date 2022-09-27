package kr.co.goodjobproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.goodjobproject.service.principalOauth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터 체인에 등록!
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private principalOauth2UserService principalOauth2UserService;
	
	// Bcrypt 는 패스워드는 해쉬로 암호화해주는 클래스
	// autowire 하기 위해 bean 으로 등록
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	// 각각 유저가 있는지 확인하는 쿼리문, 유저의 권한을 확인하는 쿼리문
	// configure(AuthenticationManagerBuilder auth) 메서드 대신
	// PrincipalDetails 를 사용함
	
	// 로그인 인가에 관한 설정
	// 위에서는 로그인 가능한 아이디인지 여부를 확인한다면
	// 아래에서는 일반 유저, 로그인 유저 등등이 어디서부터 어디까지 접근 가능한지
	// 혹은 로그인과 로그아웃과 관련된 설정에 대한 부분을 여기서한다
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable()
			.authorizeHttpRequests()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.antMatchers("/login/authtest").authenticated()
		.and()
			.formLogin().loginPage("/login/loginform").permitAll() // 커스텀 로그인 페이지
//			.defaultSuccessUrl("/") // 로그인 성공시 기본 이동 페이지
			.loginProcessingUrl("/login") // 로그인 form action url => /login 이라는 요청이 들어오면 시큐리티가 낚아채서 대신 로그인 진행
//			.usernameParameter("mid") // 로그인폼에서 id 를 적는 곳에 특정한 name을 사용한다면 여기서 변경
//			.passwordParameter("mpwd") // 로그인폼에서 password 를 적는 곳에 특정한 name을 사용한다면 여기서 변경
		.and()
			.logout().logoutUrl("/logout").permitAll()
			.logoutSuccessUrl("/") // 로그아웃 성공 시 이동 페이지
		.and()
			.oauth2Login() // 외부 인증
			.loginPage("/login/loginform") // oauth2Login 시 loginForm
			.userInfoEndpoint()
			.userService(principalOauth2UserService); // SNS 로그인이 완료된 뒤 후처리가 필요함. 엑세스토큰+사용자프로필 정보
	
	}
	

}
