package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class ResumeDTO {
	
	int tno;
	String tname;
	String mbirth;
	String memail;
	String mname;
	String mgender;
	String maddr;
	String mphone;
	String mimg;
	String atitle;
	String aedu;
	String astart;
	String aend;
	String acontents;
	String winfo;
	String wstart;
	String wend;
}
