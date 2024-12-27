package com.tagmaster.codetouch.domain;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class UserDTO {
    private int user_id;
    private int site_id;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private LocalDate birth;
    private int gender;
    private String address;
    private String role;
    private int mileage;
    private int agree;
    private int business_num;
    private int report_num;
}