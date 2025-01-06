package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DeliveryDTO {
//    private int setting_id;
    private int site_id;
    private int minPrice;
    private LocalDateTime order_auto_expiry;
    private String description;

}

