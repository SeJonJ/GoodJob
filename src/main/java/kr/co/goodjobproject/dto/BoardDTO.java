package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class BoardDTO {
	int bno;
	int mno;
	String btag; // 태그
	String btitle;
	String bcontents; 
	int hits; // 조회수
	String bdate; // 입력날짜
}
