package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.AdminUpdateDTO;
import com.tagmaster.codetouch.domain.SignupDTO;
import com.tagmaster.codetouch.domain.UpdateDTO;
import com.tagmaster.codetouch.domain.UpdateRoleDTO;
import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tagmaster.codetouch.util.Util;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserSvc {
    UserMapper userMapper;

    @Autowired
    public UserSvc(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 사용자 생성
    // address 필드만 JSON으로 변환
    //String addressJson = Util.objectToJson(dto.getAddress());
    // JSON 변환된 address를 dto에 다시 세팅
    //dto.setAddress(addressJson);
//    public String SaveUser(SignupDTO dto) {
//        try {
//            int save = userMapper.insertUser(dto);
//            System.out.println("Mapper result: " + save);
//            return "회원가입 성공";
//        } catch (Exception e) {
//            return "회원가입 실패 "+e.getMessage();
//        }
//    }

    // 사용자 개인정보 수정
    public String updateUser(AdminUpdateDTO dto) {
        try {
            UserDTO update = userMapper.searchUser(dto.getSite_id(), dto.getEmail());
            String addressJson = Util.objectToJson(dto.getAddress());
            update.setAddress(addressJson);
            int alter = userMapper.updateUser(update);
            System.out.println("Mapper result : " + alter);
            return "개인정보 수정 성공";
        } catch (Exception e) {
            return "개인정보 수정 실패 " + e.getMessage();
        }
    }

    //권한 수정
    // 이메일 존재 체크 매퍼 : 존재 t.F else 예외
    public String updateRole(int site_id, String email, String role) {
        try {
            UserDTO update = userMapper.updateRole(site_id, email, role);
            update.setSite_id(site_id);
            update.setEmail(email);
            //update.setRole(role);
            int alter = userMapper.updateUser(update);
            System.out.println("Mapper result : " + alter);
            return "권한 부여 성공";
        } catch (Exception e) {
            return "권한 부여 실패" + e.getMessage();
        }
    }

    //관리자 개인정보 수정
   /* public String updateAdmin(UserDTO dto){
        try{
            UserDTO update = userMapper.updateAdmin(dto);
            int alter =userMapper.updateAdmin(update);
            return "관리자 정보 수정 성공";
        } catch (Exception e) {
            return "관리자 정보 수정 실패"+e.getMessage();
        }
    }*/

    //사이트 모든 이용자 출력
    public List<UserDTO> showAllUser(int site_id) {
        try {
            List<UserDTO> allUser = userMapper.showAllUser(site_id);
            System.out.println("Mapper result : " + allUser);
            return allUser;
        } catch (Exception e) {
            System.out.println("출력 실패" + e.getMessage());
            return null;
        }
    }

    //회원 탈퇴
    public String deleteUser(int site_id, String email) {
        try {
            userMapper.deleteUser(site_id, email);
            return "회원 탈퇴 완료";
        } catch (Exception e) {
            return "회원 탈퇴 실패" + e.getMessage();
        }
    }

    // 사용자 정보 조회
    public UserDTO searchUser(int site_id, String email) {
        try {
            UserDTO dto = userMapper.searchUser(site_id, email);
            return dto;
        } catch (Exception e) {
            System.out.println("사용자 조회 실패" + e.getMessage());
            return null;
        }
    }
}