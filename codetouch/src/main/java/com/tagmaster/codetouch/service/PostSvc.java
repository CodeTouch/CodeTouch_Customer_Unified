package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.mapper.PostMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSvc {
    PostMapper postMapper;

    @Autowired
    public PostSvc(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    // 게시글 생성
    public String savePost(PostDTO dto) {
        try {
            // 내용 필드를 JSON으로 변환 후 저장
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            int result = postMapper.insertPost(dto);
            return result > 0 ? "저장 성공" : "저장 실패";
        } catch (Exception e) {
            return "저장 실패 " + e.getMessage();
        }
    }

    // 게시글 수정
    public String updatePost(PostDTO dto) {
        try {
            // 내용 필드를 JSON으로 변환 후 업데이트
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            int result = postMapper.updatePost(dto);
            return result > 0 ? "수정 성공" : "수정 실패";
        } catch (Exception e) {
            return "수정 실패 " + e.getMessage();
        }
    }

    // 게시글 삭제
    public String deletePost(int postId) {
        try {
            int result = postMapper.deletePostById(postId);
            return result > 0 ? "삭제 성공" : "삭제 실패";
        } catch (Exception e) {
            return "삭제 실패 " + e.getMessage();
        }
    }

    // 게시글 ID로 조회
    public PostDTO getPostById(int postId) {
        try {
            PostDTO post = postMapper.getPostById(postId);
            // JSON 데이터를 Map으로 변환하여 반환
            post.setContent(Util.jsonToMap(post.getContent()).toString());
            return post;
        } catch (Exception e) {
            System.err.println("조회 실패 " + e.getMessage());
            return null;
        }
    }

    // 유저 ID로 게시글 조회
    public List<PostDTO> getPostByUserId(int userId) {
        try {
            return postMapper.getPostsByUserId(userId);
        } catch (Exception e) {
            System.err.println("조회 실패 " + e.getMessage());
            return null;
        }
    }

    // 키워드로 게시글 조회
    public List<PostDTO> getPostsByKeyword(String keyword) {
        try {
            return postMapper.getPostsByKeyword(keyword);
        } catch (Exception e) {
            System.err.println("조회 실패 " + e.getMessage());
            return null;
        }
    }
}


