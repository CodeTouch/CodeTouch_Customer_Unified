package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int site_id;
    private String name;
    private String category;
    private Integer price;
    private Integer sale_percentage;
    private String sale_name;
    private LocalDate sale_period;
    private String image;
    private int stock;
    private String description;
}
