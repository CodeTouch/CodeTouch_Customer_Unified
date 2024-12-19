package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tagmaster.codetouch.util.Util;

import java.util.List;

@Service
public class UserSvc {
    UserMapper userMapper;
    @Autowired
    public UserSvc (UserMapper userMapper){
        this.userMapper=userMapper;
    }
    // 사용자 생성
    // DTO type ->
    public String SaveUser(UserDTO dto) {
        try {
            // address 필드만 JSON으로 변환
            String addressJson = Util.objectToJson(dto.getAddress());
            // JSON 변환된 address를 dto에 다시 세팅
            dto.setAddress(addressJson);
            UserDTO save = userMapper.insertUser(dto);
            System.out.println("Mapper result: " + save);
            return "저장성공";
        } catch (Exception e) {
            return "저장실패";
        }
    }

    // 사용자 개인정보 수정
    public String UpdateUser(UserDTO dto){
    try{
         if(dto!=null){
             String addressJson = Util.objectToJson(dto.getAddress());
             dto.setAddress(addressJson);
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
    public String UpdateRole(int site_id, String email, String role) {
        try{
            UserDTO update=userMapper.updateRole(site_id, email, role);
            update.setSite_id(site_id);
            update.setEmail(email);
            //update.setRole(role);
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
            //update.setBusiness_num(dto.getBusiness_num());
            //update.setReport_num(dto.getReport_num());
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

    //회원 탈퇴
    public String deleteUser(int site_id,String email){
        try{
            userMapper.deleteUser(site_id,email);
            return "회원 탈퇴 완료";
        } catch (Exception e) {
            return "회원 탈퇴 실패";
        }
    }

    // 사용자 정보 조회
    public UserDTO searchUser(int site_id, String email) {
        try {
            UserDTO dto=userMapper.searchUser(site_id, email);
            return dto;
        } catch (Exception e) {
            return null;
        }
    }
}