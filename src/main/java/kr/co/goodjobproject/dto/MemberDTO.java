package kr.co.goodjobproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDTO{
	int mno; 
	int tno;
	String tname;
	String mid;
	String mpwd;
	String mname;
	String mgender;
	String maddr; // 주소
	String mbirth; // 생년월일
	String mphone;
	String memail;
	int mpay; // 결제여부 1이면 결제 완료, 0 이면 미결제
	String snsCheck; // sns 여부
	String mimg; // 프로필 이미지
	String role; // 유저 권한
	String isNonExpired; // 계정 만료
	
	// sns 회원 회원가입 전용 생성자
	@Builder
	public MemberDTO(int tno, String mid, String mname, int mpay, String snsCheck) {
		this.tno = tno;
		this.mid = mid;
		this.mname = mname;
		this.mpay = mpay;
		this.snsCheck = snsCheck;
	}
	

}
