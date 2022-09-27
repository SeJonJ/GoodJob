package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder 
public class CompanyDTO {
	int cno;
	int tno;
	String cid;
	String cpwd;
	String cname;
	String caddr;
	String cphone;
	int cpeople; // 총 사원수  
	String ceo; // 대표자
	String cmanager; // 담당자
	String csetup; // 설립일
	String chomepage; // 홈페이지
	String csales; // 매출액
	String cbin; // 사업자번호
	String cemail; // 이메일
	String cimg; // 이미지
	String role; // 기업 권한
	String isNonExpired; // 계정 만료

}
