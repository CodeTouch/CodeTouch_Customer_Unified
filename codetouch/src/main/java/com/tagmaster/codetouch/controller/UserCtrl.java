package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class UserCtrl {
    private final UserSvc userSvc;

    @Autowired
    public UserCtrl(UserSvc userSvc) {
        this.userSvc = userSvc;
    }

    // 사용자 생성
    @PostMapping("/회원/회원가입")
    @ResponseBody
    public String saveUser(@ModelAttribute SignupDTO dto, Model model) {
        try {
            return userSvc.SaveUser(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
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
    @PostMapping("/회원/권한수정")
    public String updateRole(@ModelAttribute UpdateRoleDTO dto) {
        try{
            return userSvc.updateRole(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 관리자 정보 수정
    @PostMapping("/관리자/수정")
    public String updateAdmin(@ModelAttribute UserDTO userDTO) {
        try {
            return userSvc.updateAdmin(userDTO);
        }catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 사이트 모든 이용자 출력
    @GetMapping("/회원리스트/{site_id}")
    public List<UserDTO> getAllUsers(@PathVariable int site_id) {
        try{
        return userSvc.showAllUser(site_id);
    } catch (Exception e) {
        throw new BadRequestException("");
        }
    }

    // 회원 탈퇴
    @PostMapping("/회원/삭제")
    public String deleteUser(
            @ModelAttribute DeleteDTO dto) {
        try{
            return userSvc.deleteUser(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    // 사이트 이용자 조회
    @GetMapping("/회원/조회/{site_id}/{email}")
    public UserDTO getUser(
            @PathVariable int site_id,
            @PathVariable String email) {
        try{
        return userSvc.searchUser(site_id, email);
    } catch (Exception e) {
        throw new BadRequestException("");
        }
    }
}