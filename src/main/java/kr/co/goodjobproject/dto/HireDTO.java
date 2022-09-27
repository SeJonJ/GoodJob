package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder 
public class HireDTO {
	int hno;
	int cno;
	int bigno;
	int smallno;
	int jno;
	String hworkinfo;	//경력여부(신입,경력,경력무관)
	String hmain;  		//주요업무
	String hreq;		//필수/우대조건 
	String hedu;		//학력(고졸,대졸,,)
	String hform;		//근무형태(정규,계약,인턴)
	String hsal;		//급여(면접후,내규,직접작성)
	String hworkday;	//근무시간(근무요일)
	String hdate;		//접수기간
	String hway;		//접수방법(면접,이메일.etc)
	String htitle;		//채용 제목
	String hapi; 		// api 여부
	String cname;

}
