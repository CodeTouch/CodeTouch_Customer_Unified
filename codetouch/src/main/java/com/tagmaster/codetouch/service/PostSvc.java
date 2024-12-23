package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.mapper.PostMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            int result = postMapper.insertPost(dto);
            return Util.checkExist(result, "저장 성공", "저장 실패", "게시글 저장 중 문제가 발생했습니다");
        } catch (Exception e) {
            return "저장 실패: " + e.getMessage();
        }
    }

    // 게시글 수정
    public String updatePost(PostDTO dto) {
        try {
            String contentJson = Util.objectToJson(dto.getContent());
            dto.setContent(contentJson);
            int result = postMapper.updatePost(dto);
            return Util.checkExist(result, "수정 성공", "수정 실패", "게시글이 존재하지 않습니다");
        } catch (Exception e) {
            return "수정 실패: " + e.getMessage();
        }
    }

    // 게시글 삭제
    public String deletePost(int postId) {
        try {
            int result = postMapper.deletePostById(postId);
            return Util.checkExist(result, "삭제 성공", "삭제 실패", "게시글이 존재하지 않습니다");
        } catch (Exception e) {
            return "삭제 실패: " + e.getMessage();
        }
    }

    // 게시글 ID로 조회
    public String getPostById(int postId) {
        try {
            PostDTO post = postMapper.getPostById(postId);
            int result = (post != null) ? 1 : 0;
            return Util.checkExist(result, "조회 성공", "조회 실패", "게시글이 존재하지 않습니다");
        } catch (Exception e) {
            return "조회 실패: " + e.getMessage();
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
}
