package kr.co.goodjobproject.config;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityConfigTest {

	@Autowired
	private PasswordEncoder pwden;
	
	@Test
	void testPasswordEncoder() {
		String enPw = "$2a$10$g4tCic1KFIibhqjtWvLBce/IEtTRWPysbsXakcXK2w2EHS0Ecj2ce";

		
		String pwd = "tester123";
//		String enPw = pwden.encode(pwd);
//		System.out.println("암호화된 패스워드 : "+enPw);
		
		boolean result = pwden.matches(pwd, enPw);
//		boolean result = pwden.encode(pwd)==enPw;
		System.out.println("매칭 결과 : "+result);
	}

}
