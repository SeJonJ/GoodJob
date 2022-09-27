package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.CompanyDAO;
import kr.co.goodjobproject.dto.CompanyDTO;

@Service
public class CompanyService {

	@Autowired
	private CompanyDAO dao;
	
	public List<CompanyDTO> getAll(){
		return dao.getAll();	
	}
	
	public List<CompanyDTO> selectAll(){
		return dao.selectAll();
	}
	public String getCname(int cno) {
		return dao.getCname(cno);
	}
	public String getCno(int cno) {
		return dao.getCno(cno);
	}
	
	public List<CompanyDTO> allSelect(){
		return dao.allSelect();
		
	}
	
	// 기업 회원 insert
	public int addCompany(CompanyDTO dto) {
		int result = dao.insertCompany(dto);
		
		return result;
	}
	
	// 기업 아이디 찾기
	public String findCompanyId(String cbin, String cmanager) {
		return dao.findId(cbin, cmanager);
	}
	
	// 기업 패스워드 찾기
	public CompanyDTO findCompanyPwd(String cid, String cbin, String cmanager) {
		return dao.findPwd(cid, cbin, cmanager);
	}
	
	// 기업 임시 패스워드로 변경
	public int changeTempPw(int cno, String cpwd) {
		return dao.changeTempPw(cno, cpwd);
	}
	
	//기업 마이페이지
	public CompanyDTO myPageCompany(int cno) {
		return dao.myPageCompany(cno);
	}
	
	//기업 마이페이지 관심태그
	public String myPageCompanyTag(int cno) {
		return dao.myPageCompanyTag(cno);
	}
	
	//기업정보 수정
	public int modifyCompanyOk(CompanyDTO dto) {
		 return dao.modifyCompanyOk(dto);
	}
	// 기업 삭제
	public int delCompany(int cno) {
		return dao.deleteCompany(cno);
	}
	
	//기업마이페이지 파일업로드
	public void uploadImg(String fileName, int cno) {
		dao.uploadImg(fileName, cno);
	}

}
