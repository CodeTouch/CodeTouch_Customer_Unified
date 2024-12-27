package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.domain.ProductReviewCreateDTO;
import com.tagmaster.codetouch.domain.ReviewReadDTO;
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
    public List<PostDTO> readProductsReview(@ModelAttribute ReviewReadDTO dto) {
        try {
            return reviewSvc.readProductsReview(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/상품/후기/조회/{site_id}/{pd_id}")
    public List<PostDTO> readProductReview(@ModelAttribute int site_id, @ModelAttribute int pd_id) {
        try {
            return reviewSvc.readProductReview(site_id, pd_id);
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
    @GetMapping("/상품/후기/검색/{site_id}/{content}")
    public List<PostDTO> searchProductReview(@ModelAttribute int site_id, @ModelAttribute String type, @ModelAttribute String content) {
        try {
            return reviewSvc.searchProductReview(site_id, type, content);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/상품/후기/삭제/{site_id}, {post_id}")
    public String deleteProductReview(@ModelAttribute int site_id, int post_id) {
        try {
            return reviewSvc.deleteProductReview(site_id, post_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
