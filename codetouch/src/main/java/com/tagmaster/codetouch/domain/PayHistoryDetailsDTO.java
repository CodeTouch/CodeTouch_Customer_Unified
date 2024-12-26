package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayHistoryDetailsDTO {
    private int site_id;
    private int pd_id;
    private int user_id;
    private String image;
    private String user_name;
}
