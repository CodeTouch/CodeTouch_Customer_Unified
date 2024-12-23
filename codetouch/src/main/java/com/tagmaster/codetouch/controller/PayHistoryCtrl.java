package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PayHistorySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/결제내역")
public class PayHistoryCtrl {
    private final PayHistorySvc payHistorySvc;

    @Autowired
    public PayHistoryCtrl(PayHistorySvc payHistorySvc) {
        this.payHistorySvc = payHistorySvc;
    }
    @PostMapping("/생성")
    public String savePayHistory(@ModelAttribute PayHistoryDTO dto){
        try{
        return payHistorySvc.savePay(dto);
    } catch (Exception e) {
        throw new BadRequestException("");
        }
    }
//    @PostMapping("/수정")
}
