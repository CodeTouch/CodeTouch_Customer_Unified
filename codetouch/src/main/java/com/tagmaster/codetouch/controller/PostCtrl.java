package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PostAllReadDTO;
import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.domain.PostDeleteDTO;
import com.tagmaster.codetouch.domain.PostSearchDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PostSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class PostCtrl {
    private final PostSvc postSvc;
    @Autowired
    public PostCtrl(PostSvc postSvc) {
        this.postSvc = postSvc;
    }
    @ResponseBody
    @PostMapping("/게시글/전체조회")
    public List<PostDTO> getAllPosts(@ModelAttribute int site_id, @ModelAttribute String type, @ModelAttribute int pageNumber) {
        try{
            return postSvc.getAllPosts(site_id, type, pageNumber);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
    @ResponseBody
    @PostMapping("/게시글/조회")
    public List<PostDTO> readPost(@ModelAttribute PostSearchDTO dto, @ModelAttribute int pageNumber) {
        try{
            return postSvc.getPostsByKeyword(dto, pageNumber);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
    @ResponseBody
    @PostMapping("/게시글/삭제")
    public String deletePost(@ModelAttribute PostDeleteDTO dto) {
        try{
            return postSvc.deletePost(dto);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage()+"");
        }
    }
}

