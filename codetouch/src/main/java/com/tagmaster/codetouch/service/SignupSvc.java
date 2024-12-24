/*
package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.SignupDTO;
import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SignupSvc {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public SignupSvc(UserMapper userMapper,
                     BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void SignupProcess(SignupDTO signupDTO) {
        String email = signupDTO.getEmail();
        int exist = userMapper.existByEmail(email);
        if (exist > 0) {
            return;
        }
        SignupDTO dto = new SignupDTO();
        dto.setNickname(signupDTO.getNickname());
        dto.setPassword(bCryptPasswordEncoder.encode(signupDTO.getPassword()));
        dto.setName(signupDTO.getName());
        dto.setPhone(signupDTO.getPhone());
        dto.setEmail(email);
        dto.setGender(signupDTO.getGender());
        dto.setBirth(dto.getBirth());
        userMapper.insertUser(dto);
    }

    public Boolean isEmailAvailable(String email) {
        return userMapper.existByEmail(email) == 0;
    }
}
*/
