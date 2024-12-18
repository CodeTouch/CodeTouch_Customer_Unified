package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {
    @Insert("insert into Site ( user_id ,site_name,favicon,main_image)values(#{user_id},#{site_name},#{favicon},#{main_image})")
    int insertSiteSet(SiteDTO dto);
    //사이트 생성

    @Update("Update Site set site_name=#{site_name}, favicon=#{favicon}, main_image=#{main_image} where site_id=#{site_id}")
    SiteDTO siteUpdate(SiteDTO dto);
    //사이트 기본 설정 수정

}
