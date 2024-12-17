package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class PayHistoryDTO {
    private int pay_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private Integer order_num;
    private Integer total_price;
    private LocalDateTime create_at;
}
