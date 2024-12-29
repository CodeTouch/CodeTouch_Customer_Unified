package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteUpdateDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {

    @Update("Update Site set site_name=#{site_name}, favicon=#{favicon}, main_image=#{main_image}" +
            " WHERE site_id=#{site_id} And user_id=#{user_id}")
    int siteUpdate(SiteUpdateDTO updateDTO);

    @Select("select site_id from site where url=#{url}")
    int findSiteByUrl(String url);
}