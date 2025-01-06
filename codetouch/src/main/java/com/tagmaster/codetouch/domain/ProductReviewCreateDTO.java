package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductReviewCreateDTO {
    private int site_id;
    private int pd_id;
    private int user_id;
    private String type;
    private String content;
    private String image;
    private int rating;
}
