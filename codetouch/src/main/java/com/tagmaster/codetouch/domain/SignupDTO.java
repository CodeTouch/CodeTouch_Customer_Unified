package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupDTO {
    private String email;
    private String name;
    private String phone;
    private String role = "USER";
    private String nickname;
    private String password;
    private String birth;
    private Integer gender;
    private int mileage = 0;
}
