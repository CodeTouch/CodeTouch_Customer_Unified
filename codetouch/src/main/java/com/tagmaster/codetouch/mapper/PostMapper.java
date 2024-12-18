package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PostDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    //생성
    @Insert("INSERT INTO post (pd_id, user_id, type, content, image, rating) VALUES (#{pd_id}, #{user_id}, #{type}, #{content}, #{image}, #{rating})")
    void insertPost(PostDTO dto);
    //수정
    @Update("UPDATE post SET pd_id=#{pd_id}, user_id=#{user_id}, type=#{type}, content=#{content}, image=#{image}, rating=#{rating} WHERE post_id=#{post_id}")
    void updatePost(PostDTO dto);
    //삭제
    @Delete("DELETE FROM post WHERE post_id=#{post_id}")
    void deletePostById(int post_id);
    //게시글 아이디로 게시글 찾기
    @Select("SELECT pd_id, user_id, content, image, rating FROM post WHERE post_id=#{post_id}")
    PostDTO getPostById(int post_id);
    //유저 아이디로 게시글 찾기
    @Select("SELECT pd_id, content, image, rating FROM post WHERE user_id=#{user_id}")
    List<PostDTO> getPostsByUserId(int user_id);
    //내용에 키워드로 검색해 찾기
    @Select("SELECT pd_id, content, image, rating FROM post WHERE content LIKE CONCAT ('%', #{keyword}, '%')")
    List<PostDTO> getPostsByKeyword(String keyword);
    //별점 높은 순으로 게시글 가져오기
    @Select("SELECT pd_id, user_id, content, image FROM post WHERE pd_id=#{pd_id} ORDER BY rating DESC")
    List<PostDTO> getPostsByHighRated();
    //별점 낮은 순으로 게시글 가져오기
    @Select("SELECT pd_id, user_id, content, image FROM post WHERE pd_id=#{pd_id} ORDER BY rating ASC")
    List<PostDTO> getPostsByLowRated();

}
