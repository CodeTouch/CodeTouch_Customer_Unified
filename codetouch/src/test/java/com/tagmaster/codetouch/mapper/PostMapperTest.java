package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PostDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostMapperTest {
    @Autowired
    private PostMapper postMapper;

    @Test
    @Rollback(value = false)
    public void insertPostTest() {
        PostDTO dto = new PostDTO();
        dto.setPd_id(2);
        dto.setUser_id(3);
        dto.setType("후기");
        dto.setContent("최고의 옷. 무대를 뒤집어놓으셧다 ㄷ");
        dto.setImage("image10.jpg");
        dto.setRating(5);
        postMapper.insertPost(dto);
        System.out.println("생성");
    }

    @Test
    @Rollback(false)
    public void updatePostTest() {
        PostDTO dto = new PostDTO();
        dto.setPost_id(2);
        dto.setPd_id(1);
        dto.setUser_id(1);
        dto.setType("게시글");
        dto.setContent("옷 안예뻐요.");
        dto.setImage("image1.jpg");
        dto.setRating(3);
        postMapper.updatePost(dto);
        System.out.println(dto);
    }

    @Test
    @Rollback(value = false)
    public void deletePostTest() {
        postMapper.deletePostById(3);
    }

    @Test
    @Rollback(value = false)
    public void getPostByIdTest() {
        PostDTO dto = postMapper.getPostById(1);
        System.out.println(dto);
    }
    @Test
    @Rollback(value = false)
    public void getPostsByUserIdTest(){
        List<PostDTO> dtoList = postMapper.getPostsByUserId(1);
        System.out.println(dtoList);
    }

    @Test
    @Rollback(value = false)
    public void getPostsByProductIdTest(){
        List<PostDTO> dtoList = postMapper.getPostsByProductId(1);
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void getPostsByKeywordTest(){
        List<PostDTO> dtoList = postMapper.getPostsByKeyword("응");
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void getPostsByHighRated(){
        List<PostDTO> dtoList = postMapper.getPostsByHighRated(2);
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void getPostsByLowRated(){
        List<PostDTO> dtoList = postMapper.getPostsByLowRated(2);
        System.out.println(dtoList);
    }

}
