package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.SmallLocationDAO;
import kr.co.goodjobproject.dto.SmallLocationDTO;

@Service
public class SmallLocationService {
	
	@Autowired
	private SmallLocationDAO dao;
	
	public List<SmallLocationDTO> selectAll(){
		return dao.selectAll();
	}

}
