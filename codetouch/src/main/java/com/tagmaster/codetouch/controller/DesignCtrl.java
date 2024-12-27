//package com.tagmaster.codetouch.controller;
//
//import com.tagmaster.codetouch.exception.BadRequestException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//@RequestMapping("/고객")
//public class DesignCtrl {
//    private final DesignEditSvc designEditSvc;
//    @Autowired
//    public DesignCtrl(DesignEditSvc designEditSvc){
//        this.designEditSvc = designEditSvc;
//    }
//
//    @ResponseBody
//    @PostMapping("/사이트/편집/저장")
//    public String designEditCtrl(@ModelAttribute int site_id) {
//        try{
//            return designEditSvc.insertDesign(site_id);
//        } catch (Exception e) {
//            throw new BadRequestException("");
//        }
//    }
//}
