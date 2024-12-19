package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private int pd_id;
    private int site_id;
    private String name;
    private String category;
    private Integer price;
    // private Integer sale_percentage;
    private String image;
    // private Integer sale_state;
    private int stock;
    private String description;
    // private LocalDateTime create_at;
    // private LocalDateTime update_at;
}
