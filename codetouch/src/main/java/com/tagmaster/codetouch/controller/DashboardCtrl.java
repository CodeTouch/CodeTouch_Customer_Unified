package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/고객")
public class DashboardCtrl {
    private final DashboardSvc dashboardSvc;
    @Autowired
    public VisitorCtrl(DashboardSvc dashboardSvc){
        this.dashboardSvc = dashboardSvc;
    }


    @ResponseBody
    @PostMapping("/관리자/대시보드")
    public String saveProduct(@ModelAttribute int site_id) {
        try {
            return dashboardSvc.dashboard(site_id);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
