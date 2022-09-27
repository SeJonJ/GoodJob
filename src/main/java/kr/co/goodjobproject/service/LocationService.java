package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.LocationDAO;
import kr.co.goodjobproject.dto.LocationDTO;

@Service
public class LocationService {
	
	@Autowired
	private LocationDAO dao;
	
	public List<LocationDTO> getLoc(LocationDTO dto){
		return dao.getLoc(dto);
	}
	
	public List<String> myPageHireLocation(int cno){
		return dao.myPageHireLocation(cno);
	}
	
}
