package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
	
	int bigno;
	int smallno;
	int smallcode;
	int bigcode;
	String bigname;
	String smallname;
}
