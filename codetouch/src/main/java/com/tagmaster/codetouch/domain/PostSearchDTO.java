package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDTO {
    private int user_id;
    private int site_id;
    private int count;
    private String type;
    private String content;// 추가
}
