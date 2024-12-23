package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SiteMapperTest {
    @Autowired
    private SiteMapper siteMapper;

    @Test
    @Rollback(value = false)
    public void insertSiteTest(){
        SiteDTO dto = new SiteDTO();
        dto.setUser_id(1);
        dto.setSite_name("히준의 깜찍한 쇼핑몰 ><");
        siteMapper.insertSite(dto);
    }
    @Test
    @Rollback(value = false)
    public void updateSiteTest(){
        SiteDTO dto = new SiteDTO();
        dto.setSite_id(4);
        dto.setUser_id(1);
        dto.setSite_name("지오니행복가득쇼핑몰");
        siteMapper.updateSite(dto);
    }


}
