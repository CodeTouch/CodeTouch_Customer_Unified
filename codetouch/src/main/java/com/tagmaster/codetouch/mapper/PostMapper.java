package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.*;
import org.apache.ibatis.annotations.*;
import java.awt.print.Pageable;
import java.util.List;

@Mapper
public interface PostMapper {
    //생성

    @Insert("INSERT INTO post (pd_id, user_id, title, type, content, image, rating) VALUES (#{pd_id}, #{user_id}, #{title}, #{type}, #{content}, #{image}, #{rating})")
    int insertPost(PostDTO dto);

    //게시글 아이디로 게시글 찾기
    @Select("SELECT u.email, p.title, p.content, p.image, p.rating FROM post p JOIN user u on u.user_id = p.user_id WHERE p.post_id=#{post_id} and p.type='문의'")
    PostDTO getPostById(int post_id);

    //삭제
    @Delete("DELETE FROM post WHERE site_id=#{site_id} AND type=#{type} AND post_id=#{post_id}")
    int deletePostById(PostDeleteDTO dto);

    //사이트 아이디로 전체 게시글들 찾기 - 수정 필요 (user email 찾아야함...) 완
    @Select("SELECT u.email, p.title, p.content, p.create_at " +
            "FROM post p JOIN user u ON p.user_id = u.user_id WHERE p.site_id=#{site_id} and p.type='문의'" +
            "ORDER BY create_at DESC LIMIT #{limit} OFFSET #{offset}")
    List<PostDTO> getAllPosts(int site_id, String type, int limit, int offset );


    @Select("SELECT p.title, p.type, p.content, p.image, p.rating, p.create_at, " +
            "       pd.name AS product_name, u.email AS user_email " +
            "FROM post p " +
            "JOIN product pd ON p.pd_id = pd.pd_id " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.site_id = #{site_id} " +
            "AND p.type = '후기'")
    List<PayHistoryDetailsDTO> getPostsBySiteIdAndType(int site_id, String type);

    //5개 뽑기
    @Select("SELECT p.title, p.type, p.content, p.image, p.rating, p.create_at, " +
            "       pd.name AS product_name, u.email AS user_email " +
            "FROM post p " +
            "JOIN product pd ON p.pd_id = pd.pd_id " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.site_id = #{site_id} " +
            "AND p.type = '후기' " +
            "ORDER BY p.rating DESC, p.create_at DESC " +
            "LIMIT 5")
    List<PayHistoryDetailsDTO> getTop5PostsBySiteIdAndType(int site_id, String type);
    // 10개 뽑기
    @Select("SELECT p.title, p.type, p.content, p.image, p.rating, p.create_at, " +
            "       pd.name AS product_name, u.email AS user_email " +
            "FROM post p " +
            "JOIN product pd ON p.pd_id = pd.pd_id " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.site_id = #{site_id} " +
            "AND p.type = '후기' " +
            "ORDER BY p.rating DESC, p.create_at DESC " +
            "LIMIT 10")
    List<PayHistoryDetailsDTO> getTop10PostsBySiteIdAndType(int site_id, String type);

    @Select("SELECT p.title, p.type, p.content, p.image, p.rating, p.create_at, " +
            "       pd.name AS product_name, u.email AS user_email " +
            "FROM post p " +
            "JOIN product pd ON p.pd_id = pd.pd_id " +
            "JOIN user u ON p.user_id = u.user_id " +
            "WHERE p.site_id = #{site_id} " +
            "AND p.type = '후기' " +
            "AND p.pd_id = #{pd_id}")
    List<PayHistoryDetailsDTO> getPostsBySiteIdAndTypeAndPdId(ProductReviewReadDTO dto);

    //유저 아이디로 게시글 찾기
//    @Select("SELECT u.email, p.content, p.image, p.title, p.rating, p.create_at FROM post p JOIN user u ON u.user_id = p.user_Id WHERE p.site_id=#{site_id} AND type='문의' AND p.user_id=#{user_id}")
//   List<PostDTO> getPostsByUserId(int site_id, String type, int user_id);

    //상품 아이디로 게시글 찾기
    @Select("SELECT pd.name, u.email, p.title, p.content, p.image, p.rating FROM post p JOIN user u ON p.user_id=u.user_id JOIN product pd ON p.pd_id=pd.pd_id WHERE pd_id=#{pd_id} and type='문의'")
    List<PostDTO> getPostsByProductId(int pd_id, String type);

    //내용에 키워드로 검색해 찾기
    @Select("SELECT u.email, p.title, p.content, p.image, p.create_at FROM post p JOIN user u ON p.user_id=u.user_id " +
            "WHERE p.site_id = #{site_id} AND p.type = '문의' AND content LIKE CONCAT('%', #{content}, '%')")
    List<PostDTO> getPostsByKeyword(PostSearchDTO dto, int limit , int offset);
  
    @Select("SELECT u.email, p.type, p.content, p.image, p.rating, p.create_at FROM post p JOIN user u ON p.user_id=u.user_id WHERE site_id=#{site_id} AND type='후기' AND content LIKE CONCAT ('%', #{content}, '%')")
    List<PayHistoryDetailsDTO> getReviewByKeyword(PostSearchDTO dto);
    //별점 높은 순으로 게시글 가져오기
//    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE pd_id=#{pd_id} ORDER BY rating DESC")
//    List<PostDTO> getPostsByHighRated(int pd_id);
//
    //별점 낮은 순으로 게시글 가져오기
//    @Select("SELECT pd_id, user_id, type, content, image, title, rating, pd_image, create_at FROM post WHERE pd_id=#{pd_id} ORDER BY rating ASC")
//    List<PostDTO> getPostsByLowRated(int pd_id);

//    @Insert("INSERT INTO post (pd_id, site_id, user_id, type, content, image, rating, create_at) VALUES (#{pd_id}, #{site_id}, #{user_id}, #{type}, #{content}, #{image}, #{rating}, now())")
//    int insertReview(ReviewDTO reviewDTO);


}
