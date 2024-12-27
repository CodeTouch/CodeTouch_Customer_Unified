package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private String type; //ENUM "후기", "문의"
    private String content;
    private String image;
    private int rating;
    private int count;
    private LocalDateTime created_at; // 시간이 왜 없었지 ㅜㅜ
}
