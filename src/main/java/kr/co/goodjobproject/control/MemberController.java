package kr.co.goodjobproject.control;

import java.util.List;
import java.util.StringTokenizer;

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
import kr.co.goodjobproject.dto.BoardDTO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.dto.ReviewListDTO;
import kr.co.goodjobproject.service.BoardService;
import kr.co.goodjobproject.service.HireService;
import kr.co.goodjobproject.service.MemberService;
import kr.co.goodjobproject.service.ResumeService;
import kr.co.goodjobproject.service.ReviewService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	@Autowired
	HireService hs;
	@Autowired
	ReviewService rs;
	@Autowired
	BoardService bs;
	@Autowired
	ResumeService resumeS;
	
	@Autowired
	private PasswordEncoder pwdEncoder;
	
	// 일반회원 마이페이지 이동
	@GetMapping("myPage/myPageMember")
	public  String myPageMember(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		int mno = principalDetails.getMdto().getMno();
		
		String interest = ms.interest(mno);
		int resumeCount = resumeS.checkResume(mno);
		String payDate = ms.payDate(mno);
		List<BoardDTO> blist = bs.myPageBoard(mno);
		List<ReviewListDTO> rlist = rs.myPageReview(mno);
		List<CompanyDTO> cname = hs.getCname(mno);
		List<HireDTO> hlist = hs.getHname(mno);
		
		System.out.println(rlist);
		
		int pay = principalDetails.getMdto().getMpay();
		
		if(pay ==1) {
		StringTokenizer st = new StringTokenizer(payDate,"-");
		
		int year = Integer.parseInt(st.nextToken());
		String month = st.nextToken();
		String day = st.nextToken();
		
		String year2 =  String.valueOf(year+1);
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(year2);
		
		
		payDate = year2 +"-"+ month+"-"+day;
		
		System.out.println(payDate);
		
		//결제날짜
		model.addAttribute("payDate", payDate);
		}
		
		//관심분야
		model.addAttribute("interest",interest);
		//이력서 썻는지 확인
		model.addAttribute("resumeCount",resumeCount);
		//내가쓴글
		model.addAttribute("blist",blist);
		//내가쓴리뷰
		model.addAttribute("rlist", rlist);
		//관심기업
		model.addAttribute("cname", cname);
		model.addAttribute("hlist", hlist);
		
		//로그인정보
		model.addAttribute("mdto", principalDetails.getMdto());
//		System.out.println(principalDetails.getMdto());
//		log.info("rlist: "+rlist);
//		log.info("hlist: "+hlist);
//		log.info("cname: "+cname);
		//알겠습니다 감사합니다
		//getType : user : mdto / com : cdto
		//cdto  
		
//		MemberDTO list = ms.getOne(mid);
//		
//		model.addAttribute("mid", list);
//		
//		log.info("mid: "+list);
//		System.out.println("list: "+list);
		
		
		return "myPage/myPageMember";
	}

	//일반회원 개인정보수정 페이지 이동
	@GetMapping("modify/modifyMember")
	public String modifyMember(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		
		int mno = principalDetails.getMdto().getMno();
		
		List<MemberDTO> mList = ms.myPageMemberModify(mno);
		System.out.println(principalDetails.getMdto());
		
		model.addAttribute("mdto", principalDetails.getMdto());
		return "modify/modifyMember";
	}
	
	//일반회원 개인정보 수정완료
	@PostMapping("/modify/modifyMemberOk")
	public String modifyBasicMember(@ModelAttribute("dto") MemberDTO dto) {
		
		//dto에서 패스워드 가져와서 다시 인코딩에서 set
		dto.setMpwd(pwdEncoder.encode(dto.getMpwd()));
		System.out.println(dto);
		ms.modifyBasicMember(dto);
		
		return "redirect:/myPage/myPageMember";
	}
	//구독
	@GetMapping("subscribe")
	public String subscribe(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		int mno = principalDetails.getMdto().getMno();
		
		ms.subscribe(mno);
		
		principalDetails.getMdto().setMpay(1);
		
		return "redirect:myPage/myPageMember";
	}
	
	//구독취소
	@GetMapping("myPage/canclesub")
	public String canclesub(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		ms.canclesub(principalDetails.getMdto().getMno());
		
		principalDetails.getMdto().setMpay(0);
		
		return "redirect:/myPage/myPageMember"; 
	}
	
	@PostMapping("/member/delete")
	@ResponseBody
	public int deleteMember(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("pwd")String pwd) {
		//ms.delMember(principalDetails.getMdto().getMno());
		
//		System.out.println("pwd : "+pwd);
		if(pwdEncoder.matches(pwd, principalDetails.getMdto().getMpwd())) {
			return ms.delMember(principalDetails.getMdto().getMno());
		}
		return 0;
		
	}
	
	
	
}
