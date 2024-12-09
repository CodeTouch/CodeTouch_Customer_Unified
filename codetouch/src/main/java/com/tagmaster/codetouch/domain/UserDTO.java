package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.minidev.json.JSONObject;

import java.util.Date;

@Getter
@Setter
@ToString
@Data
public class UserDTO {
    private int userId;
    private String id;
    private String password;
    private String email;
    private String name;
    private String phone;
    private Date birth; // ?
    private int gender; // ?
    private JSONObject address; // ?
    private int role; // ?
    private int mileage;
    private int business_num;
    private int report_num;

    public UserDTO(int userId,String id, String password, String email,String name,String phone,int gender,int role){
    }
}
