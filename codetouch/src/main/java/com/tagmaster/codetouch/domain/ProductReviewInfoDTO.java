package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProductReviewInfoDTO {
    private String image;
    private String name;
    private String pd_image;
    private String content;
    private int rating;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
