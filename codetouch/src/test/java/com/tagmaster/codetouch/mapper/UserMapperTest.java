package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import net.minidev.json.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.Rollback;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest{
    @Autowired
    private UserMapper userMapper;

    // 사용자 생성
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
}
    // 사용자 정보 수정 //
    @Test
    @Rollback(value = false)
    public void updateUserTest(){
        UserDTO dto=new UserDTO();
        dto.setPassword("5678");
        dto.setName("지오니");
        dto.setNickname("띵");
        dto.setPhone("010-2222-2222");
        dto.setAddress("suwon,13687"); //json 바꾸기
        dto.setAgree(2);
        userMapper.updateUser(dto);
    }

    // 권한수정
    @Test
    @Rollback(value = false)
    public void updateRoleTest(){
        UserDTO dto =new UserDTO();
        dto.setUserId(1);
        dto.setRole("USER,ADMIN");
        userMapper.updateRole(dto);
    }

    //관리자 정보수정
    @Test
    @Rollback(value = false)
    public void updateAdminTest(){
        UserDTO dto=new UserDTO();
        dto.setPassword("543");
        dto.setName("adminJioni");
        dto.setNickname("adminThing");
        dto.setPhone("010-9999-9999");
        dto.setAgree(2);
        dto.setBusiness_num(987678);
        dto.setReport_num(2345);
        userMapper.updateAdmin(dto);
    }

    //사이트 모든 이용자 출력
    @Test
    @Rollback(value = false)
    public void showAllUserTest(){
        UserDTO dto =new UserDTO();
        List<UserDTO> showUsers =userMapper.showAllUser(1);
        showUsers.forEach(System.out::println); //리스트 하나씩 찍음(하나씩 안하면 주소로 찍힌다)
    }

    // 사이트 모든 이용자 메일발송
    @Test
    @Rollback(value = false)
    public void sendMailTest(){
        UserDTO dto = new UserDTO();
        dto = userMapper.sendMail(1);
        System.out.println(dto);
    }

    //id로 객체 찾기
    @Test
    @Rollback(value = false)
    public void findByUserIdTest(){
        UserDTO dto = new UserDTO();

    }

}


