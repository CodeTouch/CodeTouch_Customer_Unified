package com.tagmaster.codetouch.domain;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SiteUpdateDTO {
    private int site_id;
    private int user_id;
    private String site_name;
    private String favicon;
    private String main_image;
}


