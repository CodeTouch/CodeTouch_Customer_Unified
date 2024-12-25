package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.mapper.PayHistoryMapper;
import com.tagmaster.codetouch.mapper.PostMapper;
import com.tagmaster.codetouch.mapper.ProductMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PostSvc {
    PostMapper postMapper;
    ProductMapper productMapper;
    PayHistoryMapper payHistoryMapper;

    @Autowired
    public PostSvc(PostMapper postMapper, ProductMapper productMapper, PayHistoryMapper payHistoryMapper) {
        this.postMapper = postMapper;
        this.productMapper = productMapper;
        this.payHistoryMapper = payHistoryMapper;

    }
    // 게시글 생성
    public String savePost(PostDTO dto) {
        try {
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            postMapper.insertPost(dto);
            return "저장성공";
        } catch (Exception e) {
            return "저장 실패: " + e.getMessage();
        }
    }

    // 게시글 수정
    public String updatePost(PostDTO dto) {
        try {
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            postMapper.updatePost(dto);
            return "수정 성공";
        } catch (Exception e) {
            return "수정 실패: " + e.getMessage();
        }
    }

    // 게시글 삭제
    public String deletePost(int postId) {
        try {
            int result = postMapper.deletePostById(postId);
            return "삭제 성공";
        } catch (Exception e) {
            return "삭제 실패: " + e.getMessage();
        }
    }

    // 게시글 ID로 조회
    public PostDTO getPostById(int postId) {
        try {
            PostDTO post = postMapper.getPostById(postId);
            return post;
        } catch (Exception e) {
            System.out.println("조회 실패");
            return null;
        }
    }

    // 유저 ID로 게시글 조회
    public List<PostDTO> getPostByUserId(int userId) {
        try {
            List<PostDTO> posts = postMapper.getPostsByUserId(userId);
            return Util.checkNull(posts);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    // 키워드로 게시글 조회
    public List<PostDTO> getPostsByKeyword(String keyword) {
        try {
            List<PostDTO> posts = postMapper.getPostsByKeyword(keyword);
            return Util.checkNull(posts);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return Collections.emptyList();
        }
    }
//    public String reviewCreate(ProductReviewCreateDTO productReviewCreateDTO) {
//       PayHistoryDTO payHistoryDTO = payHistoryMapper.getPayHistory(productReviewCreateDTO.getPay_id);
//       PayHistoryDetailsDTO receipts = productMapper.getPayHistoryDetails(payHistoryDTO.getPay_id());
//       PostDTO postDTO = new PostDTO();
//       postDTO.setPd_id(receipts.getPd_id());
//       postDTO.setUser_id(receipts.getUser_id());
//       postDTO.setSite_id(receipts.getSite_id());
//       postDTO.setPd_image();
//       postDTO.setContent(dto.getContent());
//       postDTO.setTitle(dto.getTitle());
//       postDTO.setImage(dto.getImage());
//       postDTO.setPd_image(productDTO);
//        return (productMapper.getPayHistoryDetails(dto.get) > 0)
//    }
    public List<PostDTO> readProductReview(ProductReviewDTO dto){
        return postMapper.getPostsBySiteId(dto.getSite_id(), dto.getUser_id());
    }
    public List<PostDTO> searchProductReview(String content){
        return postMapper.getPostsByKeyword(content);
    }
    public String deleteProductReview(int post_id) {
        if (postMapper.deletePostById(post_id)>0){
            return "성공하였습니다.";
        }
        return "실패하였습니다.";
    }
}
