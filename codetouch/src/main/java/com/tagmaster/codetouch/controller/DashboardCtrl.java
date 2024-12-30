package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.DashboardSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/고객")
public class DashboardCtrl {
    private final DashboardSvc dashboardSvc;
    @Autowired
    public DashboardCtrl(DashboardSvc dashboardSvc){
        this.dashboardSvc = dashboardSvc;
    }


    @ResponseBody
    @PostMapping("/관리자/대시보드")
    public List<Object> saveProduct(@ModelAttribute int site_id) {
        try {
            List<Object> dashboard = new ArrayList<>();
            dashboard.add(dashboardSvc.getDashboardStatistics(site_id));
            dashboard.add(dashboardSvc.getDashboardStatistics(site_id));
            dashboard.add(dashboardSvc.countVisitor(site_id));
            return dashboard;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
