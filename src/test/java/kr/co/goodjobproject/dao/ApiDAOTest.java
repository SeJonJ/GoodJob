package kr.co.goodjobproject.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.goodjobproject.api.InsertAPI;

@SpringBootTest
class ApiDAOTest {

	InsertAPI api = new InsertAPI();
	
	@Test
	void testInsertHire() {
		api.insertHire(1, 5, 5);
	}

}
