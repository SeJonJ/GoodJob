package kr.co.goodjobproject.dto;

import java.util.HashMap;
import java.util.Map;

// resumeList.jsp 에서 사용되는 class
public class ResumeListPage {

	public static Map<String, Object> getPageDate(int totalNumber, int countPerPage, int currentPage){
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 총 페이지
		int totalPage = (totalNumber%countPerPage==0)?totalNumber/countPerPage:totalNumber/countPerPage+1;
		
		// 현재 페이지 게시물의 시작번호
		// mysql limit 이용
		// 1번째 페이지에서는 0, 2번째 페이지에서는 15, 3번째는 30..
		int startNo = (currentPage - 1) * countPerPage;

		// 시작 페이지 번호
		int startPageNo = currentPage -5 <=0?1:currentPage-5;
		// 끝 페이지 번호
		int endPageNo = startPageNo+10>=totalPage?totalPage:startPageNo+10;
		
		// 이전
		boolean prev = currentPage>5?true:false;
		// 다음
		boolean next = currentPage+5>=totalPage? false:true;
		
		map.put("currentPage", currentPage);
		map.put("totalPage", totalPage);
		map.put("startNo", startNo);
		map.put("startPageNo", startPageNo);
		map.put("endPageNo", endPageNo);
		map.put("prev", prev);
		map.put("next", next);
		
		return map;
	}
}
