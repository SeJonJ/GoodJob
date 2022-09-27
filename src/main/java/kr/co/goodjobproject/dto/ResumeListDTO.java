package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeListDTO {
	int ano;
	int mno;
	String atitle;
	String acontents;
	String aedu;
	String astart;
	String aend;
	String mname;
}
