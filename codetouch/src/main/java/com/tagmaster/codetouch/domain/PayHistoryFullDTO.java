package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PayHistoryFullDTO {
    private int pay_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private Integer merchant_id;
    private LocalDateTime create_at;
}

