package kr.co.goodjobproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.goodjobproject.dto.SmallLocationDTO;

@Mapper
public interface SmallLocationDAO {
	
	@Select("SELECT smallno, smallname, smallcode FROM smalllocation")
	List<SmallLocationDTO> selectAll();

}
