package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.goodjobproject.dto.JobcodeDTO;

@Mapper
public interface JobcodeDAO {

	@Select("SELECT jno, jtitle, jcode FROM jobcode")
	List<JobcodeDTO> selectAll();
	
}
