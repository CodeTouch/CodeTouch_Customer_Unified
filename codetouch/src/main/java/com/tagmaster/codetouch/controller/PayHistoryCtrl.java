package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.PayHistorySvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/결제내역")
public class PayHistoryCtrl {
    private final PayHistorySvc payHistorySvc;

    @Autowired
    public PayHistoryCtrl(PayHistorySvc payHistorySvc) {
        this.payHistorySvc = payHistorySvc;
    }
    @ResponseBody
    @PostMapping("/생성")
    public String savePayHistory(@ModelAttribute PayHistoryDTO dto){
        try{
        return payHistorySvc.savePay(dto);
    } catch (Exception e) {
        throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/조회")
    public PayHistoryDTO readPayHistory(@ModelAttribute int site_id, int user_id, int pay_id) {
        try {
            return payHistorySvc.readPay(site_id, user_id, pay_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
    @ResponseBody
    @PostMapping("/전체조회")
    public List<PayHistoryDTO> readPayHistoryList(@ModelAttribute int site_id, int user_id) {
        try{
            return payHistorySvc.readPayList(site_id, user_id);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
