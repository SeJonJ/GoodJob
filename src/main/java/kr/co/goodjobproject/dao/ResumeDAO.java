package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;

import kr.co.goodjobproject.dto.ApplicationDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.dto.ResumeDTO;
import kr.co.goodjobproject.dto.WorkDTO;
import kr.co.goodjobproject.dto.WorkDivDTO;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import kr.co.goodjobproject.dto.ResumeListDTO;

@Mapper
public interface ResumeDAO {

	// 이력서열람 페이징 select 문
	@Select("select a.*, m.mname " 
			+ "from application a " 
			+ "        join member m " 
			+ "            on a.mno = m.mno "
			+ "        join company c " 
			+ "            on m.tno = c.tno " 
			+ "where c.cno = #{cno} "
			+ "order by a.ano desc "
			+ "limit #{startNo}, 15 ")
	public List<ResumeListDTO> getAll(@Param("cno") int cno, @Param("startNo") int startNo);

	// 이력서열람 총 데이터 수 가져오기
	@Select("select count(*) " + "from application a " + "        join member m " + "            on a.mno = m.mno "
			+ "        join company c " + "            on m.tno = c.tno " + "where c.cno = #{cno} ")
	public int getTotal(int cno);

	// Like 테이블 데이터 추가 (기업이력서 좋아요 추가)
	@Insert("INSERT INTO `like` VALUES(0, #{cno}, #{ano}) ")
	void addLike(@Param("cno") int cno, @Param("ano") int ano);

	// Like 테이블 데이터 삭제 (기업이력서 좋아요 삭제)
	@Delete("DELETE FROM `like` WHERE cno = #{cno} AND ano = #{ano} ")
	void deleteLike(@Param("cno") int cno, @Param("ano") int ano);

	// 로그인한 기업이 해당 이력서 좋아요 했는지 확인 (했으면 1, 아니면 0 return)
	@Select("SELECT count(*) FROM `like` WHERE cno = #{cno} AND ano = #{ano} ")
	int checkLike(@Param("cno") int cno, @Param("ano") int ano);

	// ano로 이력서 하나 가져오기
	@Select("SELECT * FROM application WHERE ano = #{ano} ")
	ApplicationDTO applicationeGetOne(int ano);
	
	// 이력서 기본정보 + 내용
	@Select("select distinct m.mbirth, m.memail, m.mname, m.mphone, m.mgender, m.maddr, m.mimg, t.tname, "
			+ "a.atitle, a.aedu, a.astart, a.aend, a.acontents "
			+ "from member m "
			+ "left outer join topindustry t on m.tno = t.tno "
			+ "left outer join application a on m.mno = a.mno "
			+ "left outer join work w on m.mno = w.mno "
			+ "where m.mno = #{mno} ")
	ResumeDTO getMember(@Param("mno")int mno);
	
	// 이력서 경력 사항 가져오기
	@Select("select w.winfo, w.wstart, w.wend "
			+ "from member m "
			+ "left outer join topindustry t on m.tno = t.tno "
			+ "left outer join application a on m.mno = a.mno "
			+ "left outer join work w on m.mno = w.mno "
			+ "where m.mno = #{mno}")
	List<WorkDTO> getWorkInfo(@Param("mno")int mno);
	
	// 이력서 경력사항 추가
	@Insert("insert into work(ano, mno, wno, wstart, wend, winfo) "
			+ "values(#{ano}, #{mno}, 0, #{wstart}, #{wend}, #{winfo})")
	void insertWork(@Param("ano")int ano, @Param("mno")int mno, @Param("wstart")String wstart, @Param("wend")String wend, @Param("winfo")String winfo);

		
	// 이력서 내용 추가
	@Insert("insert into application(ano, mno, acontents, aedu, astart, aend, atitle) " 
			+ "values (#{ano}, #{mno}, #{acontents}, #{aedu}, #{astart}, #{aend}, #{atitle})" 
			+ "on duplicate key update " 
			+ "aedu = #{aedu}, astart = #{astart}, aend = #{aend}, atitle = #{atitle}, acontents = #{acontents}")
	void insertResume(ApplicationDTO dto); 	
	
	// 이력서 경력 사항 수정을 위한 기존 경력 사항 삭제
	@Delete("delete from work where mno = #{mno}")
	void delWork(@Param("mno")int mno);
		
		
		
		//마이페이지 이력서 제목
		@Select("select atitle from application where ano in(select ano from `like` where cno = #{cno} ) " )
		List<ApplicationDTO> myPageResume(int cno);
		
		//마이페이지 이력서 작성자
		@Select(" select mname from member where mno in(select mno from application where ano in(select ano from `like` where cno = #{cno}) ) ")
		List<String> myPageResumeName(int cno);
		
		@Select(" select tname from topindustry where tno in(select tno from member where mno in(select mno from application where ano in(select ano from `like` where cno = #{cno} ) ) )  ")
		List<String> myPageResumeTI(int cno);
		
		// 로그인한 일반유저가 이력서 작성했는지 확인
		@Select(" select count(*) from application where mno = #{mno} ")
		int checkResume(int mno);
	
}
