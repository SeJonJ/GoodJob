package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class HireDetailLocDTO {
	
	String bigname; // 대분류
	String smallname; // 소분류
}
