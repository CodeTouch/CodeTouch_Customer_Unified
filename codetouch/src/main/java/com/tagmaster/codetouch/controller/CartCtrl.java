package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.service.CartSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/고객")
public class CartCtrl {
    private final CartSvc cartSvc;
    @Autowired
    public CartCtrl(CartSvc cartSvc) {
        this.cartSvc = cartSvc;
    }
}
