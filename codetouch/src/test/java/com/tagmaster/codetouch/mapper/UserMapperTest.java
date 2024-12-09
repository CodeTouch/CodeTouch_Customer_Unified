package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import net.minidev.json.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest{
    @Autowired
    private UserMapper userMapper;

    @Test
    @Rollback(value = false)
    public void insertUserTest(){
        UserDTO dto = new UserDTO();
        dto.setEmail("rnrmfwldwlddl@gmail.com");
        dto.setPassword("1234");
        dto.setName("jioni");
        dto.setNickname("thing");
        dto.setPhone("010-1234-5678");
        dto.setBirth(LocalDate.of(1997,2,24)); //svc
        dto.setGender(2); //svc
        dto.setRole("USER,ADMIN"); //service 에서 처리하기
        dto.setAgree(1);
        userMapper.insertUser(dto);
}/*
    @Test
    @Rollback(value = false)
    void updateUser */
}
