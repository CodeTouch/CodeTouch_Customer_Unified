package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.DesignEditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/고객")
public class DesignCtrl {
    private final DesignEditSvc designEditSvc;
    @Autowired
    public DesignCtrl(DesignEditSvc designEditSvc){
        this.designEditSvc = designEditSvc;
    }

    @PostMapping("/디자인저장")
    public void designEditCtrl(@RequestBody DesignEditDTO dto) {
        System.out.println("테스트용");
        try{
            designEditSvc.insertDesign(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    @ResponseBody
    @GetMapping("/디자인불러오기/{site_id}")
    public DesignEditDBDTO designReadCtrl(@PathVariable int site_id){
        try {
            DesignEditDBDTO test = designEditSvc.readDesign(site_id);
            return test;
        } catch (Exception e) {
            return null;
        }
    }

}
