
package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.domain.DesignPostDTO;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.DesignEditSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/고객")
public class DesignCtrl {
    private final DesignEditSvc designEditSvc;
    @Autowired
    public DesignCtrl(DesignEditSvc designEditSvc){
        this.designEditSvc = designEditSvc;
    }

    @PostMapping("/디자인저장")
    public void designEditCtrl(@RequestBody DesignEditDTO dto) {
        System.out.println("테스트용");
        try{
            designEditSvc.updateDesign(dto);
        } catch (Exception e) {
            throw new BadRequestException("디자인 저장/업데이트 실패:"+e.getMessage());
        }
    }

    @ResponseBody
    @GetMapping("/디자인불러오기/{url}")
    public DesignEditDBDTO designReadCtrl(@PathVariable String url){
        try {
            DesignEditDBDTO design = designEditSvc.readDesign(url);
            return design;
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/페이지게시")
    public void postDesignCtrl(@RequestBody int site_id){
        try{
            designEditSvc.insertPostDesign(site_id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/사이트편집/{url}")
    public String htmlCtrl (){
        return "editSite";
    }
}