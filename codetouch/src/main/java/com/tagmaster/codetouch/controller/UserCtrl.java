package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class UserCtrl {
    private final UserSvc userSvc;

    @Autowired
    public UserCtrl(UserSvc userSvc) {
        this.userSvc = userSvc;
    }
    // 사용자 정보 수정
    @PostMapping("/회원/수정")
    @ResponseBody
    public String updateUser(@ModelAttribute UserDTO dto) {
        try {
            return userSvc.updateUser(dto);

        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 권한 수정
    @ResponseBody
    @PostMapping("/회원/권한수정")
    public String updateRole(@ModelAttribute UpdateRoleDTO dto) {
        try{
            return userSvc.updateRole(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 사이트 모든 이용자 출력
    @GetMapping("/회원리스트/{site_id}")
    @ResponseBody
    public List<UserDTO> getAllUsers(@ModelAttribute int site_id) {
        try{
        return userSvc.showAllUser(site_id);
    } catch (Exception e) {
            System.out.println(e+"출력 실패");
        return null;
        }
    }
    @PostMapping("/관리자리스트")
    @ResponseBody
    public List<UserDTO> getAdminUsers(@ModelAttribute int site_id, String role) {
        try{
            return userSvc.showAdminUsers(site_id, role);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @PostMapping("/관리자")
    @ResponseBody
    public UserDTO getAdminUser(@ModelAttribute UpdateRoleDTO dto) {
        try{
            return userSvc.showAdminUser(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 회원 탈퇴
//    @PostMapping("/회원/삭제")
//    @ResponseBody
//    public String deleteUser(@ModelAttribute DeleteUserDTO dto) {
//        try{
//            return userSvc.deleteUser(dto);
//        } catch (Exception e) {
//            throw new BadRequestException("");
//        }
//    }

    // 사이트 이용자 조회
    @ResponseBody
    @GetMapping("/회원/조회/{site_id}/{email}")
    public UserDTO getUser(
            @ModelAttribute int site_id,
            @ModelAttribute String email) {
        try{
        return userSvc.searchUser(site_id, email);
    } catch (Exception e) {
        throw new BadRequestException("");
        }
    }
}
