package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.PageUtilReview;
import kr.co.goodjobproject.dto.ReviewDTO;
import kr.co.goodjobproject.dto.ReviewListDTO;
import kr.co.goodjobproject.dto.ReviewListnewDTO;

@Mapper
public interface ReviewDAO {
	
	@Select("SELECT * FROM Review")
	List<ReviewDTO> selectAll();
	
	@Select("SELECT * FROM Review WHERE cno = #{cno} ")
	List<ReviewDTO> selectOne(int cno);
	
	//기업평점평균
	@Select("SELECT ROUND(AVG(rstar),1) AS AVG, cno FROM Review WHERE cno = #{cno} order by cno desc;")
	double getAvg(int cno); 
	
	//마이페이지 내가쓴리뷰
	@Select("select r.rtitle, r.rstar, c.cname, r.rno, c.cno from Review r , company c where r.cno = c.cno and r.mno = #{mno}")
	List<ReviewListDTO> myPageReview(int mno);

	/* 게시판 목록(페이징 적용) */
    List<ReviewListnewDTO> getListPagingReview(PageUtilReview page);
	
    /* 게시판 총 갯수 */
    int getTotal(PageUtilReview page);
 
	
	/*
	//메인->기업리뷰
	//리뷰리스트(기업가져오기+평점포함+리뷰수포함)
	@Select("select c.cno, c.cname, c.caddr, t.tname, round(avg(r.rstar),0)as avg, COUNT(r.cno)as cnt "
			+ "from company c "
			+ "left outer join topindustry t on t.tno = c.tno "
			+ "left outer join Review r on r.cno = c.cno "
			+ "group by c.cno "
			+ "order by cno desc " )
	List<ReviewListnewDTO>getReviewList();
	
	//기업검색O + 정렬O
	@Select("select c.cno, c.cname, c.caddr, t.tname, round(avg(r.rstar),0)as avg, COUNT(r.cno)as cnt "
			+ "			from company c  "
			+ "			left outer join topindustry t on t.tno = c.tno  "
			+ "			left outer join Review r on r.cno = c.cno  "
			+ "         where c.cname like concat('%',#{search},'%') "
			+ "			group by c.cno  "
			+ "			order by #{order} desc ")
	List<ReviewListnewDTO>getSearchOrderList(String search, String order);
	
		
	//기업검색O + 정렬X
	@Select("select c.cno, c.cname, c.caddr, t.tname, round(avg(r.rstar),0)as avg, COUNT(r.cno)as cnt "
			+ "			from company c  "
			+ "			left outer join topindustry t on t.tno = c.tno  "
			+ "			left outer join Review r on r.cno = c.cno  "
			+ "            where c.cname like concat('%',#{search},'%') "
			+ "			group by c.cno  "
			+ "			order by cno desc ")
	List<ReviewListnewDTO>getSearchList(String search);
	
		
	//기업검색X + 정렬O(avg)
	@Select("select c.cno, c.cname, c.caddr, t.tname, round(avg(r.rstar),0)as avg, COUNT(r.cno)as cnt "
			+ "						from company c  "
			+ "						left outer join topindustry t on t.tno = c.tno  "
			+ "						left outer join Review r on r.cno = c.cno  "
			+ "						group by c.cno  "
			+ "						order by avg desc;")
	List<ReviewListnewDTO>getAvgList();
	
	//기업검색X + 정렬O(cnt)
	@Select("select c.cno, c.cname, c.caddr, t.tname, round(avg(r.rstar),0)as avg, COUNT(r.cno)as cnt "
			+ "						from company c  "
			+ "						left outer join topindustry t on t.tno = c.tno  "
			+ "						left outer join Review r on r.cno = c.cno  "
			+ "						group by c.cno  "
			+ "						order by cnt desc;")
	List<ReviewListnewDTO>getCntList();
	*/
	
	//기업리뷰 -> 기업리뷰디테일
	//리뷰디테일
	@Select("select c.cname,c.cno,r.mno, r.rno,r.rstar,r.rtitle, r.rgood, r.rbad, j.jtitle, r.regdate "
			+ "from Review r join company c "
			+ "on r.cno = c.cno "
			+ "join jobcode j  "
			+ "on r.jno = j.jno "
			+ "where c.cno = #{cno} ")
	List<ReviewListDTO>getOneComReviewList(int cno);
	
		
	//해당기업리뷰수
	@Select("select count(*) from Review where cno = #{cno} ")
	public int getReviewCnt(int cno);
	
	
	//모달에서 리뷰insert
	@Insert("insert into Review(cno, mno, jno, rbad, rgood, rstar, rtitle, regdate) "
			+ "values(#{cno}, #{mno}, #{jno}, #{rbad}, #{rgood}, #{rstar}, #{rtitle}, NOW()) ")
	public void insertReview(ReviewDTO dto);
	
	
	//리뷰 수정
	@Update("update Review set jno = #{jno}, rtitle = #{rtitle} , rstar = #{rstar} , rgood = #{rgood} ,rbad = #{rbad} where rno = #{rno} ")
	public void modifyReview(ReviewDTO dto);
	
	
	//리뷰 삭제
	@Delete("delete from Review where rno = #{rno} ")
	void deleteReview(int rno);

	

}
