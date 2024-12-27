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
//public class SalesCtrl {
//    private final SalesSvc salesSvc;
//    @Autowired
//    public SalesCtrl(SalesSvc salesSvc){
//        this.salesSvc = salesSvc;
//    }
//
//    @ResponseBody
//    @PostMapping("/매출/조회")
//    public String salesCtrl(@ModelAttribute int site_id) {
//        try{
//            return salesSvc.readSales(site_id);
//        } catch (Exception e) {
//            throw new BadRequestException("");
//        }
//    }
//}
