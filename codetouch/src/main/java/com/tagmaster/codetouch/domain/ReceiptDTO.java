package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReceiptDTO {
    private int site_id;
    private int pd_id;
    private int user_id;
    private String pd_name;
    private String email;
}
