package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class HireDetailDTO {
	int hno;
	int cno;
	String htitle;
	String hworkinfo; // 경력조건
	String hedu; // 학력조건
	String hform; // 고용조건
	String hreq; // 필수요건
	String jtitle; // 분야
	String hsal; // 급여
	String hworkday; // 근무조건
	String hmain; // 상세내용
	String hdate; // 만료일자
	String hway; // 신청방법
	String cname; // 회사명
	String tname; // 업종
	String caddr; // 회사 주소
	String cphone; // 회사 대표 번호
	String cpeople; // 사원수
	String ceo; // 대표자명
	String csetup; // 설립일
	String chomepage; // 홈페이지 주소
	String csales; // 매출액
	String hapi; // api 여부
}
