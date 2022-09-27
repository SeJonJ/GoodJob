package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListDTO {
	int rno;
	int hno;
	int cno;
	int bigno;
	int jno;
	int samllno;
	int mno;
	String cname;
	String rstar;
	String rtitle;
	String rgood; 
	String rbad; 
	String smallname;
	String bigname;
	String jtitle;
	String hworkinfo;
	String regdate;
}
