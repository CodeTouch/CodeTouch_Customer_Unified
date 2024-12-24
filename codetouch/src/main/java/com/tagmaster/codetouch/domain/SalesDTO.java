package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SalesDTO {
    private int site_id;
    private String sales_event_name;
    private LocalDate sales_date;
    private int pd_id;
}
