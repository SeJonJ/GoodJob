package kr.co.goodjobproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FindPwMailTest {

    @Autowired
    FindPwMail mail;

    @Test
    void createMessage() {
    }

    @Test
    void createKey() {
    }

    @Test
    void sendSimpleMessage() throws Exception {
        mail.sendSimpleMessage("wkdtpwhs@naver.com");
    }
}