package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterProductDTO {
    private int site_id;
    private String pd_id;
    private String description;
    private String sale_percentage;
    private String category;
    private int stock;
}
