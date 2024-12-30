package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class VisitorCountDTO {
    private int user_id;
    private int site_id;
    private LocalDate create_at;
}
