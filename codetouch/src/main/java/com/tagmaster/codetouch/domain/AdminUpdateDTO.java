package com.tagmaster.codetouch.domain;

import lombok.*;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class AdminUpdateDTO {
    private String password;
    private String name;
    private String nickname;
    private String phone;
    private String address;
    private String role="USER";
    private int mileage=0;
    private int business_num;
    private int report_num;
}