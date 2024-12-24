package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.domain.RegisterProductDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.ProductSvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/고객")
public class ProductCategoryCtrl {
    private final ProductSvc productSvc;
    public ProductCategoryCtrl(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

}
