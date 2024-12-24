package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.MailSendDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/고객")
public class MailCtrl {
    private final MailSvc mailSvc;
    @Autowired
    public MailSendCtrl(MailSvc mailSvc){
        this.mailSvc = mailSvc;
    }

    public String MailSendCtrl(@ModelAttribute MailSendDTO mailSendDTO) {
        try{
            return mailSvc.mailSend(mailSendDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
