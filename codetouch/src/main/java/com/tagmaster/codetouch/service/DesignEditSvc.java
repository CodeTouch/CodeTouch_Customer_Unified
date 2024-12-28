package com.tagmaster.codetouch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.mapper.DesignEditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DesignEditSvc {
    DesignEditMapper designEditMapper;

    @Autowired
    public DesignEditSvc(DesignEditMapper designEditMapper){
        this.designEditMapper=designEditMapper;
    }
    public String insertDesign(DesignEditDTO dto){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String pageJson = objectMapper.writeValueAsString(dto.getPage());
            DesignEditDBDTO insertDB = new DesignEditDBDTO();
            insertDB.setPage(pageJson);
            insertDB.setHeader(dto.getHeader());
            insertDB.setFooter(dto.getFooter());
            insertDB.setSite_id(dto.getSite_id());
            designEditMapper.insertDesign(insertDB);
            return "저장성공";
        } catch (Exception e) {
            return e.getMessage()+"저장실패";
        }
    }

    public DesignEditDBDTO readDesign(int site_id){
        try {
            DesignEditDBDTO result= designEditMapper.readDesign(site_id);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public DesignEditDTO updateDesign(DesignEditDTO dto){
        try {

            Integer checkSiteId = designEditMapper.checkSiteId(dto.getSite_id());
            if(checkSiteId != null){
                DesignEditDBDTO result = new DesignEditDBDTO();
                result.setHeader(dto.getHeader());
                result.setPage(dto.getPage().toString());
                result.setFooter(dto.getFooter());
                designEditMapper.updateDesign(result);
            }
        }
    }
}
