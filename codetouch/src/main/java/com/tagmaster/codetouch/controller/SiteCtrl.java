package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.DesignPostDTO;
import com.tagmaster.codetouch.domain.SiteUpdateDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.mapper.DesignPostMapper;
import com.tagmaster.codetouch.service.DesignEditSvc;
import com.tagmaster.codetouch.service.SiteSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/고객")
public class SiteCtrl {
    private final SiteSvc siteSvc;
    private final DesignEditSvc designEditSvc;

    @Autowired
    public SiteCtrl(SiteSvc siteSvc, DesignEditSvc designEditSvc) {
        this.siteSvc = siteSvc;
        this.designEditSvc =  designEditSvc;
    }

    //@PreAuthorize("hasAuthority('ADMIN,USER')")
    @PostMapping("/사이트/수정")
    @ResponseBody
    public String updateSite(@RequestBody SiteUpdateDTO dto) {
        try{
            return siteSvc.updateSetting(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    @GetMapping("/사이트/{url}")
    public String showSite(@PathVariable String url){
        return "postSite";
    }

    @GetMapping("/사이트/{url}/정보")
    @ResponseBody
    public DesignPostDTO getSiteData(@PathVariable String url){
        int site_id = siteSvc.findSite(url);
        DesignPostDTO siteData = designEditSvc.readDesignPost(site_id);
        return siteData;
    }
}
