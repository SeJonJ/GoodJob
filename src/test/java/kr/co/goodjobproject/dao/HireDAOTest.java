package kr.co.goodjobproject.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.jupiter.api.Test;

import kr.co.goodjobproject.dto.PageUtil;

class HireDAOTest {
	
	private HireDAO dao;
	private JobDAO jdao;
	
	/*@Test
	public void testGetListPaging() {
		PageUtil page = new PageUtil();
		
		 page.setPageNum(1); 
		
		List list = dao.getHireListPaging(page);
		
		list.forEach(board -> log.info("" + board));
		
		}*/
	
	/*
	 * @Test public void testGetListJon() { //System.out.println(jdao.getJobList());
	 * log.info(jdao.getJobList()); }
	 */

}
