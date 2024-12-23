package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.SiteDTO;
import com.tagmaster.codetouch.domain.SiteUpdateDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.SiteSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/고객")
public class SiteCtrl {
    private final SiteSvc siteSvc;

    @Autowired
    public SiteCtrl(SiteSvc siteSvc) {
        this.siteSvc = siteSvc;
    }
    @PostMapping("/사이트/수정")
    public String updateSite(SiteUpdateDTO dto) {
        try{
            return siteSvc.updateSetting(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
