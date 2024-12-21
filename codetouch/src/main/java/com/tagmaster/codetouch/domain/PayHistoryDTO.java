package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PayHistoryDTO {
    private int pay_id;
    private int pd_id;
    private Integer merchant_id;
    private Integer total_price;
    private LocalDateTime create_at;
}
