package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.BigLocationDAO;
import kr.co.goodjobproject.dto.BigLocationDTO;

@Service
public class BigLocationService {
	
	@Autowired
	private BigLocationDAO dao;
	
	public List<BigLocationDTO> selectAll(){
		return dao.selectAll();
	}

}
