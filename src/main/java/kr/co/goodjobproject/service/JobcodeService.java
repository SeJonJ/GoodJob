package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.JobcodeDAO;
import kr.co.goodjobproject.dto.JobcodeDTO;

@Service
public class JobcodeService {
	
	@Autowired
	private JobcodeDAO dao;
	
	public List<JobcodeDTO> selectAll(){
		return dao.selectAll();
		
	}

}
