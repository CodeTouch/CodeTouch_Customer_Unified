package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.mapper.PayHistoryMapper;
import com.tagmaster.codetouch.mapper.PostMapper;
import com.tagmaster.codetouch.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ReviewSvc {
    private final ProductMapper productMapper;
    private final PostMapper postMapper;
    private final PayHistoryMapper payHistoryMapper;

    @Autowired
    public ReviewSvc(ProductMapper productMapper, PostMapper postMapper, PayHistoryMapper payHistoryMapper) {
        this.productMapper = productMapper;
        this.postMapper = postMapper;
        this.payHistoryMapper = payHistoryMapper;
    }

    public String reviewCreate(ProductReviewCreateDTO productReviewCreateDTO) {
        PayHistoryDTO payHistoryDTO = payHistoryMapper.getPayHistoryBySiteIdAndPdIdAndUserId(productReviewCreateDTO.getSite_id(), productReviewCreateDTO.getPd_id(), productReviewCreateDTO.getUser_id());
        ReceiptDTO receipts = productMapper.getPayHistoryDetails(payHistoryDTO.getSite_id(), payHistoryDTO.getPay_id());
        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setPd_id(receipts.getPd_id());
        reviewDTO.setUser_id(receipts.getUser_id());
        reviewDTO.setType(productReviewCreateDTO.getType());
        reviewDTO.setSite_id(receipts.getSite_id());
        reviewDTO.setContent(productReviewCreateDTO.getContent());
        reviewDTO.setImage(productReviewCreateDTO.getImage());
        reviewDTO.setRating(productReviewCreateDTO.getRating());
        if (productMapper.insertReview(reviewDTO) > 0) {
            return "후기 등록 성공";
        } else {
            return "후기 등록 실패";
        }
    }

    public List<PayHistoryDetailsDTO> readProductsReview(ProductsReviewReadDTO dto) {
        if (dto.getCheck() == 5) {
            return postMapper.getTop5PostsBySiteIdAndType(dto.getSite_id(), dto.getType());
        } else if (dto.getCheck() == 10) {
            return postMapper.getTop10PostsBySiteIdAndType(dto.getSite_id(), dto.getType());
        }
        return postMapper.getPostsBySiteIdAndType(dto.getSite_id(), dto.getType());
    }

    public List<PayHistoryDetailsDTO> readProductReview(ProductReviewReadDTO dto) {
        return postMapper.getPostsBySiteIdAndTypeAndPdId(dto);
    }

    public List<PayHistoryDetailsDTO> searchProductReview(ProductReviewSearchDTO dto) {
        return postMapper.getReviewByKeyword(dto);
    }

    public String deleteProductReview(ProductReviewDeleteDTO dto) {
        if (postMapper.deleteReviewById(dto) > 0) {
            return "삭제에 성공하였습니다.";
        }
        return "실패하였습니다.";
    }
}
