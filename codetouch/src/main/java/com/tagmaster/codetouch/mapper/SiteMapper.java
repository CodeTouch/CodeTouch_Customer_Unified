package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteInsertDTO;
import com.tagmaster.codetouch.domain.SiteUpdateDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {

    @Update("Update Site set site_name=#{site_name}, favicon=#{favicon}, main_image=#{main_image} WHERE site_id=#{site_id} And user_id=#{user_id}")
    int siteUpdate(SiteUpdateDTO updateDTO);
    //사이트 기본 설정 수정
//    @Update("Update Site set user_id=#{user_id}, site_name=#{site_name} WHERE site_id=#{site_id}")
//    int updateSite(SiteDTO dto);

}
