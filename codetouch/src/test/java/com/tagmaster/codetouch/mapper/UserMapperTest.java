package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import net.minidev.json.JSONObject;
import org.apache.ibatis.jdbc.Null;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.annotation.Rollback;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class UserMapperTest{
    @Autowired
    private UserMapper userMapper;

    // 사용자 생성 // 이메일 중복 확인 : q@q.q
    @Test
    @Rollback(value = false)
    public void insertUserTest(){
        UserDTO dto = new UserDTO();
        dto.setEmail("rnrmfwldwlddl@gmail.com");
        dto.setSite_id(1);
        dto.setPassword("1234");
        dto.setName("jioni");
        dto.setNickname("thing");
        dto.setPhone("01012345678");
        dto.setBirth(LocalDate.of(1997,2,24)); //svc
        dto.setGender(2); //svc
        //dto.setRole("USER,ADMIN"); //service 에서 처리하기
        dto.setAgree(1);
        userMapper.insertUser(dto);
}
    // 사용자 정보 수정 //
    @Test
    @Rollback(value = false)
    public void updateUserTest(){
        UserDTO dto=new UserDTO();
        dto.setEmail("rnrmfwldwlddl@gmail.com");
        dto.setSite_id(1);
        dto.setName("지오니");
        dto.setNickname("띵");
        dto.setPhone("01022222222");
        dto.setAddress("{\"기본배송지\":\"서울시 강남구\"}"); //json
        userMapper.updateUser(dto);
    }

    // 권한수정
    @Test
    @Rollback(value = false)
    public void updateRoleTest(){
        UserDTO dto =new UserDTO();
        dto.setEmail("rnrmfwldwlddl@gmail.com");
        dto.setSite_id(1);
        //dto.setRole("USER,ADMIN");
        //userMapper.updateRole(dto.getSite_id(), dto.getEmail(),dto.getRole());
        System.out.println(dto);
    }

    //관리자 정보수정
    @Test
    @Rollback(value = false)
    public void updateAdminTest(){
        UserDTO dto=new UserDTO();
        dto.setSite_id(1);
        dto.setEmail("rnrmfwldwlddl@gmail.com");
        dto.setName("adminJioni");
        dto.setNickname("adminThing");
        dto.setPhone("01099999999");
        //dto.setBusiness_num(987678);
        //dto.setReport_num(2345);
        userMapper.updateAdmin(dto);
    }

    //사이트 모든 이용자 출력
    @Test
    @Rollback(value = false)
    public void showAllUserTest(){
        List<UserDTO> showUsers =userMapper.showAllUser(1);
        showUsers.forEach(System.out::println); //리스트 하나씩 찍음(하나씩 안하면 주소로 찍힌다)
    }

    // 사이트 모든 이용자 메일발송
    @Test
    @Rollback(value = false)
    public void sendMailTest(){
        List<String> mail = userMapper.sendMail(1);
        mail.forEach(System.out::println);
    }

    //사용자 회원탈퇴
    @Test
    @Rollback(value = false)
    public void deleteUserTest(){
        String del= userMapper.deleteUser(1,"rnrmfwldwlddl@gmail.com");
        System.out.println(del);
    }


    //사이트 이용자 조회
    @Test
    public void searchUserTest(){
        UserDTO search = userMapper.searchUser(1,"rnrmfwldwlddl@gmail.com");
        System.out.println(search);
    }
}
