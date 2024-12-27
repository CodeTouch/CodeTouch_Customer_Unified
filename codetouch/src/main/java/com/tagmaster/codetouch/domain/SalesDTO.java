package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalesDTO {
    private int site_id;
    private int pd_id;
    private String sale_name;
    private LocalDate sale_period;
    private Integer sale_percentage;
}
