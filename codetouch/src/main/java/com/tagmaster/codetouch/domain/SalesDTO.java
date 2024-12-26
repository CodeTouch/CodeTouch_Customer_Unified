package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalesDTO {
    private int site_id;
    private int pd_id;
    private String sale_name; //추가필요
    private LocalDate sale_date; //추가필요
    private Integer sale_percentage;

}
