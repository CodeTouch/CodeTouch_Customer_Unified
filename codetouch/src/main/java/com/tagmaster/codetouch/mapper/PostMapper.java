package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PostDTO;
import com.tagmaster.codetouch.domain.ProductReviewDTO;
import com.tagmaster.codetouch.domain.ProductReviewInfoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostMapper {
    //생성
    @Insert("INSERT INTO post (pd_id, user_id, type, content, image, title, rating, pd_image, create_at) VALUES (#{pd_id}, #{user_id}, #{type}, #{content}, #{image}, #{title}, #{rating}, #{pd_image}, #{create_at})")
    int insertPost(PostDTO dto);
    //수정
    @Update("UPDATE post SET pd_id=#{pd_id}, type=#{type}, content=#{content}, image=#{image}, rating=#{rating} WHERE post_id=#{post_id}")
    int updatePost(PostDTO dto);
    //삭제
    @Delete("DELETE FROM post WHERE site_id=#{site_id} And post_id=#{post_id}")
    int deletePostById(int site_id, int post_id);
    //게시글 아이디로 게시글 찾기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE post_id=#{post_id}")
    PostDTO getPostById(int post_id);
    //사이트 아이디로 전체 게시글들 찾기
    @Select("SELECT p.pd_id, u.user_id, p.type, p.content, p.image, p.title, p.rating, p.pd_image, p.create_at " +
            "FROM post p " +
            "JOIN user u ON u.user_id = p.user_id " +
            "WHERE p.site_id = #{site_id} AND u.email = #{email}")
    List<PostDTO> getPostsBySiteIdAndEmail(int site_id, String email);

    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE site_id=#{site_id}")
    List<PostDTO> getPostsBySiteId(int site_id);
    //5개 뽑기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at " +
            "FROM post " +
            "WHERE site_id = #{site_id} " +
            "ORDER BY rating DESC, create_at DESC " +
            "LIMIT 5")
    List<PostDTO> getTop5PostsBySiteId(int site_id);
    // 10개 뽑기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at " +
            "FROM post " +
            "WHERE site_id = #{site_id} " +
            "ORDER BY rating DESC, create_at DESC " +
            "LIMIT 10")
    List<PostDTO> getTop10PostsBySiteId(int site_id);

    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE site_id=#{site_id} And pd_id=#{pd_id}")
    List<PostDTO> getPostsBySiteIdAndPdId(int site_id, int pd_id);
    //유저 아이디로 게시글 찾기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE user_id=#{user_id}")
    List<PostDTO> getPostsByUserId(int user_id);
    //상품 아이디로 게시글 찾기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE pd_id=#{pd_id}")
    List<PostDTO> getPostsByProductId(int pd_id);
    //내용에 키워드로 검색해 찾기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE site_id=#{site_id} AND content LIKE CONCAT ('%', #{content}, '%')")
    List<PostDTO> getPostsByKeyword(int site_id, String content);
    //별점 높은 순으로 게시글 가져오기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE pd_id=#{pd_id} ORDER BY rating DESC")
    List<PostDTO> getPostsByHighRated(int pd_id);
    //별점 낮은 순으로 게시글 가져오기
    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE pd_id=#{pd_id} ORDER BY rating ASC")
    List<PostDTO> getPostsByLowRated(int pd_id);

}
