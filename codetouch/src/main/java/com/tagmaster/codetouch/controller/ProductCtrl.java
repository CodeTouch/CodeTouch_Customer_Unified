package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.ProductDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/고객")
public class ProductCtrl {
    private final ProductSvc productSvc;
    @Autowired
    public ProductCtrl(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }
    @ResponseBody
    @PostMapping("/상품")
    public String saveProduct(ProductDTO dto) {
        try {
            return productSvc.saveProduct(dto);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
