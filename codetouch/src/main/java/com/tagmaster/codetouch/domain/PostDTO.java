package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class PostDTO {
    private int pay_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private String type;
    private String content;
    private String image;
    private int rating;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
