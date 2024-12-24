package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.ProductDTO;
import com.tagmaster.codetouch.domain.RegisterProductDTO;
import com.tagmaster.codetouch.domain.SalesDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class ProductCtrl {
    private final ProductSvc productSvc;
    @Autowired
    public ProductCtrl(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }
    @ResponseBody
    @PostMapping("/상품/등록")
    public String saveProduct(@ModelAttribute RegisterProductDTO registerProductDTO) {
        try {
            return productSvc.saveProduct(registerProductDTO);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @ResponseBody
    @PostMapping("/상품리스트/조회")
    public List<String> readProduct(@ModelAttribute int site_id) {
        try{
            return productSvc.readProducts(site_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/삭제/{pd_id}")
    public String deleteProduct(@ModelAttribute int pd_id) {
        try{
            return productSvc.deleteProduct(pd_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/할인/설정")
    public String setSalesPercentageProduct(@ModelAttribute SalesDTO salesDTO) {
        try {
            return productSvc.setSales(salesDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/카테고리/{site_id}/{category}")
    public String readProductCategory(@ModelAttribute int site_id, @ModelAttribute String category) {
        try{
            return productSvc.readByCategory(site_id, category);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/{site_id}")
    public String readProductReview(@ModelAttribute int site_id) {
        try {
            return productSvc.readProductReview(site_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/검색/{site_id}/{keyword}")
    public String searchProduct(@ModelAttribute int site_id, @ModelAttribute String content) {
        try {
            return productSvc.searchProduct(site_id, content);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/삭제/{site_id}/{post_id}")
    public String deleteProduct(@ModelAttribute int site_id, @ModelAttribute int post_id) {
        try {
            return productSvc.deleteProduct(site_id, post_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
