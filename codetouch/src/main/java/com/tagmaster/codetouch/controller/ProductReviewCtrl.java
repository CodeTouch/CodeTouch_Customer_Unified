package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PostSvc;
import com.tagmaster.codetouch.service.ReviewSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class ProductReviewCtrl {
    private final PostSvc postSvc;
    private final ReviewSvc reviewSvc;


    @Autowired
    public ProductReviewCtrl(PostSvc postSvc, ReviewSvc reviewSvc) {
        this.postSvc = postSvc;
        this.reviewSvc = reviewSvc;
    }

    @ResponseBody
    @PostMapping("/상품/후기/전체조회")
    public List<PayHistoryDetailsDTO> readProductsReview(@ModelAttribute ProductsReviewReadDTO dto) {
        try {
            return reviewSvc.readProductsReview(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/조회")
    public List<PayHistoryDetailsDTO> readProductReview(@ModelAttribute ProductReviewReadDTO dto) {
        try {
            return reviewSvc.readProductReview(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/생성")
    public String createProductReview(@ModelAttribute ProductReviewCreateDTO productReviewCreateDTO) {
        try {
            return reviewSvc.reviewCreate(productReviewCreateDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/검색")
    public List<PayHistoryDetailsDTO> searchProductReview(@ModelAttribute ProductReviewSearchDTO dto) {
        try {
            return reviewSvc.searchProductReview(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/삭제")
    public String deleteProductReview(@ModelAttribute ProductReviewDeleteDTO productReviewDeleteDTO) {
        try {
            return reviewSvc.deleteProductReview(productReviewDeleteDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
