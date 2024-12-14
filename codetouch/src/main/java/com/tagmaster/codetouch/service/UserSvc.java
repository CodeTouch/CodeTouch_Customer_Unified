package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


import java.util.List;


@Service
public class UserSvc {
    UserMapper userMapper;
    public String SaveUser(UserDTO dto) {
        try {
                UserDTO save = userMapper.insertUser(dto);
                System.out.println("Mapper result: " + save);
            return "저장성공";
        } catch (Exception e) {
            return "저장실패";
        }
    }

    @Autowired
public UserSvc (UserMapper userMapper){
        this.userMapper=userMapper;
    }
    //사용자 생성

    // 사용자 개인정보 수정
    public String UpdateUser(UserDTO dto){
    try{
         if(dto!=null){
            UserDTO update = userMapper.updateUser(dto);
            update.setPassword(dto.getPassword());
            update.setName(dto.getName());
            update.setNickname(dto.getNickname());
            update.setPhone(dto.getPhone());
            update.setAddress(dto.getAddress());
            update.setAgree(dto.getAgree());
            userMapper.updateUser(update);
        }
        return "정보 수정 성공";
    } catch (Exception e) {
        return "정보 수정 실패";
    }
    }

    //권한 수정
    public String UpdateRole(UserDTO dto) {
        try{
            UserDTO update=userMapper.updateRole(dto);
            update.setRole(dto.getRole());
            userMapper.updateUser(update);
            return "권한 부여 성공";
        } catch (Exception e) {
            return "권한 부여 실패";
        }
    }

    //관리자 개인정보 수정
    public String UpdateAdmin(UserDTO dto){
        try{
            UserDTO update = userMapper.updateAdmin(dto);
            update.setPassword(dto.getPassword());
            update.setName(dto.getName());
            update.setNickname(dto.getNickname());
            update.setPhone(dto.getPhone());
            update.setAddress(dto.getAddress());
            update.setAgree(dto.getAgree());
            update.setBusiness_num(dto.getBusiness_num());
            update.setReport_num(dto.getReport_num());
            userMapper.updateAdmin(update);
            return "관리자 정보 수정 성공";
        } catch (Exception e) {
            return "관리자 정보 수정 실패";
        }
    }

    //사이트 모든 이용자 출력
    public List<UserDTO> showAllUser(int site_id){
        try{
            List<UserDTO> allUser =userMapper.showAllUser(site_id);
            return allUser;
        } catch (Exception e) {
            return null;
        }
    }

    //사이트 모든 이용자 메일 발송 // 수정필요함
    public String sendMail(int site_id,String title,String text){
        try{
            List<String> allMail = userMapper.sendMail(site_id);

            for (String email : allMail){
                SimpleMailMessage message =new SimpleMailMessage();
                message.setTo(email); // 수신자 설정
                message.setSubject(title); // 제목 설정
                message.setText(text); // 메일 내용
                //메일수신 //수정필요
            }
            return "메일 전송 성공";
        } catch (Exception e) {
            return "메일 전송 실패";
        }
    }

    //회원 탈퇴
    public String deleteUser(int site_id,String email){
        try{
            userMapper.deleteUser(site_id,email);
            return "회원 탈퇴";
    } catch (Exception e) {
            return "회원 탈퇴 실패";
        }
    }

    //사이트 이용자 조회
    public UserDTO searchUser (int site_id, String email){
        try{
            UserDTO result =userMapper.searchUser(site_id,email);
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
}

