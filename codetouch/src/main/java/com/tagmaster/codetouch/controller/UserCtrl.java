//package com.tagmaster.codetouch.controller;
//
//import com.tagmaster.codetouch.domain.UserDTO;
//import com.tagmaster.codetouch.service.UserSvc;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/user")
//@CrossOrigin(origins = "*")
//public class UserCtrl {
//    private final UserSvc userSvc;
//
//    @Autowired
//    public UserCtrl(UserSvc userSvc) {
//        this.userSvc = userSvc;
//    }
//    // 사용자 생성
//    @PostMapping
//    public ModelAndView saveUser(@RequestBody UserDTO userDTO) {
//        String result = userSvc.SaveUser(userDTO);
//        ModelAndView modelAndView = new ModelAndView("user/save");
//        modelAndView.addObject("message", result);
//        return modelAndView;
//    }
//
//    // 사용자 정보 수정
//    @PostMapping("/update")
//    public ModelAndView updateUser(@RequestBody UserDTO userDTO) {
//        String result = userSvc.UpdateUser(userDTO);
//        ModelAndView modelAndView = new ModelAndView("user/update");
//        modelAndView.addObject("message", result);
//        return modelAndView;
//    }
//
//    // 권한 수정
//    @PostMapping("/updateRole")
//    public ModelAndView updateRole(@RequestBody int site_id, @RequestBody String email, @RequestBody String role) {
//        String result = userSvc.UpdateRole(site_id, email, role);
//        ModelAndView modelAndView = new ModelAndView("user/updateRole");
//        modelAndView.addObject("message", result);
//        return modelAndView;
//    }
//
//    // 관리자 정보 수정
//    @PostMapping("/updateAdmin")
//    public ModelAndView updateAdmin(@RequestBody UserDTO userDTO) {
//        String result = userSvc.UpdateAdmin(userDTO);
//        ModelAndView modelAndView = new ModelAndView("user/updateAdmin");
//        modelAndView.addObject("message", result);
//        return modelAndView;
//    }
//
//    // 사이트 모든 이용자 출력
//    @GetMapping("/{site_id}")
//    public ModelAndView getAllUsers(@PathVariable int site_id) {
//        List<UserDTO> users = userSvc.showAllUser(site_id);
//        ModelAndView modelAndView = new ModelAndView("user/list");
//        modelAndView.addObject("users", users);
//        return modelAndView;
//    }
//
//    // 회원 탈퇴
//    @PostMapping("/delete")
//    public ModelAndView deleteUser(
//            @RequestParam int site_id,
//            @RequestParam String email) {
//        String result = userSvc.deleteUser(site_id, email);
//        ModelAndView modelAndView = new ModelAndView("user/delete");
//        modelAndView.addObject("message", result);
//        return modelAndView;
//    }
//
//    // 사이트 이용자 조회
//    @GetMapping("/{site_id}/{email}")
//    public ModelAndView getUser(
//            @PathVariable int site_id,
//            @PathVariable String email) {
//        UserDTO user = userSvc.searchUser(site_id, email);
//        ModelAndView modelAndView = new ModelAndView("user/detail");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//}
//}