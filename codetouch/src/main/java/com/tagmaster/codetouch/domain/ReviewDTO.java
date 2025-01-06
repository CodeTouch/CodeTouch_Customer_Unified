package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDTO {
    private int post_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private String type;
    private String content;
    private String image;
    private int rating;
    private int count;
    private LocalDateTime create_at;
}
