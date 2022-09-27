package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.JobDAO;
import kr.co.goodjobproject.dto.JobDTO;

@Service
public class JobService {
	
	@Autowired
	private JobDAO dao;
	
	public List<JobDTO> getJobList(){
		return dao.getJobList();
	}
	
	public JobDTO getJob(int jno){
		return dao.getJob(jno);
	}
	
}
