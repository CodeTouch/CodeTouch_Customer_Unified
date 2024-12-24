package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.SiteUpdateDTO;
import com.tagmaster.codetouch.mapper.SiteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SiteSvc {
    SiteMapper siteMapper;
    @Autowired
    public SiteSvc(SiteMapper siteMapper){
        this.siteMapper=siteMapper;
    }
    //사이트 기본 설정 생성
 /*   public String createSetting(SiteDTO siteDTO){
        try {
            siteMapper.insertSite(siteDTO);
            return "사이트의 기본 설정 정보추가 완료";
        } catch (Exception e) {
         return e.getMessage();
        }
    }*/
    // 수정
    public String updateSetting(SiteUpdateDTO siteDTO){
        try{
            siteMapper.siteUpdate(siteDTO);
            return "사이트 기본 설정 업데이트 완료";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
