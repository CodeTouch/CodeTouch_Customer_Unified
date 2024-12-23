package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.SiteDTO;
import com.tagmaster.codetouch.service.SiteSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/사이트")
public class SiteCtrl {
    private final SiteSvc siteSvc;

    @Autowired
    public SiteCtrl(SiteSvc siteSvc) {
        this.siteSvc = siteSvc;
    }
    @PostMapping("/생성")
    public ModelAndView insertSite(SiteDTO dto) {
        String result = siteSvc.createSetting(dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/site"); //리다이렉트 늪에 빠지기 않게 조심
        modelAndView.addObject("result", result);
        return modelAndView;
    }
    @PostMapping("/수정")
    public ModelAndView updateSite(SiteDTO dto) {
        String result = siteSvc.updateSetting(dto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/site");
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
