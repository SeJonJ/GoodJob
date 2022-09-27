package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.goodjobproject.dto.LocationDTO;

@Mapper
public interface LocationDAO {
	
	// 소분류 지역 조회
	@Select("select smallno, smallname "
			+ "from smalllocation " 
			+ "where bigno = #{bigno}")
	public List<LocationDTO> getLoc(LocationDTO dto);
	
	//마이페이지 공고 지역 가져오기
	@Select( "select bigname from biglocation where bigno in(select bigno from hire where cno in(select cno from company where cno=#{cno}))" )
	List<String> myPageHireLocation(int cno);
	
}
