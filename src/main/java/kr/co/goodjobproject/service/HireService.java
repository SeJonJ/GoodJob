package kr.co.goodjobproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.HireDAO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dao.ReviewDAO;
import kr.co.goodjobproject.dto.BookmarkDTO;
import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.dto.HireDetailDTO;
import kr.co.goodjobproject.dto.HireDetailLocDTO;
import kr.co.goodjobproject.dto.HireListDTO;
import kr.co.goodjobproject.dto.PageUtil;
import kr.co.goodjobproject.dto.ReviewDTO;

@Service
public class HireService {
	
	@Autowired
	private HireDAO dao;
	
	//공고등록
	public void insertOne(HireDTO dto) {
			dao.insertOne(dto);
	}
	//공고한개
	public HireDTO getOne(int hno) {
		return dao.getOne(hno);
	}
	
	//공고수정
	public void modifyOne(HireDTO dto) {
		dao.modifyOne(dto);
	}
	
	//공고삭제
	public void deleteHireWriter(int hno) {
		dao.deleteHireWriter(hno);
	}
	
	public List<HireListDTO> getRecentHire(){
		return dao.getRecentHire();
	}
	
	public List<HireListDTO> getHireListPaging(PageUtil page){
		return dao.getHireListPaging(page);
	}
	
	public int getTotal(PageUtil page) {
		return dao.getTotal(page);
	}
	
//	// 채용공고상세
	public HireDetailDTO hireDetail(int hno) {
		return dao.getHireDetails(hno);
	}
	
	// 채용공고 지역 찾기
	public HireDetailLocDTO hireDetailLoc(int hno) {
		return dao.getHireDetailLoc(hno);
	}
	
	// 채용 공고 메인-링크 분리
	public String[] getHireMain(HireDetailDTO hdto) {
		char[] chArr = hdto.getHmain().toCharArray();
		
		int index = 0;
		for(int i=0; i<chArr.length; i++) {
			if(chArr[i] >= 65 && chArr[i] <=122) {
				index = i;
				break;
			}
		}
		
		String[] str = new String[2];
		str[0] = hdto.getHmain().substring(index);
		str[1] = hdto.getHmain().substring(0, index);
		
		 return str;
	}
	
	// 채용 공고 설립일 분리
	public String[] getSetupDate(HireDetailDTO hdto) {
		String[] str = new String[3];
		
		str[0] = hdto.getCsetup().substring(0, 4);
		str[1] = hdto.getCsetup().substring(4,6);
		str[2] = hdto.getCsetup().substring(6,8);
		
		return str;
	}
	
	// 채용공고 디테일 좋아요 숫자
	public int getBookmarkOne(int hno) {
		return dao.getBookmarkOne(hno);
	}
	
	// 유저 좋아요 확인
	public BookmarkDTO getLike(int mno, int hno) {
		return dao.getLikeFlag(hno, mno);
	}
	
	// 유저 좋아요 추가
	public int insLike(int mno, int hno) {
		return dao.insertBookmark(mno, hno);
	}
	
	//공고제목, 회사명, 마감일 조회
	public List<CompanyDTO> getCname(int mno){
		return dao.getCname(mno);
	}
	public List<HireDTO> getHname(int mno){
		return dao.getHname(mno);
	}
	
	//마이페이지 공고정보 가져오기
	public List<HireDTO> myPageHire(int cno){
		return dao.myPageHire(cno);
	}

	// 유저 좋아요 추가
	public int delLike(int mno, int hno) {
		return dao.deleteBookmark(mno, hno);
	}
	
	public List<HireListDTO> getBookmarkHire(){
		return dao.getBookmarkHire();
	}
	
	// 공고에 맞는 리뷰 하나만 가져오기
	public ReviewDTO getDetailReview(int cno) {
		return dao.getDetailReview(cno);
	}
	
	// 메인 - 최근 공고 리스트 좋아요 확인
	public List<BookmarkDTO> getRecentHireBookmark(int mno, List<HireListDTO> hList){
		List<BookmarkDTO> list = new ArrayList<>();
		
		for(HireListDTO dto : hList) {
			list.add(dao.getLikeFlag(dto.getHno(), mno));
		}
		
		return list;
	}
	
	// 메인 - 좋아요 많은 순 리스트 좋아요 확인
	public List<BookmarkDTO> getMostBookmarkList(int mno, List<HireListDTO> hList){
		List<BookmarkDTO> list = new ArrayList<>();
		
		for(HireListDTO dto : hList) {
			list.add(dao.getLikeFlag(dto.getHno(), mno));
		}
		
		return list;
	}
	
	// 검색 페이지 좋아요 확인
	public List<BookmarkDTO> getHireListBM(int mno, List<HireListDTO> hlist){
		List<BookmarkDTO> list = new ArrayList<>();
		
		for(HireListDTO dto : hlist) {
			list.add(dao.getLikeFlag(dto.getHno(), mno));
		}
		
		return list;
	}
	
	//기업 마이페이지 좋아요 갯수
	public int getBookMarkNo(int cno) {
		return dao.getBookMarkNo(cno);
	}

}