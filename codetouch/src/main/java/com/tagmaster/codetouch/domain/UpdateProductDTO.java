package com.tagmaster.codetouch.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UpdateProductDTO {
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
