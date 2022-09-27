package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.goodjobproject.dto.CompanyDTO;

@Mapper
public interface CompanyDAO {
	
	@Select("SELECT * FROM company")
	List<CompanyDTO> selectAll();
	
	
	@Select("SELECT * FROM company order by cno desc ")
	List<CompanyDTO> getAll();
	
	
	@Select("SELECT * FROM company order by cname asc ")
	List<CompanyDTO> allSelect();
	
	//기업이름만 
	@Select("select cname from company where cno = #{cno} ")
	public String getCname(int cno);
	//
	@Select("select cno from company where cno = #{cno} ")
	public String getCno(int cno);
	
	
	// 기업 회원 한명 insert
	@Insert("INSERT INTO company values("
			+ "0, #{tno}, #{cid}, #{cpwd}, #{cname}, #{caddr}, #{cphone},"
			+ "#{cpeople}, #{ceo}, #{cmanager}, #{csetup}, #{chomepage}, #{csales}, #{cbin},"
			+ "#{cemail}, null, 'ROLE_MANAGER', 'true')")
	int insertCompany(CompanyDTO dto);
	

	// 기업 계정 가져오기
	@Select("select * from company where cid=#{cid}")
	CompanyDTO getCompany(@Param("cid")String cid);
	
	// 기업 계정 아이디 찾기
	@Select("select cid from company where cbin=#{cbin} and cmanager=#{cmanager}")
	String findId(@Param("cbin")String cbin, @Param("cmanager")String cmanager);
	
	// 기업 계정 패스워드 찾기
	@Select("select * from company where cid=#{cid} and cbin=#{cbin} and cmanager=#{cmanager}")
	CompanyDTO findPwd(@Param("cid")String cid, @Param("cbin")String cbin, @Param("cmanager")String cmanager);
	
	// 기업 임시 패스워드 변경
	@Update("update company set cpwd = #{cpwd} where cno = #{cno}")
	int changeTempPw(@Param("cno")int cno, @Param("cpwd")String cpwd);
	
	//기업 마이페이지 
	@Select("select * from company where cno=#{cno}")
	CompanyDTO myPageCompany(int cno);
	
	//기업 마이페이지 태그
	@Select("select tname from topindustry where tno in(select tno from company where cno=#{cno})")
	String myPageCompanyTag(int cno);
	
	// 기업 삭제
	@Update("update company set isNonExpired = 'false' where cno = #{cno}")
	int deleteCompany(@Param("cno")int cno);
	
	//기업정보 수정
	@Update(" update company set cpwd=#{cpwd}, caddr=#{caddr}, cphone=#{cphone}, cpeople=#{cpeople}, cmanager=#{cmanager}, chomepage=#{chomepage}, cemail=#{cemail} where cno=#{cno} ")
	int modifyCompanyOk(CompanyDTO dto);
	
	//이미지 업로드
	@Update(" UPDATE company set cimg = #{cimg} where cno = #{cno} ")
	int uploadImg(@Param("cimg")String cimg, @Param("cno")int cno);
	
}
