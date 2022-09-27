package kr.co.goodjobproject.control;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.dto.ReviewDTO;
import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.JobcodeService;
import kr.co.goodjobproject.service.MemberService;
import kr.co.goodjobproject.service.ReviewService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ReviewController {
	
	@Autowired
	ReviewService rs;
	@Autowired
	CompanyService cs;
	@Autowired
	JobcodeService js;
	@Autowired
	MemberService ms;
	
	//리뷰디테일로 이동
	@RequestMapping("/reviewDetail")
	public String goReviewDetail(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestParam(value="cno")int cno, Model model) {
		
		if(principalDetails != null) {

			model.addAttribute("user", principalDetails);
			
			if(principalDetails.getType().equals("user")) {
			
				model.addAttribute("job",js.selectAll());
				model.addAttribute("cname",cs.getCname(cno));
				model.addAttribute("cno",cs.getCno(cno));
				model.addAttribute("cnt",rs.getReviewCnt(cno));
				model.addAttribute("list",rs.getOneComReviewList(cno));	
				return "review/reviewDetail";
			}else {
				
				model.addAttribute("job",js.selectAll());
				model.addAttribute("cname",cs.getCname(cno));
				model.addAttribute("cno",cs.getCno(cno));
				model.addAttribute("cnt",rs.getReviewCnt(cno));
				model.addAttribute("list",rs.getOneComReviewList(cno));					
				return "review/reviewDetail";
			}
		}else {

			model.addAttribute("job",js.selectAll());
			model.addAttribute("cname",cs.getCname(cno));
			model.addAttribute("cno",cs.getCno(cno));
			model.addAttribute("cnt",rs.getReviewCnt(cno));
			model.addAttribute("list",rs.getOneComReviewList(cno));		
			return "review/reviewDetail";
		}	
	}
	
		
	/*
	//리뷰리스트페이지(검색+정렬)
	@GetMapping("/searchOk")
	public String getComList(@RequestParam(value="search", required = false)String search,
			@RequestParam(value="order", required = false)String order, Model model) {
		model.addAttribute("getList",rs.getSearchOrderList(search, order));
		
		return "/review/searchOk";
	}*/
	
	
	  //리뷰insert 
	  @GetMapping("/writeOOK")
	  public String ReviewWriteOK(@AuthenticationPrincipal PrincipalDetails principalDetails,
			  @ModelAttribute("dto")ReviewDTO dto, Model model ) { 
		  	  
		  if(principalDetails != null) {
				
				if(principalDetails.getType().equals("user")) {
					rs.insertReview(dto);
					model.addAttribute("user", principalDetails.getMdto());
					return "redirect:/reviewList";
				}else {
					rs.insertReview(dto);
					model.addAttribute("user", principalDetails.getCdto());
					return "redirect:/reviewList";
				}				
			}else {
				rs.insertReview(dto);
				return "redirect:/reviewList";
			}
		  
	  	}
	  
	  // 리뷰 수정
	  @RequestMapping("/reviewModify")
	 public String modifyReview(@RequestParam("rno")int rno, @RequestParam("jno")String jno, @RequestParam("rtitle")String rtitle, 
			 @RequestParam("rgood")String rgood, @RequestParam("rbad")String rbad, @RequestParam("rstar")int rstar) {
		
		 System.out.println(rno+" "+jno+" "+rtitle+" "+rgood+" "+rbad+" "+rstar);
		 
		 ReviewDTO rdto = new ReviewDTO();
		 
		 rdto.setRno(rno); 
		 rdto.setJno(Integer.parseInt(jno));
		 rdto.setRstar(rstar);
		 rdto.setRtitle(rtitle);
		 rdto.setRgood(rgood);
		 rdto.setRbad(rbad);
		 
		 rs.modifyReview(rdto);
		 
		return "redirect:/reviewList";
		 
	 }
	  
	  
	     
	  //리뷰 삭제
	  @PostMapping("/reviewDelete")
	  public String ReviewDelete(@RequestParam("rno")int rno) {
		  rs.deleteReview(rno);
		  log.info("test");
		return "redirect:/reviewList";
		  
	  }
	

}