package kr.co.goodjobproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.BookmarkDTO;
import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.dto.HireDetailDTO;
import kr.co.goodjobproject.dto.HireDetailLocDTO;
import kr.co.goodjobproject.service.BigLocationService;
import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.HireService;
import kr.co.goodjobproject.service.SmallLocationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HireController {
	
	@Autowired
	HireService hs;
	@Autowired
	CompanyService cs;
	@Autowired
	BigLocationService bs;
	@Autowired 
	SmallLocationService ss;
	
	// 채용 공고 디테일 => 추후 hno = requestParam 으로 변경
	// 추후 지역코드 가져오게 sql 수정, dto 수정
	@GetMapping("/hire/hiredetail")
	public String hireDetails(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model,
			@RequestParam("hno")int hno) {
		
//		int hnoParam = 136;
		
		HireDetailDTO hdto = hs.hireDetail(hno);
		hdto.setHno(hno);
		HireDetailLocDTO hldto = hs.hireDetailLoc(hno);
		
		String[] strList = hs.getHireMain(hdto);
		String[] setupDate = hs.getSetupDate(hdto);
		
		// principalDetails 가 null 이 아니고 로그인 한 상태라면
		if(principalDetails != null) {
			
			// 유저 좋아요 확인
			if(principalDetails.getMdto() != null) {
				int mno = principalDetails.getMdto().getMno();
//				int hno = hnoParam;
				
				BookmarkDTO bdto = hs.getLike(mno, hno);
				System.out.println("bdto : "+bdto);
				model.addAttribute("like", bdto);
				
				// 개인유저 로그인한 경우 
				model.addAttribute("pay", principalDetails.getMdto().getMpay());
			}
		}
		
		System.out.println(hs.getDetailReview(hdto.getCno()));
		
		
		// 해당 공고 좋아요 개수
		model.addAttribute("bookmarkCnt", hs.getBookmarkOne(hno));
		
		// 공고 정보
		model.addAttribute("model", hdto);
		model.addAttribute("loc", hldto);
		model.addAttribute("main", strList);
		model.addAttribute("setup", setupDate);
		model.addAttribute("review", hs.getDetailReview(hdto.getCno()));
		
		
		
		return "/hire/hiredetail";
	}
	
	@GetMapping("/hire/likeUpdate")
	@ResponseBody
	public String likeUpdate(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("flag")String flag,
			@RequestParam("hno")String hno) {
		if(principalDetails == null) {
			return "noLogin";
		}else {
			
			int mno = principalDetails.getMdto().getMno();
			
			System.out.println("flag : "+flag);
			System.out.println("hno ; "+hno);
			System.out.println("mno ; "+mno);
			
			if(flag.equals("0")) {
				hs.insLike(mno, Integer.parseInt(hno));
				return "ins";

			}else {
				hs.delLike(mno, Integer.parseInt(hno));
				return "del";
			}
		}
	}
	
	//공고등록 insert //@ModelAttribute("dto")EmpDTO dto
	@PostMapping("/hire/hireWriter")
	public String insertHireWriter(@ModelAttribute("dto")HireDTO dto) {
		hs.insertOne(dto);
		return "redirect:/main";
		
	}
	
	//공고수정페이지
	@GetMapping("/hire/hireWriterModify2")
	public String modifyHireWriter(@RequestParam("hno")int hno, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("마이페이지->공고수정페이지 : "+principalDetails.getCdto());

		
		model.addAttribute("one",hs.getOne(hno));
		
		model.addAttribute("CList",cs.selectAll());
		model.addAttribute("big",bs.selectAll());
		model.addAttribute("small",ss.selectAll());
		return "/hire/hireWriterModify2";
	}
	
	//공고수정
	@GetMapping("/hire/hireWriterModify")
	public String modifyOne(@RequestParam("hno")int hno, Model model, @ModelAttribute("dto")HireDTO dto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		System.out.println("공고수정 : "+principalDetails.getCdto());
		model.addAttribute("one",hs.getOne(hno));
		hs.modifyOne(dto);
		System.out.println(dto);
		return "redirect:/myPage/myPageCompany";
	}
	
	//공고삭제
	@GetMapping("/deleteHireWriter")
	public String deleteHireWriter(@RequestParam("hno")int hno) {
		hs.deleteHireWriter(hno);
		return "redirect:/myPage/myPageCompany";
		
	}
	
	
}
