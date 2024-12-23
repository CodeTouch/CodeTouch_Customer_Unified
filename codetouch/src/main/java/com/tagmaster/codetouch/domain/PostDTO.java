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
public class PostDTO {
    private int post_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private String title;
    private String type;
    private String title; //추가
    private String writer; //추가
    private String content;
    private String image;
    private int rating;
    private int count;
}
