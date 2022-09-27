package kr.co.goodjobproject.service;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class FindPwMail implements MailServiceInter {

	@Autowired
	JavaMailSender emailsender;

	// 임시 패스워드
	private String tempPW;

	// 메일 내용 작성
	@Override
	public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub

		MimeMessage message = emailsender.createMimeMessage();

		message.addRecipients(RecipientType.TO, to);// 보내는 대상
		message.setSubject("GoodJob 계정 임시 패스워드");// 제목

		String msgg = "";
		msgg += "<div style='margin:100px;'>";
		msgg += "<h1> 안녕하세요</h1>";
		msgg += "<h1> 통합 취업 정보 포탈 GoodJob 입니다</h1>";
		msgg += "<br>";
		msgg += "<p>회원님의 임시 비밀번호 입니다<p>";
		msgg += "<p>해당 비밀번호로 로그인 후 패스워드 변경 부탁드립니다.<p>";
		msgg += "<br>";
		msgg += "<p>항상 당신의 꿈을 응원합니다. 감사합니다!<p>";
		msgg += "<br>";
		msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
		msgg += "<h3 style='color:blue;'>임시 비밀번호</h3>";
		msgg += "<div style='font-size:130%'>";
		msgg += "CODE : <strong>";
		msgg += tempPW + "</strong><div><br/> ";
		msgg += "</div>";
		message.setText(msgg, "utf-8", "html");// 내용
		message.setFrom(new InternetAddress("goodjobproject@naver.com", "GoodJob_Admin"));// 보내는 사람

		return message;
	}

	// 랜덤 값 생성
	@Override
	public String createKey() {
		StringBuffer key = new StringBuffer();
		Random rnd = new Random();

		for (int i = 0; i < 8; i++) { // 인증코드 8자리
			int index = rnd.nextInt(3); // 0~2 까지 랜덤

			switch (index) {
			case 0:
				key.append((char) ((int) (rnd.nextInt(26)) + 97));
				// a~z (ex. 1+97=98 => (char)98 = 'b')
				break;
			case 1:
				key.append((char) ((int) (rnd.nextInt(26)) + 65));
				// A~Z
				break;
			case 2:
				key.append((rnd.nextInt(10)));
				// 0~9
				break;
			}
		}
		return key.toString();
	}

	// 메일 발송
	@Override
	public String sendSimpleMessage(String to) throws Exception {
		tempPW = createKey();

		// TODO Auto-generated method stub
		MimeMessage message = createMessage(to);
		try {// 예외처리
			emailsender.send(message); 
		} catch (MailException es) {
			es.printStackTrace();
			throw new IllegalArgumentException();
		}
		return tempPW;
	}

}
