package kr.co.goodjobproject.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.BoardCommentDTO;
import kr.co.goodjobproject.dto.BoardDTO;
import kr.co.goodjobproject.dto.BoardLikeDTO;
import kr.co.goodjobproject.dto.ResumeListPage;
import kr.co.goodjobproject.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService bs;
	
	// commLIst.jsp 이동
	@GetMapping(value = {"/comm/commList","/comm/commList/{tag}"})
	public String commList(@PathVariable(required = false) String tag ,Model model,
							@AuthenticationPrincipal PrincipalDetails principalDetails,
							@RequestParam(name = "currentPage", defaultValue = "1")int currentPage) {
		
		
		
		List<BoardDTO> list = null;
		Map<String, Object> map = null;
		
		// 총 게시물 수 
		int totalNumber = 0;
		
		// 페이지당 게시물 수
		int countPerPage = 15;
		
		// tag 선택한 경우 tag별로 정렬한 list 생성.
		if(tag != null) {
			if(tag.equals("전체")) {
				totalNumber = bs.getTotal();
			}else {
				totalNumber = bs.getBtagTotal(tag);
			}
			map = ResumeListPage.getPageDate(totalNumber, countPerPage, currentPage);
			int startNo = (int)map.get("startNo");
			
			list = bs.getTagSort(tag,startNo);
			
			model.addAttribute("btag",tag);
			
		}else { // tag 미선택 한 경우 전체 list 생성.
			totalNumber = bs.getTotal();
			
			map = ResumeListPage.getPageDate(totalNumber, countPerPage, currentPage);
			int startNo = (int)map.get("startNo");
			
			list = bs.getAll(startNo);
		}
		
		List<String> idList = new ArrayList<String>(list.size());
		List<Integer> bnoCntList = new ArrayList<Integer>(list.size());
		List<Integer> bLikeCntList = new ArrayList<Integer>(list.size());
		List<Integer> checkLikeCntList = new ArrayList<Integer>(list.size());

		// 일반회원으로 로그인 체크
		if(principalDetails != null && principalDetails.getType().equals("user")) {
			for(BoardDTO dto : list) {
				checkLikeCntList.add(bs.checkBLike (dto.getBno(), principalDetails.getMdto().getMno()));
			}
			model.addAttribute("loginMno", principalDetails.getMdto().getMno());
			model.addAttribute("checkLikeCntList",checkLikeCntList);
		}
			
		for(BoardDTO dto : list) {
			// 해당 board에 mno값으로 member테이블에서 mid 가져와서 idList에 담아주기
			// boardComment 테이블에서 댓글 수 가져와서 담아주기
			idList.add(bs.getBoardId(dto.getMno()));
			bnoCntList.add(bs.getCommentCnt(dto.getBno()));
			bLikeCntList.add(bs.getBLikeCnt(dto.getBno()));
		}
			
		model.addAttribute("list",list);
		model.addAttribute("idList",idList);
		model.addAttribute("bnoCntList",bnoCntList);
		model.addAttribute("bLikeCntList",bLikeCntList);
		model.addAttribute("map",map);
		return "/comm/commList";
	}
	
	
	// commDetail.jsp 이동
	@GetMapping("/comm/commDetail")
	public String commDetail(@RequestParam("bno")int bno, Model model
						,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("dto", bs.getOne(bno));
		map.put("id", bs.getBoardId(bs.getOne(bno).getMno()));
		map.put("blnoCnt", bs.getCommentCnt(bno));
		map.put("bLikeCnt", bs.getBLikeCnt(bno));
		
		if(principalDetails != null) {
			if(principalDetails.getType().equals("user")) {
				
				// session에 담겨있는 mno값 넘겨주기
				map.put("loginMno", principalDetails.getMdto().getMno());
				// 유저 로그인한 경우 해당 글에 좋아요를 했는지 안했는지 여부 확인 true = 1 false = 0
				int checkBLike = bs.checkBLike(bno,principalDetails.getMdto().getMno());
				
				
				// 해당 게시글에 로그인한 유저가 좋아요 눌렀는지 여부 확인후 i 클래스 반환
				if(checkBLike == 1) {
					map.put("heartUpdate", "bi bi-heart-fill fs-5");
				}else {
					map.put("heartUpdate", "bi bi-heart fs-5");
				}
				
			}
		}
		model.addAttribute("map",map);
		return "/comm/commDetail";
	}
	
	// commDetail 에서 ajax 동작
	@ResponseBody
	@PostMapping("/comm/boardLike")
	public Map<String, Object> ajaxBLike(@RequestBody HashMap<String, Object> map) {
		int mno = (int)map.get("mno");
		int bno = (int)map.get("bno");
		
		if(map.get("heartUpdate").equals("bi bi-heart-fill fs-5")) {
			bs.deleteBoardLike(mno, bno);
			map.put("bLikeCnt", Integer.parseInt((String)map.get("bLikeCnt"))-1);
			map.put("heartCheck", 0);
			return map;
		}else { // 반대 경우
			bs.addBoardLike(mno, bno);
			map.put("bLikeCnt", Integer.parseInt((String)map.get("bLikeCnt"))+1);
			map.put("heartCheck", 1);
			return map;
		}
	}
	
	// commDetail.jsp 에서 comment 등록시
	@PostMapping("/comm/commentWrite")
	public String commentWriteOk(@ModelAttribute("bcDto")BoardCommentDTO bcDto) {
		bs.addComment(bcDto);
		return "redirect:/comm/commDetail?bno="+bcDto.getBno();
	}
	
	
	// boardComment.jsp(리뷰) 이동 (commDetail.jsp 안에 import로 사용)
	@RequestMapping("/comm/boardComment")
	public String boardComment(@RequestParam("bno")int bno, Model model) {
		
		// bno를 이용해서 boardComment 테이블에 있는 리뷰정보 가져오기
		List<BoardCommentDTO> list = bs.getCommentAll(bno);

		// 리뷰가 있는지 없는지 한번 체크
		if(list.isEmpty()) {
			model.addAttribute("list",list);
		}else {
			// 리뷰가 있는 경우 해당 리뷰의 작성자 목록 list로 받아오기
			List<String> idList = new ArrayList<String>(list.size());
			
			for(BoardCommentDTO dto : list) {
				idList.add(bs.getBoardId(dto.getMno()));
			}
			model.addAttribute("list",list);
			model.addAttribute("idList",idList);
		}
		
		return "/comm/boardComment";
	}
	
	// commWrite 글 작성 페이지 이동
	@GetMapping("/comm/commWrite")
	public String commWrite(@AuthenticationPrincipal PrincipalDetails principalDetails,RedirectAttributes rttr) {
		
		// 유저,기업 로그인 한 경우와 로그인하지 않은 경우 분리
		if(principalDetails != null) {
			if(principalDetails.getType().equals("user")) {
				return "/comm/commWrite";
			}else {
				// 기업인 경우 커뮤니티 글작성 제한
				// comm/commList에서 alert 띄우기 위해 메세지 전달
				rttr.addFlashAttribute("loginCheck","기업회원은 글 작성이 불가능합니다");
				return "redirect:/comm/commList";
			}
		}else {
			rttr.addFlashAttribute("loginCheck","로그인 후 이용해주세요");
			return "redirect:/comm/commList";
		}
		
	}
	
	// commWrite에서 글 작성
	@PostMapping("/comm/commWrite")
	public String commWriteOk(@ModelAttribute("bdto")BoardDTO bdto,
						@AuthenticationPrincipal PrincipalDetails principalDetails) {
		bdto.setMno(principalDetails.getMdto().getMno());
		bs.addOne(bdto);
		return "redirect:/comm/commDetail?bno="+bs.getMaxBno();
	}
	
	// commDetail 글 수정 페이지 이동
	@GetMapping("/comm/commModify")
	public String modifyForm(@RequestParam("bno")int bno,Model model) {
		model.addAttribute("bdto",bs.getOne(bno));
		return "/comm/commModify";
	}
	
	// commModify 수정 완료
	@PostMapping("/comm/commModify")
	public String boardModifyOk(@ModelAttribute("bdto")BoardDTO bdto) {
		bs.boardModifyOne(bdto);
		return "redirect:/comm/commDetail?bno="+bdto.getBno();
	}
	
	// commDetail 글 삭제
	@PostMapping("/comm/deleteBoard")
	public String deleteComm(@RequestParam("bno")int bno) {
		bs.deleteBoardOne(bno);
		return "redirect:/comm/commList";
	}
	
	// 커뮤니티 댓글 수정
	@PostMapping("/comm/modifyComment")
	public String modifyComment(@ModelAttribute("bcdto")BoardCommentDTO bcdto) {
		bs.modifyCommentOne(bcdto);
		return "redirect:/comm/commDetail?bno="+bcdto.getBno();
	}
	
	// 커뮤니티 댓글 삭제
	@PostMapping("/comm/deleteComment")
	public String deleteComment(@RequestParam("bcno")int bcno, @RequestParam("bno")int bno) {
		bs.deleteCommentOne(bcno);
		return "redirect:/comm/commDetail?bno="+bno;
	}
	
}
