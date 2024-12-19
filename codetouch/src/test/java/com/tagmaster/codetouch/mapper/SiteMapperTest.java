package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SiteMapperTest {
    @Autowired
    private SiteMapper siteMapper;

    @Test
    public void insertSiteSetTest(){
        SiteDTO dto = new SiteDTO();
        siteMapper.insertSiteSet(dto);
    }
}
