package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.goodjobproject.dto.BoardCommentDTO;
import kr.co.goodjobproject.dto.BoardDTO;

@Mapper
public interface BoardDAO {
	
	// Board 테이블에만 접근하는 DAO가 아니라 comm페이지 내에서 필요한 기능은 전부 모아놓은 DAO입니다~!
	
	@Select("SELECT count(*) FROM board ")
	int getTotal();

	@Select("SELECT count(*) FROM board WHERE btag = #{tag} ")
	int getBtagTotal(String tag);
	
	@Select("SELECT * FROM board ORDER BY bno desc limit #{startNo},15 ")
	List<BoardDTO> getAll(int startNo); 
	
	// tag별로 데이터 뽑아오기
	@Select("SELECT * FROM board WHERE btag = #{tag} ORDER BY bno desc limit #{startNo},15 ")
	List<BoardDTO> getTagSort(@Param("tag")String tag,@Param("startNo") int startNo);
	
	// commList Best 태그 선택시 좋아요순으로 정렬
	@Select("SELECT b.* FROM board b JOIN boardLike bl ON b.bno = bl.bno "
			+ "GROUP BY bl.bno ORDER BY count(bl.bno)DESC, b.bno DESC limit 0,15  ")
	List<BoardDTO> getBlikeSort();
	
	@Select(" SELECT mid FROM member WHERE mno = #{mno} ")
	String getBoardId(int mno);
	
	// bno로 boardLike 개수 가져오기
	@Select(" SELECT count(*) FROM boardLike WHERE bno = #{bno} ")
	int getBLikeCnt(int bno);
	
	// 내가 좋아요한 글 하트 빨갛게 표시
	@Select("SELECT count(*) FROM boardLike WHERE bno = #{bno} AND mno = #{mno} ")
	int checkBLike(@Param("bno")int bno, @Param("mno")int mno);
	
	// boardLike 테이블 데이터 삭제 (커뮤니티 게시글 좋아요 삭제)
	@Delete("DELETE FROM boardLike WHERE mno = #{mno} AND bno = #{bno} ")
	void deleteBoardLike(@Param("mno")int mno, @Param("bno")int bno);
	
	// boardLike 테이블 데이터 추가 (커뮤니티 게시글 좋아요 추가)
	@Insert("INSERT INTO boardLike VALUES(0, #{mno}, #{bno}) ")
	void addBoardLike(@Param("mno")int mno, @Param("bno")int bno);
	
	@Select(" SELECT * FROM board WHERE bno = #{bno} ")
	BoardDTO getOne(int bno);
	
	// board 게시판 글 추가
	@Insert("INSERT INTO board VALUES(0,#{mno},#{btag},#{btitle},#{bcontents},0,sysdate() ) ")
	void addOne(BoardDTO dto);
	
	// board 게시판에 가장 최근 bno값 가져오기 (commWrite에서 사용)
	@Select("SELECT max(bno) FROM board ")
	int getMaxBno();
	
	// board 게시판 글 수정
	@Update(" UPDATE board SET btag =#{btag}, btitle =#{btitle}, bcontents =#{bcontents}  WHERE bno =#{bno} ")
	void boardModifyOne(BoardDTO dto);
	
	// board 게시판 글 삭제
	@Delete(" DELETE FROM board WHERE bno = #{bno} ")
	void deleteBoardOne(int bno);
	
	// boardComment 관련 method 
	
	@Select(" SELECT * FROM boardComment WHERE bno = #{bno} ")
	List<BoardCommentDTO> getCommentAll(int bno);
	
	// bno로 boardComment 개수 가져오기
	@Select(" SELECT count(*) FROM boardComment WHERE bno = #{bno} ")
	int getCommentCnt(int bno);
	
	// 커뮤니티 댓글 달기
	@Insert(" insert into boardComment values(0,#{bno},#{mno},#{bccontents},sysdate() ) ")
	void addComment(BoardCommentDTO dto);
	
	// 커뮤니티 댓글 수정
	@Update("UPDATE boardComment SET bccontents = #{bccontents} WHERE bcno = #{bcno} ")
	void modifyCommentOne(BoardCommentDTO dto);
	
	// 커뮤니티 댓글 삭제
	@Delete(" DELETE FROM boardComment WHERE bcno = #{bcno} ")
	void deleteCommentOne(int bcno);

	//마이페이지 내가쓴글
	@Select("select btitle, bdate, btag, bno from board where mno=#{mno} ")
	public List<BoardDTO> myPageBoard(int mno);
	

}
