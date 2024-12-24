package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterProductDTO {
    private int site_id;
    private String productName;
    private String productShortDescription;
    private String toolbox;
    private String productDetailDescription;
    private String AddOptions;
    private String salePercentage;
    private String delivery;
    private String category;
    private int stock;
}
