package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.DesignEditDTO;
import com.tagmaster.codetouch.domain.DesignPostDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DesignPostMapper {

    @Select("select into posted_design (design_id, site_id, header, page, footer from posted_design where site_id = #{site_id}")
    List<DesignPostDTO> readPostDesign(int site_id);

    @Insert("Insert into posted_design (design_id, site_id, header, page, footer)values (#{design_id}, #{site_id}, #{header}," +
            "#{page}, #{footer})")
    int insertPostDesign (DesignEditDTO dto);
}
