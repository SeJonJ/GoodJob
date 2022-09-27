package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.goodjobproject.dto.JobDTO;

@Mapper
public interface JobDAO {
	
	// 직무 목록
	@Select("select jno, jtitle from jobcode order by jno ")
	List<JobDTO> getJobList();
	
	// 직무 1개 
	@Select("select jno, jtitle from jobcode where jno = #{jno} ")
	JobDTO getJob(int jno);

}
