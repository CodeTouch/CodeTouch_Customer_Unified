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
    private String title; //추가
    private String nickname; //추가
    private String type; // ??? 진짜 기억이 안나 (카테고리? 게시물 타입 )
    private String content;
    private String image;
    private int rating;
    private int count;
}
