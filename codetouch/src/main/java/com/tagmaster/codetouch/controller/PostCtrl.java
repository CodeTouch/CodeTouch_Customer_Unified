package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PostAllReadDTO;
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
    public List<String> readPost(@ModelAttribute PostAllReadDTO dto) {
        try{
            return postSvc.getPostsBySiteId(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/게시글/조회")
    public String readPost(@ModelAttribute PostSearchDTO dto) {
        try{
            return postSvc.getPostsByKeyword(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @GetMapping("/게시글/삭제/{site_id}/{post_id}")
    public String deletePost(@ModelAttribute int site_id, int post_id) {
        try{
            return postSvc.deletePost(site_id, post_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
