package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.VisitorCountDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VisitorMapper {
    @Insert("INSERT INTO visitor_count (user_id, site_id) VALUES (#{user_id}, #{site_id})")
    VisitorCountDTO insertVisitorCount(VisitorCountDTO visitorCountDTO);

    @Select("SELECT create_at, count(user_id) FROM visitor_count WHERE site_id=#{site_id} GROUP BY create_at ORDER BY create_at")
    List<VisitorCountDTO> countVisitorsBySiteId(int site_id);
}
