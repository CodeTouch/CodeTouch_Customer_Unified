package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PostSvc;
import com.tagmaster.codetouch.service.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class ProductCtrl {
    private final ProductSvc productSvc;
    private final PostSvc postSvc;
    @Autowired
    public ProductCtrl(ProductSvc productSvc, PostSvc postSvc) {
        this.productSvc = productSvc;
        this.postSvc = postSvc;
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
    public List<ProductDTO> readProduct(@ModelAttribute int site_id) {
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
    //카테고리로 나눠 설정맞추기
    @ResponseBody
    @GetMapping("/상품/카테고리/{category}")
    public ProductDTO readProductCategory(@ModelAttribute String category) {
        try{
            return productSvc.readByCategory(category);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기")
    public List<PostDTO> readProductReview(@ModelAttribute ProductReviewDTO productReviewDTO) {
        try {
            return postSvc.readProductReview(productReviewDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/생성")
    public String createProductReview(@ModelAttribute ProductReviewCreateDTO productReviewCreateDTO) {
        try {
            return postSvc.reviewCreate(productReviewCreateDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/검색/{keyword}")
    public List<PostDTO> searchProductReview(@ModelAttribute String content) {
        try {
            return postSvc.searchProductReview(content);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/삭제/{post_id}")
    public String deleteProductReview(@ModelAttribute int post_id) {
        try {
            return postSvc.deleteProductReview(post_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
