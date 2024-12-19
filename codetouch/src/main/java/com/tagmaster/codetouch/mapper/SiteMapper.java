package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.SiteDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jdbc.repository.query.Query;

@Mapper
public interface SiteMapper {
    @Insert("insert into Site ( user_id ,site_name)values(#{user_id},#{site_name})")
    int insertSite(SiteDTO dto);
    //사이트 생성
//    @Insert("insert into Site ( user_id ,site_name/*,favicon,main_image, pay_state*/)values(#{user_id},#{site_name}/*#{favicon},#{main_image}, #{pay_state}*/)")
//    int insertSite(SiteDTO dto);

    //  @Update("Update Site set user_id=#{user_id}, site_name=#{site_name} /*favicon=#{favicon}, main_image=#{main_image}*/ WHERE site_id=#{site_id}")
//    SiteDTO siteUpdate(SiteDTO dto);
    //사이트 기본 설정 수정
    @Update("Update Site set user_id=#{user_id}, site_name=#{site_name} WHERE site_id=#{site_id}")
    int updateSite(SiteDTO dto);

}
