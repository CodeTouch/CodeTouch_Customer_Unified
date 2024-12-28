package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDeleteDTO {
    private int site_id;
    private String type;
    private int post_id;
}
