package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HireListDTO {
	
	int hno;
	int cno;
	int bigno;
	int jno;
	int smallno;
	String cname;
	String htitle;
	String hdate;
	String hworkinfo;
	String jtitle;
	String bigname;
	String smallname;
	
}
