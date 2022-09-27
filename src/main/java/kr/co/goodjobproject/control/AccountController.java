package kr.co.goodjobproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.FindPwMail;
import kr.co.goodjobproject.service.MemberService;
import kr.co.goodjobproject.service.RegisterMail;

@Controller
public class AccountController {

	@Autowired
	MemberService ms;

	@Autowired
	CompanyService cs;

	// 회원가입 메일 서비스
	@Autowired
	RegisterMail registerMail;

	// 임시 패스워드 발송 서비스
	@Autowired
	FindPwMail findPwMail;

	@Autowired
	private PasswordEncoder passwdEncoder;

	// 일반회원 회원가입 완료
	@PostMapping("login/registerOkUser")
	public String registerOkUser(@ModelAttribute("dto") MemberDTO dto, @RequestParam("maddr2")String addr2) {
//		int mno; 
//		int tno;
//		String mid;
//		String mpwd;
//		String mname;
//		String mgender; // 성별
//		String maddr; // 주소
//		int enabled;  //0,1로 체크
//		int mbirth; // 생년월일
//		String mphone;
//		String memail;
//		int mpay; // 결제여부 1이면 결제 완료, 0 이면 미결제
//		String snsCheck; // sns 여부
//		String mimg; // 프로필 이미지
//		String role; // 유저 권한

		System.out.println("dto : " + dto);
		
		// 추가 주소가 있다면 주소 세팅
		if(addr2 != null) {
			dto.setMaddr(dto.getMaddr()+" "+addr2);
		}

		// dto 에서 패스워드 가져와서 다시 인코딩에서 set
		dto.setMpwd(passwdEncoder.encode(dto.getMpwd()));

		int result = ms.addGoodJobMember(dto);

//			System.out.println("result : " + result);

		return "redirect:/login/loginform";
	}

	// 기업 회원 회원가입 완료
	@PostMapping("login/registerOkCom")
	public String registerOkCom(@ModelAttribute("cdto") CompanyDTO cdto, @RequestParam("caddr2") String addr2) {
//			int cno;
//			int tno;
//			String cid;
//			String cpwd;
//			String cname;
//			String caddr;
//			String cphone;
//			int cpeople; // 총 사원수  
//			String ceo; // 대표자
//			String cmanager; // 담당자
//			String csetup; // 설립일
//			String chomepage; // 홈페이지
//			String csales; // 매출액
//			String cbin; // 사업자번호
//			String cimg; // 이미지
//			String role; // 기업 권한

//		 dto 에서 패스워드 가져와서 다시 인코딩에서 set
		cdto.setCpwd(passwdEncoder.encode(cdto.getCpwd()));

//		주소에 상세주소 추가
		cdto.setCaddr(cdto.getCaddr() + " " + addr2);

//		홈페이지 주소 null 이라면 noHomePage 로 변경
		if (cdto.getChomepage().isEmpty()) {
			cdto.setChomepage("noHomePage");
		}

		System.out.println("dto : " + cdto);

		int result = cs.addCompany(cdto);

//			System.out.println("result : " + result);

		return "redirect:/login/loginform";
	}

	// 아이디 중복 검사
	@PostMapping("login/checkid")
	@ResponseBody
	public int checkid(@RequestParam("id") String id, @RequestParam("type") String type) {

//		System.out.println("ajax 완료 : "+id);
//		String check = service.checkID(id);
//		System.out.println("중복검사 : "+a);

		if (id.equals(ms.checkID(id, type))) {
			return 1;
		}
		return 0;
	}

	// 이메일 인증
	@PostMapping("login/mailConfirm")
	@ResponseBody
	String mailConfirm(@RequestParam("email") String email) throws Exception {

		String code = registerMail.sendSimpleMessage(email);
		System.out.println("인증코드 : " + code);
		return code;
	}

	// 일반회원 계정 찾기 페이지 이동
	@GetMapping("login/findAcntMember")
	String findAcntMember() {
		return "login/findAcntMember";
	}

	// 일반 회원 아이디 찾기
	@PostMapping("login/findMemberId")
	@ResponseBody
	String findMemberId(@RequestParam("mname") String mname, @RequestParam("mphone") String mphone) {
		// System.out.println(mname+ " : "+mphone);

		return ms.findMemberId(mname, mphone);
	}

	// 일반 회원 비밀번호 찾기 및 임시 패스워드로 변경
	@PostMapping("login/findMemberPwd")
	@ResponseBody
	String findMemberPwd(@RequestParam("mid") String mid, @RequestParam("mname") String mname,
			@RequestParam("mphone") String mphone) throws Exception {
		// System.out.println(mid + " : " + mname + " : " + mphone);
		MemberDTO mdto = ms.findMemberPwd(mid, mname, mphone);

		if (mdto != null) {
			// 임시 패스워드 메일 발송 및 변수 저장
			String tempPw = passwdEncoder.encode(findPwMail.sendSimpleMessage(mdto.getMemail()));
			// System.out.println("tempPw : " + tempPw);
			// 임시 패스워드 db 에 저장
			ms.changeTempPw(tempPw, mdto.getMno());

			return "변경완료";
		}
		return null;
	}

	// 기업 계정 찾기 페이지 이동
	@GetMapping("login/findAcntCompany")
	String findAcntCompany() {
		return "login/findAcntCompany";
	}

	// 기업 계정 아이디 찾기
	@PostMapping("login/findCompanyId")
	@ResponseBody
	String findCompanyId(@RequestParam("cbin") String cbin, @RequestParam("cmanager") String cmanager) {

		return cs.findCompanyId(cbin, cmanager);
	}

	// 기업 계정 패스워드 찾기 및 임시 패스워드로 변경
	@PostMapping("login/findCompanyPwd")
	@ResponseBody
	String findCompanyPwd(@RequestParam("cid")String cid, @RequestParam("cbin") String cbin, @RequestParam("cmanager") String cmanager) throws Exception {
		CompanyDTO cdto = cs.findCompanyPwd(cid, cbin, cmanager);
		
		if(cdto != null) {
			// 임시 패스워드 생성 및 메일 발송
			String tempPw = passwdEncoder.encode(findPwMail.sendSimpleMessage(cdto.getCemail()));
			
			// 임시 패스워드로 업데이트
			cs.changeTempPw(cdto.getCno(), tempPw);
			
			return "변경완료";
		}
		return null;
	}

}
