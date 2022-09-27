package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.BoardDAO;
import kr.co.goodjobproject.dto.BoardCommentDTO;
import kr.co.goodjobproject.dto.BoardDTO;

@Service
public class BoardService {

	@Autowired
	private BoardDAO dao;
	
	// commList.jsp 처음 들어갔을때 실행
	public List<BoardDTO> getAll(int startNo){
		return dao.getAll(startNo);
	}
	
	public int getTotal() {
		return dao.getTotal();
	}
	public int getBtagTotal(String tag) {
		return dao.getBtagTotal(tag);
	}

	// commList.jsp에서 tag 선택시 실행
	public List<BoardDTO> getTagSort(String tag,int startNo){
		
		// tag 별로 다른 method 실행
		if(tag.equals("BEST")) {
			return dao.getBlikeSort();
		}else if(tag.equals("전체")) {
			return dao.getAll(startNo);
		}else {
			return dao.getTagSort(tag,startNo);
		}
	}
	
	// commList.jsp 에서 작성자 아이디 가져오는 method 
	public String getBoardId(int mno) {
		return dao.getBoardId(mno);
	}
	
	// commDetail에서 내가 좋아요 눌렀는지 여부 반환
	public int checkBLike(int bno, int mno) {
		return dao.checkBLike(bno, mno);
	}
	// boardLike 테이블 데이터 삭제
	public void deleteBoardLike(int mno, int bno) {
		dao.deleteBoardLike(mno,bno);
	}
	
	// boardLike 테이블 데이터 추가
	public void addBoardLike(int mno, int bno) {
		dao.addBoardLike(mno,bno);
	}
	// bno값으로 blike 개수 가져오는 method
	public int getBLikeCnt(int bno) {
		return dao.getBLikeCnt(bno);
	}
	// bno값으로 boardComment 개수 가져오는 method
	public int getCommentCnt(int bno) {
		return dao.getCommentCnt(bno);
	}
	
	// bno 이용해 게시판 글 하나만 가져오는 method
	public BoardDTO getOne(int bno) {
		return dao.getOne(bno);
	}
	
	
	//마이페이지 내가쓴글
	public List<BoardDTO> myPageBoard(int mno){
		return dao.myPageBoard(mno);
	}
	// board 게시판 글 추가
	public void addOne(BoardDTO dto) {
		dao.addOne(dto);
	}
	
	public int getMaxBno() {
		return dao.getMaxBno();
	}
	
	// board 게시판 글 수정
	public void boardModifyOne(BoardDTO dto) {
		dao.boardModifyOne(dto);
	}
	
	// board 게시판 글 삭제
	public void deleteBoardOne(int bno) {
		dao.deleteBoardOne(bno);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	/* boardComment 관련 method 들 */
	
	// commDetail.jsp 에서 리뷰 가져오는 method
	public List<BoardCommentDTO> getCommentAll(int bno){
		return dao.getCommentAll(bno);
	}
	
	// 커뮤니티 댓글 추가
	public void addComment(BoardCommentDTO dto) {
		dao.addComment(dto);
	}
	
	// 커뮤니티 댓글 수정
	public void modifyCommentOne(BoardCommentDTO dto) {
		dao.modifyCommentOne(dto);
	}
	// 커뮤니티 댓글 삭제
	public void deleteCommentOne(int bcno) {
		dao.deleteCommentOne(bcno);
	}
	
}
