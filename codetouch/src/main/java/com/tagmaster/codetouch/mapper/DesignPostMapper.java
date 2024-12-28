package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DesignEditDBDTO;
import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.domain.DesignPostDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DesignPostMapper {

    @Select("SELECT design_id, site_id, header, page, footer FROM posted_design WHERE site_id = #{site_id}")
    List<DesignPostDTO> readPostDesign(int site_id);

    //수정
    @Insert("Insert into posted_design ( site_id, header, page, footer)values ( #{site_id}, #{header}, #{page}, #{footer})")
    int insertPostDesign (DesignPostDTO dto);

    @Update("update posted_design set header=#{header}, page=#{page}, footer=#{footer} where site_id=#{site_id}")
    int updatePostDesign(DesignPostDTO dto);

    @Select("select site_id from posted_design where site_id=#{site_id}")
    Integer checkPostSiteId(int site_id);

}
