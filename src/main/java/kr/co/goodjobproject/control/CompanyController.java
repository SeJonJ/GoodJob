package kr.co.goodjobproject.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.ApplicationDTO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.HireService;
import kr.co.goodjobproject.service.LocationService;
import kr.co.goodjobproject.service.ResumeService;


@Controller
public class CompanyController {
	
	@Autowired
	CompanyService cs;
	
	@Autowired
	ResumeService rs;
	
	@Autowired
	HireService hs;
	
	@Autowired
	LocationService ls;
	
	@Autowired
	private PasswordEncoder pwdEncoder;

	//기업회원 마이페이지 이동
	@GetMapping("myPage/myPageCompany")
	public String myPageCompany(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		if(principalDetails.getMdto() == null) {
			int cno = principalDetails.getCdto().getCno();
			
			int bookMarkNo = hs.getBookMarkNo(cno);
			List<String> locaList = ls.myPageHireLocation(cno);
			List<HireDTO> hList = hs.myPageHire(cno);
			List<String> TIList = rs.myPageResumeTI(cno);
			List<String> nList = rs.myPageResumeName(cno);
			List<ApplicationDTO> aList = rs.myPageResume(cno);
			String ctag = cs.myPageCompanyTag(cno);
			
			//마이페이지 공고 좋아요
			model.addAttribute("bookMarkNo", bookMarkNo);
			//마이페이지 공고정보(지역)
			model.addAttribute("locaList", locaList);
			//마이페이지 공고 정보
			model.addAttribute("hList", hList);
			//마이페이지 이력서 관심태그
			model.addAttribute("TIList", TIList);
			//마이페이지 이력서 작성자
			model.addAttribute("nList", nList);
			//마이페이지 이력서
			model.addAttribute("aList", aList);
			//마이페이지 관심태그
			model.addAttribute("ctag", ctag);
			model.addAttribute("cdto",principalDetails.getCdto());
			
			return "myPage/myPageCompany";
		}
		
		return "redirect:/";
	}
	
	//기업회원 수정 페이지 이동
	@GetMapping("modify/modifyCompany")
	public String modifyCompany(Model model,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		model.addAttribute("cdto", principalDetails.getCdto());
		
		return "modify/modifyCompany";
	}
	
	//기업정보 수정
	@PostMapping("modify/modifyCompanyOk")
	public String modifyCompanyOk(@ModelAttribute("dto") CompanyDTO dto ) {
		
		dto.setCpwd(pwdEncoder.encode(dto.getCpwd()));
		System.out.println(dto);
		cs.modifyCompanyOk(dto);
		
		return "redirect:/myPage/myPageCompany";
	}
	
	@PostMapping("/company/delete")
	@ResponseBody
	public int deleleCompany(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("pwd")String pwd) {
		
		if(pwdEncoder.matches(pwd, principalDetails.getCdto().getCpwd())) {
			return cs.delCompany(principalDetails.getCdto().getCno());
		}
		
		return 0;
	}
	
	

}
