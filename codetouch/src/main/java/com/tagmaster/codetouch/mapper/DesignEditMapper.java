package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DesignEditMapper {
    @Insert("insert into design (site_id, header, page, footer) values (#{site_id}, #{header}, #{page}, #{footer})")
    int insertDesign(DesignEditDBDTO dto);

    @Select("select site_id,header,page,footer from design where site_id=#{site_id}")
    @Result(property = "site_id", column = "site_id")
    DesignEditDBDTO readDesign(int site_id);

    @Update("update design set header=#{header}, page=#{page}, footer=#{footer} where site_id=#{site_id}")
    int updateDesign(DesignEditDBDTO dto);

    @Select("select site_id from design where site_id=#{site_id}")
    Integer checkSiteId(int site_id);


}

