package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.MailSendDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/고객")
public class MailCtrl {
    private final DesignEditSvc designEditSvc;
    @Autowired
    public MailSendCtrl(DesignEditSvc designEditSvc){
        this.designEditSvc = designEditSvc;
    }

    public String MailSendCtrl(@ModelAttribute MailSendDTO mailSendDTO) {
        try{
            return designEditSvc.updateDesign(site_id);
        }
    }
}
