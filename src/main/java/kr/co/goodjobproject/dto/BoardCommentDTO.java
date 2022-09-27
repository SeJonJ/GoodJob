package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class BoardCommentDTO {
	int bcno;
	int bno;
	int mno;
	String bccontents; // 내용 
	String bcdate; // 날짜
}
