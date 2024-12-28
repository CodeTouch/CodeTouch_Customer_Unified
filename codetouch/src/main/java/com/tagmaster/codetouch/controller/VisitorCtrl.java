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
//public class VisitorCtrl {
//    private final VisitorSvc visitorSvc;
//    @Autowired
//    public VisitorCtrl(VisitorSvc visitorSvc){
//        this.visitorSvc = visitorSvc;
//    }
//
//    @ResponseBody
//    @PostMapping("/방문자/조회")
//    public String visitorCtrl(@ModelAttribute int site_id) {
//        try{
//            return visitorSvc.readVisitor(site_id);
//        } catch (Exception e) {
//            throw new BadRequestException("");
//        }
//    }
//}
