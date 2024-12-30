package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.mapper.UserMapper;
import com.tagmaster.codetouch.mapper.VisitorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tagmaster.codetouch.util.Util;

import java.time.LocalDate;
import java.util.List;

@Service
public class UserSvc {
    private final VisitorMapper visitorMapper;
    UserMapper userMapper;
    DashboardSvc dashboardSvc;

    @Autowired
    public UserSvc(UserMapper userMapper, VisitorMapper visitorMapper, DashboardSvc dashboardSvc) {
        this.userMapper = userMapper;
        this.visitorMapper = visitorMapper;
        this.dashboardSvc = dashboardSvc;
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
    public String updateUser(UserDTO dto) {
        try {
            UserDTO update = userMapper.searchUser(dto.getSite_id(), dto.getEmail());
            update.setNickname(dto.getNickname());
            update.setEmail(dto.getEmail());
            update.setName(dto.getName());
            update.setPhone(dto.getPhone());
            String addressJson = Util.objectToJson(dto.getAddress());
            update.setAddress(addressJson);
            update.setBusiness_num(dto.getBusiness_num());
            update.setReport_num(dto.getReport_num());
            int alter = userMapper.updateUser(update);
            System.out.println("Mapper result : " + alter);
            return "개인정보 수정 성공";
        } catch (Exception e) {
            return "개인정보 수정 실패 " + e.getMessage();
        }
    }

    //권한 수정
    // 이메일 존재 체크 매퍼 : 존재 t.F else 예외
    public String updateRole(UpdateRoleDTO dto) {
        try {
            UserDTO userDTO = userMapper.searchUser(dto.getSite_id(), dto.getEmail());
            if (dto.getRole().equals("USER")) {
                dto.setRole("ADMIN,USER");
            } else {
                dto.setRole("USER");
            }
            dto.setEmail(userDTO.getEmail());
            UserDTO alter = userMapper.updateRole(dto);
            System.out.println("Mapper result : " + alter);
            return "권한 부여 성공";
        } catch (Exception e) {
            return "권한 부여 실패" + e.getMessage();
        }
    }

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

    // 모든 관리자 출력
    public List<UserDTO> showAdminUsers(int site_id, String role) {
        try {
            List<UserDTO> allAdmin = userMapper.showAdminUsers(site_id, role);
            System.out.println("Mapper result (Admins): " + allAdmin);
            return allAdmin;
        } catch (Exception e) {
            System.out.println("출력 실패: " + e.getMessage());
            return null;
        }
    }
    public UserDTO showAdminUser(UpdateRoleDTO dto) {
        try {
            UserDTO Admin = userMapper.showAdminUser(dto);
            System.out.println("Mapper result (Admins): " + Admin);
            return Admin;
        } catch (Exception e) {
            System.out.println("출력 실패: " + e.getMessage());
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
            return userMapper.searchUser(site_id, email);
        } catch (Exception e) {
            System.out.println("사용자 조회 실패" + e.getMessage());
            return null;
        }
    }
    public void findUser(String email, int site_id) {
        UserDTO dto = userMapper.getUserByEmail(email);
        VisitorCountDTO visitorCountDTO = new VisitorCountDTO();
        visitorCountDTO.setUser_id(dto.getUser_id());
        visitorCountDTO.setSite_id(site_id);
        dashboardSvc.insertVisitorCount(visitorCountDTO);
    }

}