package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DesignEditMapper {
    @Insert("insert into design (site_id, header, page, footer) values (#{site_id}, #{header}, #{page}, #{footer})")
    int insertDesign(DesignEditDBDTO dto);

    @Select("select * from design where site_id=#{site_id}")
    DesignEditDBDTO readDesign(int site_id);
}

