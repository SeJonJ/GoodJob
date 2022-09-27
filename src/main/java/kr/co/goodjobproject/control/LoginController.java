package kr.co.goodjobproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.goodjobproject.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService service;

//	// 로그인 폼으로 이동
	@GetMapping("/login/loginform")
	public String loginform() {
//		System.out.println("로그인폼");
		return "login/loginform";
	}
	
	// 일반 회원 회원가입 폼으로 이동
	@GetMapping("/login/registerformMember")
	public String registerMember() {
		return "login/registerformMember";
	}
	
	// 기업 회원 회원가입 폼으로 이동
	@GetMapping("/login/registerformCompany")
	public String registerCompany() {
		return "login/registerformCompany";
	}
	
}
