
package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/*

@Service
public class UserSvc {
    UserMapper userMapper;
    @Autowired
public UserSvc (UserMapper userMapper){
        this.userMapper=userMapper;
    }
public String SaveUser(UserDTO dto) { //사이트 유저 생성
    try {
        userMapper.insertUser(dto);

    } catch (Exception e) {
        return "저장실패";
    }
    return "저장 성공";
    }

    //개인정보 수정
    public String UpdateUser(UserDTO dto){
    try{
        UserDTO updateUser=userMapper.findByUserId(dto.getUserId());

    } catch (Exception e) {
        return "수정 실패";
    }
    return "수정 성공";
    }
}


