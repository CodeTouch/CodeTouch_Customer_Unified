package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CartDTO {
    private int cart_id;
    private int pd_id;
    private int user_id;
    private int site_id;
    private int count;
}
