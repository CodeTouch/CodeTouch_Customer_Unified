package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PostDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

@Mapper
public interface PostMapper {
    //생성
    @Insert("INSERT INTO post (pd_id, user_id, title, type, content, image, rating, created_at) VALUES (#{pd_id}, #{user_id}, #{title}, #{type}, #{content}, #{image}, #{rating}, #{created_at})")
    int insertPost(PostDTO dto);

    //수정
    @Update("UPDATE post SET pd_id=#{pd_id}, type=#{type}, content=#{content}, image=#{image}, rating=#{rating} WHERE post_id=#{post_id}")
    int updatePost(PostDTO dto);

    //삭제
    @Delete("DELETE FROM post WHERE post_id=#{post_id}")
    int deletePostById(int site_id,int post_id);

    //게시글 아이디로 게시글 찾기
    @Select("SELECT pd_id, user_id, type, content, image, rating FROM post WHERE post_id=#{post_id} and type='문의'")
    PostDTO getPostById(int post_id);

    //사이트 아이디로 전체 게시글들 찾기
    @Select("SELECT pd_id, user_id, type, title, content, image, rating " +
            "FROM post WHERE site_id=#{site_id} and type='문의'" +
            "ORDER BY created_at DESC LIMIT #{limit} OFFSET #{offset}")
    List<PostDTO> getAllPosts(int site_id, int limit, int offset );

    //유저 아이디로 게시글 찾기
    @Select("SELECT pd_id, content, image, rating FROM post WHERE user_id=#{user_id} and type='문의'")
    List<PostDTO> getPostsByUserId(int user_id);

    //상품 아이디로 게시글 찾기
    @Select("SELECT pd_id, content, image, rating FROM post WHERE pd_id=#{pd_id} and type='문의'")
    List<PostDTO> getPostsByProductId(int pd_id);

    //내용에 키워드로 검색해 찾기
    @Select("SELECT title, content, image, create_at FROM post " +
            "WHERE site_id = #{site_id} AND type = '문의' AND content LIKE CONCAT('%', #{content}, '%')")
    List<PostDTO> getPostsByKeyword(int site_id,String type,String content,int limit , int offset);

    //별점 높은 순으로 게시글 가져오기
    @Select("SELECT pd_id, type, user_id, content, image FROM post WHERE pd_id=#{pd_id} ORDER BY rating DESC")
    List<PostDTO> getPostsByHighRated(int pd_id);

    //별점 낮은 순으로 게시글 가져오기
    @Select("SELECT pd_id, type, user_id, content, image FROM post WHERE pd_id=#{pd_id} ORDER BY rating ASC")
    List<PostDTO> getPostsByLowRated(int pd_id);

 //join 으로 이메일 뽑아오기 //todo
}
