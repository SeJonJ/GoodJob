package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewListnewDTO {
	int cno;
	int tno;
	String cname;
	String caddr;
	String tname;
	
	int avg; 
	int cnt;
	
	String search;
	String order;

}
