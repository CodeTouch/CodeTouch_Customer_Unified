package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PostAllReadDTO;
import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.domain.PostSearchDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PostSvc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class PostCtrl {
    private final PostSvc postSvc;
    public PostCtrl(PostSvc postSvc) {
        this.postSvc = postSvc;
    }
    @ResponseBody
    @GetMapping("/게시글/전체조회")
    public List<PostDTO> getAllPosts(@ModelAttribute int site_id, String type, int pageNumber) {
        try{
            return postSvc.getAllPosts(site_id,type,pageNumber);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
    @ResponseBody
    @PostMapping("/게시글/조회")
    public List<PostDTO> readPost(@ModelAttribute PostSearchDTO dto, int pageNumber) {
        try{
            return postSvc.getPostsByKeyword(dto.getSite_id(),dto.getType(),dto.getContent(),pageNumber);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
    @ResponseBody
    @GetMapping("/게시글/삭제/{site_id}/{post_id}")
    public String deletePost(@ModelAttribute int site_id, int post_id) {
        try{
            return postSvc.deletePost(site_id, post_id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
}

