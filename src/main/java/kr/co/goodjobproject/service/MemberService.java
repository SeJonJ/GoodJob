package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.MemberDAO;
import kr.co.goodjobproject.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO mdao;
	
	// 전체 조회
	public List<MemberDTO> getAll(){
		return mdao.getAll();
	}
	
	// 아이디 중복 검사
	public String checkID(String mid, String type) { 
		if(type.equals("user")) {
			return mdao.checkIdUser(mid);
		}else if(type.equals("com")) {
			return mdao.checkIdCom(mid);
		}
		
		return null;
	}
	
	// 일반 유저 추가
	public int addGoodJobMember(MemberDTO dto) {
		
		if(mdao.insertGoodJobMember(dto) != 1) {
		return 0;
		}
		
		return 1;
	}
	
	// 
	public MemberDTO selectOne() {
		return mdao.selectOne();
	}
	
	public MemberDTO getOne(String mid) {
		return mdao.getOne(mid);
	}

	// 일반 회원 아이디 찾기
	public String findMemberId(String mname, String mphone) {
		return mdao.findMemberId(mname, mphone);
	}
	
	// 일반 회원 패스워드 찾기 -> 계정정보 가져오기
	public MemberDTO findMemberPwd(String mid, String mname, String mphone) {
		return mdao.findMemberPwd(mid, mname, mphone);
	}
	
	// 일반 회원 계정 정보 확인 후 임시 패스워드로 변경
	public int changeTempPw(String tempPwd, int mno) {
		return mdao.updateTempPw(tempPwd, mno);
	}
	
	//마이페이지 파일업로드
	public void uploadImg(String fileName, int mno) {
		mdao.uploadImg(fileName, mno);
	}
	
	//일반회원 마이페이지 수정
	public List<MemberDTO> myPageMemberModify(int mno){
		return mdao.myPageMemberModify(mno);
	}
	
	//일반회원 개인정보 수정
	public int modifyBasicMember(MemberDTO dto) {
		if(mdao.modifyBasicMember(dto) != 1 ) {
			return 0;
		}
		return 1;
	}
	
	//일반회원 결제
	public void subscribe(int mno) {
		mdao.subscribe(mno);
		mdao.subscribe2(mno);
	}
	
	//결제날짜 가져오기
	public String payDate(int mno) {
		return mdao.payDate(mno);
	}
	//구독취소
	public void canclesub(int mno) {
		mdao.canclesub(mno);
	}
	// 멤버 탈퇴
	public int delMember(int mno) {
		return mdao.deleteMember(mno);
	}
	
	//관심분야 가져오기
	public String interest(int mno) {
		return mdao.interest(mno);
	}
	
	
}
