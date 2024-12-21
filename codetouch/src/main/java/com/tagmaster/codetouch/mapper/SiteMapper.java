package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {
    @Insert("insert into Site ( user_id ,name, favicon, main_image)values(#{user_id},#{name},#{favicon},#{main_image})")
    void createSet(SiteDTO dto);
    //사이트 기본 설정 생성

    @Update("Update Site set name=#{name}, favicon=#{favicon}, main_image=#{main_image} where site_id=#{site_id}")
    SiteDTO updateSet(SiteDTO dto);
    //사이트 기본 설정 수정
}
