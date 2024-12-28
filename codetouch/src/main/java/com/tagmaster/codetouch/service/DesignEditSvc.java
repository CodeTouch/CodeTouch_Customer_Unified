package com.tagmaster.codetouch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.domain.DesignPostDTO;
import com.tagmaster.codetouch.mapper.DesignEditMapper;
import com.tagmaster.codetouch.mapper.DesignPostMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DesignEditSvc {
    DesignEditMapper designEditMapper;
    DesignPostMapper designPostMapper;

    @Autowired
    public DesignEditSvc(DesignEditMapper designEditMapper,DesignPostMapper designPostMapper) {
        this.designEditMapper = designEditMapper;
        this.designPostMapper =designPostMapper;
    }

    public String insertDesign(DesignEditDTO dto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String pageJson = objectMapper.writeValueAsString(dto.getPage());
            DesignEditDBDTO insertDB = new DesignEditDBDTO();
            insertDB.setSiteId(dto.getSite_id());
            insertDB.setPage(pageJson);
            insertDB.setHeader(dto.getHeader());
            insertDB.setFooter(dto.getFooter());
            designEditMapper.insertDesign(insertDB);
            return null;
        } catch (Exception e) {
            return e.getMessage() + "저장실패";
        }
    }

    public DesignEditDBDTO readDesign(int site_id) {
        try {
            DesignEditDBDTO result = designEditMapper.readDesign(site_id);
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDesign(DesignEditDTO dto) {
        try {
            Integer checkSiteId = designEditMapper.checkSiteId(dto.getSite_id());

            ObjectMapper objectMapper = new ObjectMapper();
            String pageJson = objectMapper.writeValueAsString(dto.getPage());

            DesignEditDBDTO result = new DesignEditDBDTO();
            result.setSiteId(dto.getSite_id());
            result.setPage(pageJson);
            result.setHeader(dto.getHeader());
            result.setFooter(dto.getFooter());

            if (Util.checkNull(checkSiteId) != null) {
                designEditMapper.updateDesign(result);
            }
            else {
                designEditMapper.insertDesign(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    ///게시하기 서비스

    public void insertPostDesign(int site_id){
        try{
            Integer checkSiteId = designPostMapper.checkPostSiteId(site_id);
            DesignEditDBDTO getDesign = designEditMapper.readDesign(site_id);

            DesignPostDTO result = new DesignPostDTO();
            result.setSite_id(getDesign.getSiteId());
            result.setPage(getDesign.getPage());
            result.setHeader(getDesign.getHeader());
            result.setFooter(getDesign.getFooter());

            if (Util.checkNull(checkSiteId) != null){
                    designPostMapper.updatePostDesign(result);
            }else {
                designPostMapper.insertPostDesign(result);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
