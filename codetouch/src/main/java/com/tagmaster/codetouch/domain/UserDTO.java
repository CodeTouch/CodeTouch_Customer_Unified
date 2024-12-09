package com.tagmaster.codetouch.domain;

import lombok.*;
import net.minidev.json.JSONObject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int userId;
    private int siteId;
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
    //복합키 어카지 email , site // email 1개당 site 1개

    public UserDTO(int userId,String id, String password, String email,String name,String phone,int gender,int role){
    }
    // 일반유저

    // 관리자


}
