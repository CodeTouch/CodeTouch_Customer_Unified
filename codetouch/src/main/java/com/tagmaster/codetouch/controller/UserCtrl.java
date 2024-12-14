package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.service.UserSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserCtrl {
    @Autowired
    UserSvc userSvc;
    public UserCtrl(UserSvc userSvc) {
        this.userSvc = userSvc;
    }
    // 사용자 생성
    @PostMapping
    public ModelAndView saveUser(@RequestBody UserDTO userDTO) {
        String result = userSvc.SaveUser(userDTO);
        ModelAndView modelAndView = new ModelAndView("user/save");
        modelAndView.addObject("message", result);
        return modelAndView;
    }

    // 사용자 정보 수정
    @PostMapping("/update")
    public ModelAndView updateUser(@RequestBody UserDTO userDTO) {
        String result = userSvc.UpdateUser(userDTO);
        ModelAndView modelAndView = new ModelAndView("user/update");
        modelAndView.addObject("message", result);
        return modelAndView;
    }

    // 권한 수정
    @PostMapping("/updateRole")
    public ModelAndView updateRole(@RequestBody UserDTO userDTO) {
        String result = userSvc.UpdateRole(userDTO);
        ModelAndView modelAndView = new ModelAndView("user/updateRole");
        modelAndView.addObject("message", result);
        return modelAndView;
    }

    // 관리자 정보 수정
    @PostMapping("/updateAdmin")
    public ModelAndView updateAdmin(@RequestBody UserDTO userDTO) {
        String result = userSvc.UpdateAdmin(userDTO);
        ModelAndView modelAndView = new ModelAndView("user/updateAdmin");
        modelAndView.addObject("message", result);
        return modelAndView;
    }

    // 사이트 모든 이용자 출력
    @GetMapping("/{siteId}")
    public ModelAndView getAllUsers(@PathVariable int siteId) {
        List<UserDTO> users = userSvc.showAllUser(siteId);
        ModelAndView modelAndView = new ModelAndView("user/list");
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    // 회원 탈퇴
    @PostMapping("/delete")
    public ModelAndView deleteUser(
            @RequestParam int siteId,
            @RequestParam String email) {
        String result = userSvc.deleteUser(siteId, email);
        ModelAndView modelAndView = new ModelAndView("user/delete");
        modelAndView.addObject("message", result);
        return modelAndView;
    }

    // 사이트 이용자 조회
    @GetMapping("/{siteId}/{email}")
    public ModelAndView getUser(
            @PathVariable int siteId,
            @PathVariable String email) {
        UserDTO user = userSvc.searchUser(siteId, email);
        ModelAndView modelAndView = new ModelAndView("user/detail");
        modelAndView.addObject("user", user);
        return modelAndView;
}
}