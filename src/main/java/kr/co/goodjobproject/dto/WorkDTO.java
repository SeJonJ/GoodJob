package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class WorkDTO {
	int wno;
	int ano;
	int mno;
	String winfo; // 경력내용
	String wstart; // 입사년도
	String wend; // 퇴사년도
}
