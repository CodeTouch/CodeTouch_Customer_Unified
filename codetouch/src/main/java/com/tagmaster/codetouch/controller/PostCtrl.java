package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PostAllReadDTO;
import com.tagmaster.codetouch.domain.PostSearchDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PostSvc;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/고객")
public class PostCtrl {
    private final PostSvc postSvc;
    public PostCtrl(PostSvc postSvc) {
        this.postSvc = postSvc;
    }
    @GetMapping("/게시글/전체조회")
    public String readPost(@ModelAttribute PostAllReadDTO dto) {
        try{
            return postSvc.getPostsBySiteId(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @PostMapping("/게시글/조회")
    public String readPost(@ModelAttribute PostSearchDTO dto) {
        try{
            return postSvc.getPostsByKeyword(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @PostMapping("/게시글/삭제")
    public String deletePost(@ModelAttribute ) {
        try{
            return postSvc.deletePost(dto);
        }
    }

}
