package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class ReviewDTO {
	int rno;
	int cno;
	int mno;
	int jno;
	
	String rtitle;
	int rstar;
	String rgood;
	String rbad;
	String regdate;

	
}
