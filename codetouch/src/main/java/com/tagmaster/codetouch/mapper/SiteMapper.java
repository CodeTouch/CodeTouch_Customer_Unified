package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {
    @Insert("insert into Site ( user_id ,name,favicon,main_image)values(#{user_id},#{name},#{favicon},#{main_image})")
    void insertSiteSet(SiteDTO dto);

}
