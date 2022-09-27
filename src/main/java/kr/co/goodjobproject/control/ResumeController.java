package kr.co.goodjobproject.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.ApplicationDTO;
import kr.co.goodjobproject.dto.ResumeDTO;
import kr.co.goodjobproject.dto.WorkDTO;
import kr.co.goodjobproject.dto.WorkDivDTO;
import kr.co.goodjobproject.service.ResumeService;
import lombok.extern.slf4j.Slf4j;
	
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.goodjobproject.dto.ResumeListDTO;
import kr.co.goodjobproject.dto.ResumeListPage;

@Slf4j
@Controller
public class ResumeController {

	@Autowired
	private ResumeService rs;
	
	
	// resumeList.jsp 페이지 이동
	@RequestMapping("/resume/resumeList")
	public String resumeList(Model model, @AuthenticationPrincipal PrincipalDetails PrincipalDetails,
			@RequestParam(name = "currentPage", defaultValue = "1")int currentPage) {
		
		
		// 총 게시물 수
		int totalNumber = rs.getTotal(238);
		// 페이지당 게시물 수
		int countPerPage = 15;
		
		Map<String, Object> map = ResumeListPage.getPageDate(totalNumber, countPerPage, currentPage);
		
		int startNo = (int)map.get("startNo");
		
		int cno = 0;
		
		if(PrincipalDetails != null && PrincipalDetails.getType().equals("com")) {
			cno = PrincipalDetails.getCdto().getCno();
			model.addAttribute("ResumeList",rs.getAll(cno,startNo));
			model.addAttribute("cno",cno);
		}
		
		List<ResumeListDTO> resumeList = rs.getAll(cno,startNo);
		List<Integer> checkLikeList = new ArrayList<Integer>(resumeList.size());
		
		
		for(ResumeListDTO dto : resumeList) {
			checkLikeList.add(rs.checkLike(cno,dto.getAno()));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("resumeList", resumeList);
		model.addAttribute("checkLikeList", checkLikeList);
		return "/resume/resumeList";
	}
	
	// resumeList.jsp에서 하트 클릭시 ajax
	@ResponseBody
	@PostMapping("/resume/resumeLike")
	public Map<String, Object> ajaxResumeLike(@RequestBody HashMap<String, Object> map){
		
		int cno = Integer.parseInt(map.get("cno").toString());
		int ano = Integer.parseInt(map.get("ano").toString());
		
		int checkLike = rs.checkLike(cno, ano);
		// 좋아요 하지 않은 이력서인 경우
		if(checkLike == 0) {
			map.put("heart", "../images/heart1.png" );
			rs.addLike(cno, ano);
		}else {
		// 이미 좋아요한 경우 (좋아요 취소)
			map.put("heart", "../images/heart0.png" );
			rs.deleteLike(cno, ano);
		}
		
		return map;
	}
	
	
	// 이력서 메뉴 클릭시 이동(회원)
	@RequestMapping("myResume")
	public String goMyResume(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("loginMno",principalDetails.getMdto().getMno());
		
		//System.out.println(principalDetails.getMdto().getMno());
		model.addAttribute("Mlist", rs.getMember(principalDetails.getMdto().getMno()));
		// 경력 사항
		model.addAttribute("workList", rs.getWorkInfo(principalDetails.getMdto().getMno()));
		model.addAttribute("resumeSize", rs.getWorkInfo(principalDetails.getMdto().getMno()).size());
		
		System.out.println("work : "+rs.getWorkInfo(principalDetails.getMdto().getMno()));
		
		return "resume/myResume";
	}
	
	// 이력서열람에서 디테일 클릭시 이동(기업)
	@RequestMapping("/resuemeListDetail")
	public String goUserResume(Model model, @RequestParam("ano")int ano) {
		
		ApplicationDTO dto = rs.applicationeGetOne(ano);
		
		model.addAttribute("loginMno",dto.getMno());
			
		//System.out.println(principalDetails.getMdto().getMno());
		model.addAttribute("Mlist", rs.getMember(dto.getMno()));
		// 경력 사항
		model.addAttribute("workList", rs.getWorkInfo(dto.getMno()));
		model.addAttribute("resumeSize", rs.getWorkInfo(dto.getMno()).size());
			
		System.out.println("workList : "+rs.getWorkInfo(dto.getMno()));
			
		return "resume/myResume";
		}
	
	// 이력서 작성
	@RequestMapping("resumeWrite")
	public String goresumeWrite(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestParam("listNum")String listNum) {
		
		model.addAttribute("loginMno",principalDetails.getMdto().getMno());
		model.addAttribute("Mlist", rs.getMember(principalDetails.getMdto().getMno()));
		model.addAttribute("workList", rs.getWorkInfo(principalDetails.getMdto().getMno()));
		model.addAttribute("workCnt", listNum);
		
		System.out.println("worklist : "+rs.getWorkInfo(principalDetails.getMdto().getMno()));
		System.out.println("workCnt : "+listNum);
		
		return "resume/resumeWrite";
	}
	
	// 수정 완료
	@PostMapping("resumeWriteOk")
	public String resumeWriteOk(@ModelAttribute("dto")ApplicationDTO adto, @AuthenticationPrincipal PrincipalDetails principalDetails
			, @ModelAttribute("wdto")WorkDivDTO wdto, @RequestParam("workCnt")int workCnt) {
		// 학력사항 연도 + 월
//		adto.setAstart(adto.getAstart() + "년" + " " + astart2 + "월");
//		adto.setAend(adto.getAend() + "년" + " " + aend2 + "월");
		//System.out.println("실행 완료");
		adto.setMno(principalDetails.getMdto().getMno());
		//wdto.setMno(principalDetails.getMdto().getMno());
		System.out.println("wdto : "+wdto);
		System.out.println("workCnt : "+workCnt);
		//System.out.println("wdto  : "+wdto);
		rs.insertResume(adto);
		rs.insertWork(adto.getAno(), principalDetails.getMdto().getMno(), wdto, workCnt);
		
		return "redirect:myResume";
	}
}
