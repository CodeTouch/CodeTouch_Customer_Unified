package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class SignupDTO {
    private Integer site_id;
    private String email;
    private String name;
    private String phone;
    private String nickname;
    private String password;
    private LocalDate birth;
    private Integer gender;
}
