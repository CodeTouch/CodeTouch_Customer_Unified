package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateDTO {
    private String name;
    private String phone;
    private String nickname;
    private String password;
    private String birth;
    private Integer gender;
}
