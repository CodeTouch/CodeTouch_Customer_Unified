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
    ProductMapper productMapper;
    PostMapper postMapper;
    PayHistoryMapper payHistoryMapper;
    @Autowired
    public ReviewSvc(ProductMapper productMapper, PostMapper postMapper, PayHistoryMapper payHistoryMapper) {
        this.productMapper = productMapper;
        this.postMapper = postMapper;
        this.payHistoryMapper = payHistoryMapper;
    }
//    public String reviewCreate(ProductReviewCreateDTO productReviewCreateDTO) {
//        PayHistoryDTO payHistoryDTO = payHistoryMapper.getPayHistory(productReviewCreateDTO.getPay_id);
//        PayHistoryDetailsDTO receipts = productMapper.getPayHistoryDetails(payHistoryDTO.getPay_id());
//        PostDTO postDTO = new PostDTO();
//        postDTO.setPd_id(receipts.getPd_id());
//        postDTO.setUser_id(receipts.getUser_id());
//        postDTO.setSite_id(receipts.getSite_id());
//        postDTO.setPd_image();
//        postDTO.setContent(dto.getContent());
//        postDTO.setTitle(dto.getTitle());
//        postDTO.setImage(dto.getImage());
//        postDTO.setPd_image(productDTO);
//        return (productMapper.getPayHistoryDetails(dto.get) > 0);
//    }
    public List<PostDTO> readProductsReview(ReviewReadDTO dto){
        if (dto.getCheck() == 5){
            return postMapper.getTop5PostsBySiteId(dto.getSite_id());
        }
        else if(dto.getCheck() == 10){
            return postMapper.getTop10PostsBySiteId(dto.getSite_id());
        }
        return postMapper.getPostsBySiteId(dto.getSite_id());
    }

    public List<PostDTO> readProductReview(int site_id, String type, int pd_id){
        return postMapper.getPostsBySiteIdAndPdId(site_id, pd_id);
    }
    public List<PostDTO> searchProductReview(int site_id, String type, String content){
        return postMapper.getPostsByKeyword(site_id, content);
    }
    public String deleteProductReview(int site_id, int post_id) {
        if (postMapper.deletePostById(site_id, post_id) > 0){
            return "삭제에 성공하였습니다.";
        }
        return "실패하였습니다.";
    }
}
