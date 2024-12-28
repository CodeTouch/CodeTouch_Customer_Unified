package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDTO {
    private int site_id;
    private String type;
    private String content;
}
