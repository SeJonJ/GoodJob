package kr.co.goodjobproject.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.goodjobproject.dto.BookmarkDTO;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.HireDetailDTO;
import kr.co.goodjobproject.dto.HireDetailLocDTO;

import kr.co.goodjobproject.dto.HireDTO;
import kr.co.goodjobproject.dto.HireListDTO;
import kr.co.goodjobproject.dto.PageUtil;
import kr.co.goodjobproject.dto.ReviewDTO;

@Mapper
public interface HireDAO {
	
//	@Insert("INSERT INTO hire(jno,hworkinfo,hmain,hreq,hedu,hform,hsal,hworkday,bigno,smallno,hdate,hway,htitle) "
//			+ "values(#{jno},#{hworkinfo},#{hmain},#{hreq},"
//			+ "#{hedu},#{hform},#{hsal},#{hworkday},#{bigno},#{smallno},#{hdate},#{hway},#{htitle}) " )
//	void insertAll();
	
	
	//공고등록insert
	@Insert("INSERT INTO hire(cno, jno, hworkinfo, hmain, hreq, hedu, hform, hsal, hworkday, bigno, smallno, hdate, hway, htitle) "
			+ "			values(#{cno}, #{jno}, #{hworkinfo}, #{hmain}, #{hreq}, "
			+ "			#{hedu}, #{hform}, #{hsal}, #{hworkday}, #{bigno}, #{smallno}, #{hdate}, #{hway}, #{htitle}) ")
	public void insertOne(HireDTO dto);
	
	//공고한개
	@Select("select h.hno, h.cno, h.jno, h.hworkinfo, h.hmain, h.hreq, h.hedu, hform, h.hsal, h.hworkday, h.bigno, h.smallno, h.hdate, h.hway, h.htitle, h.hapi, c.cname "
			+ "from hire h "
			+ "left outer join(select cname, cno from company)c "
			+ "on h.cno = c.cno "
			+ "where hno = #{hno} ")
	public HireDTO getOne(int hno);
		
	//공고수정 modify
	@Update("update hire "
			+ "set cno = #{cno}, jno = #{jno}, hworkinfo = #{hworkinfo}, hmain = #{hmain}, "
			+ "hreq = #{hreq}, hedu =#{hedu}, hform =#{hform} , hsal = #{hsal}, hworkday = #{hworkday}, bigno = #{bigno}, smallno = #{smallno}, "
			+ "hdate = #{hdate}, hway = #{hway}, htitle =  #{htitle} "
			+ "where hno = #{hno} ")
	public void modifyOne(HireDTO dto);

	//공고삭제
	@Delete("delete from hire where hno = #{hno} ")
	void deleteHireWriter(int hno);
	
	
	// main - 최근 등록한 공고
	@Select("SELECT c.cname, h.hno, h.htitle, left(hdate, 10) as hdate FROM company c, hire h WHERE c.cno = h.cno ORDER BY h.hno desc ")
	List<HireListDTO> getRecentHire();
	
	
	// hireList 목록 - hno순(최근 등록한 공고)
	@Select("select hno, htitle, hdate, hworkinfo, cname, jtitle, bigname, smallname "
			+ "from hire "
			+ "left outer join(select cno, cname from company)c "
			+ "on hire.cno = c.cno "
			+ "left outer join(select jno, jtitle from jobcode)j "
			+ "on hire.jno = j.jno "
			+ "left outer join(select bigno, bigname from biglocation)b "
			+ "on hire.bigno = b.bigno "
			+ "left outer join(select smallno, smallname from smalllocation)s "
			+ "on hire.smallno = s.smallno "
			+ "order by hno desc ")		
	List<HireListDTO> getHireList();

	List<HireListDTO> getHireListPaging(PageUtil page);
	
	// 게시물 총 개수 조회
	//@Select("select count(*) from hire ")
	int getTotal(PageUtil page);
	 
	
	//마이페이지 공고제목, 회사명, 마감일 조회
	@Select(" select cname from company where cno in (select cno from hire where hno in (select hno from bookmark where mno = #{mno})) ")
	List<CompanyDTO> getCname(int mno);
	
	@Select("select * from hire where hno in (select hno from bookmark where mno = #{mno}) ")
	List<HireDTO> getHname(int mno);
	
	@Select( "select hno, htitle, hdate, hworkinfo, hform from hire where cno in(select cno from company where cno=#{cno})" )
	List<HireDTO> myPageHire(int cno);
	
	
	// 채용상세페이지
	@Select("select c.cno, h.htitle, h.hworkinfo, h.hedu, h.hform, h.hreq, "
			+ "j.jtitle, h.hsal, h.hworkday, h.hmain, h.hdate, h.hway, c.cname, t.tname, c.caddr, c.cphone, c.cpeople, c.ceo, "
			+ "c.csetup, c.chomepage, c.csales, h.hapi "
			+ "from company c natural join hire h, jobcode j, topindustry t "
			+ "where hno = #{hno} and j.jno = h.jno and t.tno = c.tno")
	HireDetailDTO getHireDetails(@Param("hno")int hno);
	
	
	// 채용상세 페이지 지역 찾기
	@Select("select b.bigname, s.smallname from biglocation b natural join hire h, smalllocation s "
			+ "where h.hno = #{hno} and s.smallcode = h.smallno")
	HireDetailLocDTO getHireDetailLoc(@Param("hno")int hno);
	
	// 좋아요 추가
	@Insert("insert into bookmark values(0, #{mno}, #{hno})")
	int insertBookmark(@Param("mno")int mno, @Param("hno")int hno);
		
	// 좋아요 삭제
	@Insert("delete from bookmark where hno=#{hno} and mno=#{mno}")
	int deleteBookmark(@Param("mno")int mno, @Param("hno")int hno);
	
	// 좋아요 찾기
	@Select("select * from bookmark b where b.hno = #{hno} and b.mno = #{mno}")
	BookmarkDTO getLikeFlag(@Param("hno")int hno, @Param("mno")int mno);
	
	// hireDetail 페이지 좋아요 숫자 가져오기
	@Select(" select count(*) from bookmark where hno = #{hno} ")
	int getBookmarkOne(int hno);
	
	// main - 좋아요가 많은 공고
	@Select("select c.cname, h.hno, h.htitle, left(h.hdate, 10) as hdate, count(b.hno) as count "
			+ "from hire h "
			+ "left outer join company c on c.cno = h.cno "
			+ "left outer join bookmark b on b.hno = h.hno " 
			+ "group by h.hno order by count desc ")
	List<HireListDTO> getBookmarkHire();	

	
	// 기업 리뷰 하나만 가져오기
	@Select("select r.rtitle, r.rgood, r.rbad, r.regdate, round(avg(r.rstar),1) as rstar "
			+ "from Review r where r.cno = #{cno} group by r.cno order by regdate DESC LIMIT 1")
	public ReviewDTO getDetailReview(@Param("cno")int cno);
	
	//기업마이페이지 좋아요 갯수
	@Select(" select count(*) from bookmark where hno in(select hno from hire where cno=#{cno} ) ")
	public int getBookMarkNo(int cno);
}
