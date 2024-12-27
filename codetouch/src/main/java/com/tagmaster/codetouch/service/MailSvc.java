package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.MailSendDTO;
import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class MailSvc {

    private final UserMapper userMapper;

    @Autowired
    public MailSvc(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 단체 메일 발송
    public String mailSend(MailSendDTO dto) {
        try {
            // 사용자 목록 가져오기
            List<UserDTO> users = userMapper.showAllUser(dto.getSite_id());

            // 사용자 이메일로 메일 발송
            for (UserDTO user : users) {
                System.out.println("메일 발송 중: " + user.getEmail());
                // 실제 메일 발송 로직 추가
            }


            dto.setSend_at(LocalDateTime.now());
            return "단체 메일 발송 완료";
        } catch (Exception e) {
            return "메일 발송 실패: " + e.getMessage();
        }
    }
    // 메일 발송 메서드
    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
       // mailSender.send(message);
    }
}
