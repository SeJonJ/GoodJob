package kr.co.goodjobproject.control;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.co.goodjobproject.auth.PrincipalDetails;
import kr.co.goodjobproject.dto.CompanyDTO;
import kr.co.goodjobproject.dto.MemberDTO;
import kr.co.goodjobproject.service.CompanyService;
import kr.co.goodjobproject.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileController {
	
	@Autowired
	MemberService ms;
	
	@Autowired
	CompanyService cs;

	
	@PostMapping("/myPage/insertImg")
	public String insertImg(@RequestPart MultipartFile files, @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest req ) throws IllegalStateException, IOException {
		
		String sourceFileName = files.getOriginalFilename();
//		System.out.println("파일명 : "+sourceFileName);
//		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//		File destinationFile;
//		String destinationFileName;
//		String fileUrl ="/uploadfile";
		System.out.println("파일명 : "+sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl ="/uploadfile";
		
		//절대경로
		fileUrl = req.getServletContext().getRealPath(fileUrl);
		
		/*
		 * do { destinationFileName =
		 * RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
		 * destinationFile = new File(fileUrl); }while(destinationFile.exists());
		 */
		
		
		destinationFile = new File(fileUrl+"/"+sourceFileName);
		
		System.out.println("경로 : "+destinationFile.getAbsolutePath());
		
		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
		
		String fileFullName = fileUrl +"/"+ sourceFileName;
		
//		String fileFullName = fileUrl +"/"+ sourceFileName;
//		"/goodjobproject/src/main/webapp/uploadfile/"+sourceFileName
		principalDetails.getMdto().setMimg(sourceFileName);
//		mdto.setMno(principalDetails.getMdto().getMno());
//		System.out.println("mno : "+principalDetails.getMdto().getMno());
		ms.uploadImg(sourceFileName, principalDetails.getMdto().getMno());
		
		
		return "redirect:/myPage/myPageMember";
		
	}
	
	@PostMapping("/myPage/insertImgCom")
	public String insertImgCom(@RequestPart MultipartFile files, @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest req ) throws IllegalStateException, IOException {
		
		String sourceFileName = files.getOriginalFilename();
		System.out.println(sourceFileName);
		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
		File destinationFile;
		String destinationFileName;
		String fileUrl ="/uploadfile";
		
		
		//절대경로
		fileUrl = req.getServletContext().getRealPath(fileUrl);
		
		/*
		 * do { destinationFileName =
		 * RandomStringUtils.randomAlphanumeric(32)+"."+sourceFileNameExtension;
		 * destinationFile = new File(fileUrl); }while(destinationFile.exists());
		 */
		
		
		destinationFile = new File(fileUrl+"/"+sourceFileName);
		
	
		
		System.out.println(destinationFile.getAbsolutePath());
		
		destinationFile.getParentFile().mkdirs();
		files.transferTo(destinationFile);
		
		String fileFullName = fileUrl +"/"+ sourceFileName;
		
//		cdto.setCimg(fileFullName);
//		cdto.setCno(principalDetails.getCdto().getCno());
		
		principalDetails.getCdto().setCimg(sourceFileName);
		//ms.uploadImg(sourceFileName, principalDetails.getMdto().getMno());
		
		cs.uploadImg(sourceFileName, principalDetails.getCdto().getCno());
		
		//cs.uploadImg(cdto);
		return "redirect:/myPage/myPageCompany";
	}
	
}