package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserSvc {
    UserMapper userMapper;
    @Autowired
public UserSvc (UserMapper userMapper){
        this.userMapper=userMapper;
    }
public String SaveUser(UserDTO user) { //사이트 유저 생성
    try {
        userMapper.insertUser(user);

    } catch (Exception e) {
        return "저장실패";
    }
    return "저장 성공";
    }
}

