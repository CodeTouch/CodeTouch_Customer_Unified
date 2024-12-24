package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DeliveryDTO {
    private int site_id;
    private int minPrice;
    private LocalDateTime orderAutoExpiry;
    private String description;
}

