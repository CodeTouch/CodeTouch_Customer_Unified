package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.domain.PostAllReadDTO;
import com.tagmaster.codetouch.mapper.PostMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
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
            postMapper.insertPost(dto);
            return "저장성공";
        } catch (Exception e) {
            return "저장 실패: " + e.getMessage();
        }
    }

    // 게시글 수정
    public String updatePost(PostDTO dto) {
        try {
            postMapper.updatePost(dto);
            return "수정 성공";
        } catch (Exception e) {
            return "수정 실패: " + e.getMessage();
        }
    }

    // 게시글 삭제
    public String deletePost(int site_id,int postId) {
        try {
            postMapper.deletePostById(site_id,postId);
            return "삭제 성공";
        } catch (Exception e) {
            return "삭제 실패: " + e.getMessage();
        }
    }

    // 전체 문의글
    // "문의" inquiry
    public List<PostDTO> getAllPosts(int site_id,String type,int pageNumber){
        try{
                int offset= (pageNumber-1) * 5; //1페이지당 5개씩 불러오기
                return postMapper.getAllPosts(site_id,5,offset);
        } catch (Exception e) {
            System.out.println("불러오기 실패"+e.getMessage());
            return null;
        }
    }

    // 키워드로 문의 게시글 조회
    public List<PostDTO> getPostsByKeyword(int site_id,String type,String content,int pageNumber) {
        try {
            int offset = (pageNumber-1) * 5;
            return postMapper.getPostsByKeyword(site_id,type,content,5,offset);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return Collections.emptyList();
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
            return posts;
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return Collections.emptyList();
        }
    }

}
