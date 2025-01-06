package com.tagmaster.codetouch.domain;

import com.tagmaster.codetouch.domain.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomUserDetails implements UserDetails {
     //생성자 방 식으로 초기화해야함
     private final UserDTO userDTO;
     public CustomUserDetails(UserDTO userDTO) {
         this.userDTO = userDTO;
     }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//role값
        System.out.println("CustomUserDetails.getAuthorities() 진입");
        Collection<GrantedAuthority> collection = new ArrayList<>();
        return collection;
    }

    @Override
    public String getPassword() { //비밀번호값
        return userDTO.getPassword();
    }
    @Override
    public String getUsername() {
        return userDTO.getEmail();
    }


    @Override
    public boolean isAccountNonExpired() { //expire되었는지
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //lock 되었는지
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getName() {
        return userDTO.getName();
    }

    public String getNickname() {
        return userDTO.getNickname();
    }

    public String getPhone() {
        return userDTO.getPhone();
    }
}
