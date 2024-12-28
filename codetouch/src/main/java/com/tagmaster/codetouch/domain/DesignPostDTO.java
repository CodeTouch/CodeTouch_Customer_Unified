package com.tagmaster.codetouch.domain;

import lombok.Data;
@Data
public class DesignPostDTO {
        private final int posted_id;
        private int design_id;
        private int site_id;
        private String header;
        private String page;
        private String footer;
    }
