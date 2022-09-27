package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.goodjobproject.dto.BigLocationDTO;

@Mapper
public interface BigLocationDAO {
	
	@Select("SELECT bigno, bigname, bigcode FROM biglocation")
	List<BigLocationDTO> selectAll();

}
