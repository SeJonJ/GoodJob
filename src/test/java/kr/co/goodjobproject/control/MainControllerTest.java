package kr.co.goodjobproject.control;


import java.util.List;

import org.apache.ibatis.logging.Log;
import org.junit.jupiter.api.Test;

import kr.co.goodjobproject.dao.HireDAO;
import kr.co.goodjobproject.dto.HireListDTO;
import kr.co.goodjobproject.dto.PageUtil;

class MainControllerTest {
	
	private HireDAO dao;

	@org.junit.Test
	public void testSearch() {
		PageUtil page = new PageUtil();
		List<HireListDTO> list = dao.getRecentHire();
		//list.forEach(b -> log.info(b));
	}

}
