package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.runners.Parameterized.Parameters;

import kr.co.goodjobproject.dto.MemberDTO;

@Mapper
public interface MemberDAO {
	
	// 일반 member 추가
	@Insert("INSERT INTO member values("
			+ "0, #{tno}, #{mid}, #{mpwd}, #{mname}, #{mgender}, #{maddr}, #{mphone}, #{memail},"
			+ "0, null, 1, 'goodjob', 'ROLE_USER', #{mbirth}, 'true')")
	int insertGoodJobMember(MemberDTO dto);
	
	// sns member 추가
	@Insert("INSERT INTO member(mno, tno, mid, mname, mpay, snscheck, role, isNonExpired) "
			+ "values(0, #{tno}, #{mid}, #{mname}, #{mpay}, #{snsCheck}, 'ROLE_USER', 'true')")
	int insertSNSMember(MemberDTO dto);
	
	// 전체 member 가져오기
	@Select("select * from member")
	List<MemberDTO> getAll();
	
	// member 한명만 가져오기 TEST
	@Select("select * from member where mno=1")
	MemberDTO selectOne();
	
	@Select("select * from member where mid=#{mid}")
	MemberDTO getOne(@Param("mid")String mid);
	
	// 일반 유저 로그인
	@Select("select * from member where mid=#{mid}")
	MemberDTO goodjobLogin (@Param("mid")String mid);
	
	// sns 유저 로그인
	@Select("select * from member where mid=#{mid} and snscheck=#{snscheck}")
	MemberDTO getSnsOne(@Param("mid")String mid, @Param("snscheck")String snscheck);
	
	// 기업 유저 로그인
	@Select("select * from company where cid=#{cid}")
	MemberDTO companyLogin (@Param("cid")String cid);
	
	// 일반 유저 아이디 중복 검사
	@Select("SELECT MID FROM member WHERE MID= #{mid}")
	String checkIdUser(@Param("mid")String mid);
	
	@Select("SELECT CID FROM company WHERE CID = #{cid}")
	String checkIdCom(@Param("cid")String cid);
	
	// 일반 회원 아이디 찾기
	@Select("SELECT MID FROM member WHERE MNAME=#{mname} and MPHONE=#{mphone}")
	String findMemberId(@Param("mname")String mname, @Param("mphone")String mphone);

	// 일반 회원 패스워드 찾기 시 계정 정보 확인
	@Select("SELECT * FROM member WHERE MID=#{mid} and MNAME=#{mname} and MPHONE=#{mphone}")
	MemberDTO findMemberPwd(@Param("mid")String mid, @Param("mname")String mname, @Param("mphone")String mphone);

	// 일반 회원 패스워드 계정정보 확인 후 임시 패스워드로 변경
	@Update("UPDATE member set mpwd = #{mpwd} WHERE mno = #{mno}")
	int updateTempPw(@Param("mpwd")String mpwd, @Param("mno")int mno);
	
	//마이페이지 이미지 업로드
	@Update("UPDATE member SET mimg = #{mimg} WHERE mno = #{mno}")
	void uploadImg(@Param("mimg")String mimg, @Param("mno")int mno);
	
	
	@Select(" select mid, mname,mbirth from member where mno=2 ")
	List<MemberDTO> myPageMemberModify(int mno);
	
	//일반회원 정보수정
	@Update("update member set mbirth=#{mbirth}, mpwd = #{mpwd}, memail = #{memail}, mphone=#{mphone}, tno=#{tno} where mno=#{mno}")	
	int modifyBasicMember(MemberDTO dto);
	
	//일반회원 결제(멤버테이블)
	@Update("update member set mpay = 1 where mno=#{mno} ")
	void subscribe(int mno);
	
	//일반회원 구독취소
	@Update("update member set mpay = 0 where mno=#{mno}")
	void canclesub(int mno);
	
	//일반회원 결제(payment 테이블)
	@Insert("insert into payment values(0,#{mno},29000, sysdate() )")
	void subscribe2(int mno);
	
	
	@Select("select pdate from payment where mno = #{mno} order by pdate desc limit 1")
	String payDate(int mno);
	
	// 유저 삭제
	@Update("update member set isNonExpired = 'false' where mno = #{mno}")
	int deleteMember(@Param("mno")int mno);
	
	@Select(" select tname from topindustry where tno in(select tno from member where mno=#{mno}) ")
	String interest(int mno);
}





