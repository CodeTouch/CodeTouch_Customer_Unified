package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.service.PayHistorySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ModelAndView savePayHistory(PayHistoryDTO dto){
        String result = payHistorySvc.savePay(dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", result);
        return modelAndView;
    }
//    @PostMapping("/수정")
}
