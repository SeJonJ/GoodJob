package kr.co.goodjobproject.control;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.goodjobproject.api.InsertAPI;
import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dao.ApiDAO;
import kr.co.goodjobproject.dto.BookmarkDTO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.dto.HireListDTO;
import kr.co.goodjobproject.dto.LocationDTO;
import kr.co.goodjobproject.dto.PageMakeDTO;
import kr.co.goodjobproject.dto.PageMakeReviewDTO;
import kr.co.goodjobproject.dto.PageUtil;
import kr.co.goodjobproject.dto.PageUtilReview;
import kr.co.goodjobproject.dto.ReviewListnewDTO;
import kr.co.goodjobproject.service.BigLocationService;

import kr.co.goodjobproject.dto.LocationDTO;
import kr.co.goodjobproject.dto.PageMakeDTO;
import kr.co.goodjobproject.dto.PageUtil;

import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.HireService;
import kr.co.goodjobproject.service.JobService;
import kr.co.goodjobproject.service.LocationService;
import kr.co.goodjobproject.service.MemberService;
import kr.co.goodjobproject.service.RegisterMail;
import kr.co.goodjobproject.service.ResumeService;
import kr.co.goodjobproject.service.ReviewService;
import kr.co.goodjobproject.service.SmallLocationService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

	@Autowired
	private HireService hs;
	@Autowired
	CompanyService Comservice;
	@Autowired
	BigLocationService bigService;
	@Autowired
	SmallLocationService smallService;
	@Autowired
	RegisterMail mailService;
	@Autowired
	ApiDAO dao;
	@Autowired 
	MemberService ms;
	@Autowired
	ReviewService ReviewService;
	@Autowired
	private LocationService ls;
	@Autowired
	private JobService js;
	@Autowired
	private ResumeService rs;
	
	// api 로 가져온 데이터를 넣기 위한 test
	@RequestMapping("/test")
	public String test(Model model) {
		InsertAPI api = new InsertAPI();
//		api.insertHire(dao);

		//
		HashMap<String, HireDTO> nameMap = api.insertHire(3, 100, 201);
		System.out.println("size  : " + nameMap.size());

		for (String key : nameMap.keySet()) {
			dao.insertHire(nameMap.get(key));
			try {
				CompanyDTO cdto = api.insertCompa(key, nameMap.get(key).getCno());
				if (cdto == null) {
					continue;
				} else {
					dao.insertCompany(cdto);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		model.addAttribute("msg", ms.getAll());
		return "test";
	} // api test end
	
	
	//기업로그인시
	//메인->공고등록
	@GetMapping("hire/hireWriter") 
	public String goJobAdd (Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("cname",principalDetails.getCdto());
		
		model.addAttribute("CList",Comservice.allSelect());
		model.addAttribute("big",bigService.selectAll());
		model.addAttribute("small",smallService.selectAll());
		return "hire/hireWriter";
	}	
		
	
	//메인 -> 기업리뷰클릭시 이동
	/* 게시판 목록 페이지 접속(페이징 적용) */
	@RequestMapping("/reviewList")
	public String goReviewList(@AuthenticationPrincipal PrincipalDetails principalDetails,
			Model model, PageUtilReview page, RedirectAttributes rttr, @RequestParam(value="pageNum", defaultValue = "1")int pageNum, @RequestParam(value="sort", defaultValue = "normal")String sort) {
		
		System.out.println("pageNum : "+pageNum);
		System.out.println("sort : "+sort);
		
		List<ReviewListnewDTO> rlist = ReviewService.getListPagingReview(page);
		
		if(principalDetails != null) {
			
			if(principalDetails.getType().equals("user")) {			
				//log.info("test");
				model.addAttribute("user", principalDetails.getMdto());
				
				model.addAttribute("list", rlist);
				int total = ReviewService.getTotal(page);
				//log.info("total"+total);
				PageMakeReviewDTO pageMake = new PageMakeReviewDTO(page, total);
				model.addAttribute("pageMaker", pageMake);
				//model.addAttribute("page",page);
				log.info(page.getSort());
				log.info(page.getSearch());
				rttr.addAttribute("pageNum", page.getPageNum());
				rttr.addAttribute("search", page.getSearch());		
				rttr.addAttribute("sort", page.getSort());
				
				
				return "/review/reviewList";
			}else {				
				model.addAttribute("com", principalDetails.getCdto());
				
				model.addAttribute("list", rlist);
				int total = ReviewService.getTotal(page);
				PageMakeReviewDTO pageMake = new PageMakeReviewDTO(page, total);
				model.addAttribute("pageMaker", pageMake);
				//model.addAttribute("page",page);
				
				rttr.addAttribute("pageNum", page.getPageNum());
				rttr.addAttribute("search", page.getSearch());		
				rttr.addAttribute("sort", page.getSort());
				
				return "/review/reviewList";
			}
			
		}else {
			model.addAttribute("list", rlist);
			int total = ReviewService.getTotal(page);
			PageMakeReviewDTO pageMake = new PageMakeReviewDTO(page, total);
			model.addAttribute("pageMaker", pageMake);
			//model.addAttribute("page",page);
			log.info(page.getSort());
			log.info(page.getSearch());
			rttr.addAttribute("pageNum", page.getPageNum());
			rttr.addAttribute("search", page.getSearch());		
			rttr.addAttribute("sort", page.getSort());
			
			return "/review/reviewList";
		}

	}

	
	@GetMapping("commList")
	public String commList() {
		return "commList";
	}

	// Security 의 세션안에 담긴 정보 가져오기
	@RequestMapping("login/authtest")
	public String authtest(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
		if (principalDetails.getType().equals("user")) {
			System.out.println("MDTO : " + principalDetails.getMdto());

			model.addAttribute("user", principalDetails.getMdto());
		} else {
			System.out.println("cdto : " + principalDetails.getCdto());
			model.addAttribute("user", principalDetails.getCdto());
		}

		return "login/authtest";
	}

	// main이동, 값 보내기
	@RequestMapping(value = { "/", "main" })
	public String RecentHire(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		List<HireListDTO> rList = hs.getRecentHire();
		List<HireListDTO> bList = hs.getBookmarkHire();
		// principalDetails 가 null 이 아니고 로그인 한 상태라면
		if(principalDetails != null) {
			
			// 유저 좋아요 확인
			if(principalDetails.getMdto() != null) {
				int mno = principalDetails.getMdto().getMno();
//				int hno = hnoParam;
				
				List<BookmarkDTO> rrList = hs.getRecentHireBookmark(mno, rList);
				List<BookmarkDTO> bbList = hs.getRecentHireBookmark(mno, bList);
				
				model.addAttribute("rrList", rrList);
				model.addAttribute("bbList", bbList);
			}
		}
		
		model.addAttribute("Rlist", rList);
		model.addAttribute("Blist", bList);
		model.addAttribute("check", principalDetails!=null?true:false);

		return "main/main";
	}

	// hireList이동, 값 보내기 (페이징적용)
	@RequestMapping("hireList")
	public String gohireList(@AuthenticationPrincipal PrincipalDetails principalDetails,
			Model model, PageUtil page, RedirectAttributes rttr) {
		
		List<HireListDTO> hlist = hs.getHireListPaging(page);
		
		// principalDetails 가 null 이 아니고 로그인 한 상태라면
		if(principalDetails != null) {
			
			// 유저 좋아요 확인
			if(principalDetails.getMdto() != null) {
				int mno = principalDetails.getMdto().getMno();
				
				List<BookmarkDTO> hhList = hs.getHireListBM(mno, hlist);
				model.addAttribute("hhList", hhList);
				
				model.addAttribute("check", "user");
			
			}else {
				model.addAttribute("check", "company");
			}
		}else {
			model.addAttribute("check", "noLogin");
		}
		
		// 공고
		// log.info("정렬 : " + page.getSort());
		model.addAttribute("HList", hlist);

		int total = hs.getTotal(page);
		PageMakeDTO pageMake = new PageMakeDTO(page, total);
		
		model.addAttribute("pageMaker", pageMake);
		rttr.addAttribute("pageNum", page.getPageNum());
		rttr.addAttribute("keyword", page.getKeyword());
		rttr.addAttribute("jno", page.getJno());
		rttr.addAttribute("smallno", page.getSmallno());
		rttr.addAttribute("sort", page.getSort());
		
		return "hire/hireList";
	}

	// 검색바에 시군구 값 보내기
	@RequestMapping("locList")
	public @ResponseBody String locList(@ModelAttribute("dto") LocationDTO dto, Model model) {
		List<LocationDTO> list = ls.getLoc(dto);
		model.addAttribute("Llist", list);
		// System.out.println(list);

		ObjectMapper mapper = new ObjectMapper();
		String jsonList = "";

		try {
			jsonList = mapper.writeValueAsString(list);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonList;
	}

	// 이력서 메뉴 클릭시 이동(회원)
//	@RequestMapping("myResume")
//	public String goMyResume(Model model) {
//		return "resume/myResume";
//	}

	@RequestMapping("review/modal")
	public String goModal() {
		return "review/modal";

	}
	
	// 좋아요 업데이트
	@PostMapping("/likeUpdate")
	public void updateBookMark(@AuthenticationPrincipal PrincipalDetails principalDetails,
			@RequestParam("type")String type, @RequestParam("hno")int hno) {
		System.out.println("통신성공");
//		int hno = Integer.parseInt(hNum);
		
		if(type.equals("ins")) {
			hs.insLike(principalDetails.getMdto().getMno(), hno);
		}else {
			hs.delLike(principalDetails.getMdto().getMno(), hno);
		}
	}
	
	@RequestMapping("/dropdownResume")
	public String dropdownResume(@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		// 이력서 있는 경우
		if(rs.checkResume(principalDetails.getMdto().getMno()) >0) {
			return "redirect:myResume";
		}else {
			return "redirect:resumeWrite?listNum="+rs.getWorkInfo(principalDetails.getMdto().getMno()).size();
		}
	}
	
//	@PostMapping("/main/hireListLike")
//	public void updateHireListLike(@RequestParam("type")String type, @RequestParam("hno")int hno) {
//		System.out.println("통신 성공");
//	}

}