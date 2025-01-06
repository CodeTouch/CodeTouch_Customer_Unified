package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DesignEditDTO {
    private int design_id;
    private int site_id; // snake_case로 되어 있지만 JSON 매핑에 문제없음
    private String header; // HeaderDTO 타입의 필드
    private List<PageDTO> page;     // PageDTO 타입의 필드
    private String footer; // FooterDTO 타입의 필드
}

@Getter
@Setter
class PageDTO {
    private String id;        // 페이지 이름
    private String content; // 페이지 설명
}
