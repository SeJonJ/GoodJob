package kr.co.goodjobproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.goodjobproject.dao.ResumeDAO;
import kr.co.goodjobproject.dto.ApplicationDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.dto.ResumeDTO;
import kr.co.goodjobproject.dto.WorkDTO;
import kr.co.goodjobproject.dto.WorkDivDTO;
import kr.co.goodjobproject.dto.ResumeListDTO;


@Service
public class ResumeService {

	@Autowired
	private ResumeDAO dao;
	
	public List<ResumeListDTO> getAll(int cno, int startNo){
		return dao.getAll(cno,startNo);
	}
	
	public int getTotal(int cno) {
		return dao.getTotal(cno);
	}
	
	public void addLike(int cno, int ano) {
		dao.addLike(cno, ano);
	}
	public void deleteLike(int cno, int ano) {
		dao.deleteLike(cno, ano);
	}
	public int checkLike(int cno, int ano) {
		return dao.checkLike(cno, ano);
	}
	
	public ResumeDTO getMember(int mno){
		return dao.getMember(mno);
	}
	
	public List<WorkDTO> getWorkInfo(int mno){
		return dao.getWorkInfo(mno);
	}
	
	public void insertResume(ApplicationDTO dto) {
		dao.insertResume(dto);
	}
	
	public void insertWork(int ano, int mno, WorkDivDTO wdto, int workCnt) {
		dao.delWork(mno);
		
		if(workCnt==1) {
			dao.insertWork(ano, mno, wdto.getWstart_1(), wdto.getWend_1(), wdto.getWinfo_1());
			
		}else if(workCnt==2) {
			dao.insertWork(ano, mno, wdto.getWstart_1(), wdto.getWend_1(), wdto.getWinfo_1());
			dao.insertWork(ano, mno, wdto.getWstart_2(), wdto.getWend_2(), wdto.getWinfo_2());

		}else if(workCnt==3) {
			dao.insertWork(ano, mno, wdto.getWstart_1(), wdto.getWend_1(), wdto.getWinfo_1());
			dao.insertWork(ano, mno, wdto.getWstart_2(), wdto.getWend_2(), wdto.getWinfo_2());
			dao.insertWork(ano, mno, wdto.getWstart_3(), wdto.getWend_3(), wdto.getWinfo_3());

		}
		
//		if(wdto.getWinfo_1()!="" || !wdto.getWinfo_1().equals("null")) {
//			System.out.println("null 아님");
//			dao.insertWork(ano, mno, wdto.getWstart_1(), wdto.getWend_1(), wdto.getWinfo_1());
//		}
//		if(wdto.getWinfo_2()!="" || !wdto.getWinfo_2().equals("null")) {
//			System.out.println("info2 null 아님");
//			dao.insertWork(ano, mno, wdto.getWstart_2(), wdto.getWend_2(), wdto.getWinfo_2());
//		}
//		if(wdto.getWinfo_3()!="" || !wdto.getWinfo_3().equals("null")) {
//			System.out.println("info3 null 아님");
//			dao.insertWork(ano, mno, wdto.getWstart_3(), wdto.getWend_3(), wdto.getWinfo_3());
//		}
//		if(wdto.getWinfo_4()!=null) {
//			dao.insertWork(ano, mno, wdto.getWstart_4(), wdto.getWend_4(), wdto.getWinfo_4());
//		}
//		if(wdto.getWinfo_5()!=null) {
//			dao.insertWork(ano, mno, wdto.getWstart_5(), wdto.getWend_5(), wdto.getWinfo_5());
//		}
	}
	
	public List<ApplicationDTO> myPageResume(int cno) {
		return dao.myPageResume(cno);
	}
	
	public List<String> myPageResumeName(int cno){
		return dao.myPageResumeName(cno);
	}
	
	public List<String> myPageResumeTI(int cno){
		return dao.myPageResumeTI(cno);
	}
	
	// 로그인한 일반유저가 이력서 작성했는지 확인
	public int checkResume(int mno) {
		return dao.checkResume(mno);
	}
	
	// ano로 이력서 하나 가져오기
	public ApplicationDTO applicationeGetOne(int ano) {
		return dao.applicationeGetOne(ano);
	}
}
