package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.ReviewDAO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.PageUtilReview;
import kr.co.goodjobproject.dto.ReviewDTO;
import kr.co.goodjobproject.dto.ReviewListDTO;
import kr.co.goodjobproject.dto.ReviewListnewDTO;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewDAO dao;
	
	public List<ReviewDTO> selectAll(){
		return dao.selectAll();
	}
	
	public List<ReviewDTO> selectOne(int cno){
		return dao.selectOne(cno);
	}
	
	//기업평점
	public double getAvg(int cno) {
		return dao.getAvg(cno);
	} 
	
	//마이페이지 리뷰
	public List<ReviewListDTO> myPageReview(int mno){
		return dao.myPageReview(mno);
	}
	
	/* 게시판 목록(페이징 적용) */
    public List<ReviewListnewDTO> getListPagingReview(PageUtilReview page){
    	return dao.getListPagingReview(page);
    }
	
    /* 게시판 총 갯수 */
    public int getTotal(PageUtilReview page) {
    	return dao.getTotal(page);
    }
	
	
	/*
	//리뷰전체리스트(메인->기업리뷰)
	public List<ReviewListnewDTO> getReviewList(){
		return dao.getReviewList();
	}
	
	//기업리뷰 검색+정렬 //흠.. 
	public List<ReviewListnewDTO>getSearchOrderList(String search, String order){
		
		if(search != null && search !="") {
			if(order == null) {
				return dao.getSearchList(search);
			}else if(order != null && order != "") {
				if(order.equals("CNT")) {
					return dao.getCntList();
				}else if(order.equals("AVG")) {
					return dao.getAvgList();
				}
			}
					
		}else if(search == null) {
			if(order.equals("CNT")) {
				return dao.getCntList();
			}else if(order.equals("AVG")) {		
				return dao.getAvgList();
			}		
		}		
		return dao.getSearchOrderList(search, order);  	
	}*/
		
    
    
	//리뷰디테일(해당기업리뷰들)
	public List<ReviewListDTO> getOneComReviewList(int cno){
		return dao.getOneComReviewList(cno);	
	}
	
	//해당기업의 리뷰수
	public int getReviewCnt(int cno) {
		return dao.getReviewCnt(cno);
	}
	
	//모달insert
	public void insertReview(ReviewDTO dto) {
		dao.insertReview(dto);
	}
		
	//리뷰 수정
	public void modifyReview(ReviewDTO dto) {
		dao.modifyReview(dto);
	}
	
	//리뷰 삭제
	public void deleteReview(int rno) {
		dao.deleteReview(rno);
	}

}
