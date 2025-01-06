package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.CustomUserDetails;
import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsSvc implements UserDetailsService {

    private final UserMapper userMapper;

    public CustomUserDetailsSvc(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        try {
            int user = userMapper.existByEmail(email);
            System.out.println(user + "userdetails 값");
            if (user > 0) {
                UserDTO data = userMapper.getUserByEmail(email);
                return new CustomUserDetails(data); //userdetails 를 만들어서 userservice 에서 최종적으로 authentication manager에 넘겨줄거다
            }
            return null;
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }
}
