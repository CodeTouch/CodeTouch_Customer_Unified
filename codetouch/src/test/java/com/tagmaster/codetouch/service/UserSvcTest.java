package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.controller.UserCtrl;
import com.tagmaster.codetouch.domain.UserDTO;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserSvcTest {
    @Autowired
    private UserMapper userMapper;

    // DTO (받아올값,,,,)
    // util 혹은 변형 주고 변수에 저장
    // 변수를 set 한다
    // mapper + util = service test

    // 사용자 생성 //출력값 : 메세지만 줄거
    @Test
    @Rollback(value = false)
        public void saveUserTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("jioni@e.com");
            dto.setSite_id(1);
            dto.setPassword("123");
            dto.setName("jioni");
            dto.setNickname("jioning");
            dto.setPhone("01012345678");
            dto.setBirth(LocalDate.of(1997, 2, 22));
            dto.setGender(1);
            dto.setRole("USER");
            dto.setAgree(1);
            //
            HashMap<String, String> addressMap = new HashMap<>();
            addressMap.put("시/도", "서울특별시");
            addressMap.put("시/군/구", "강남구");
            addressMap.put("읍/면/동", "삼성동");
            addressMap.put("도로명", "테헤란로");
            addressMap.put("상세주소", "123번지");
            String result = userMapper.insertUser(dto);
            assertEquals("저장 성공", result);
        }

        // 사용자 정보 수정 테스트
        @Test
        @Rollback(value = true)
        public void updateUserTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("thing@e.com");
            dto.setSite_id(1);
            dto.setPassword("password123");
            dto.setName("thing");
            dto.setNickname("king");
            dto.setPhone("01011112222");
            dto.setAgree(1);
            userSvc.SaveUser(dto);
            dto.setPassword("8765");
            dto.setName("Updated User");
            dto.setNickname("Updated");
            dto.setPhone("01099998888");

            String result = userSvc.UpdateUser(dto);

            assertEquals("정보 수정 성공", result);
        }

        // 권한 수정 테스트
        @Test
        @Rollback(value = true)
        public void updateRoleTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("roleuser@e.com");
            dto.setSite_id(1);
            dto.setPassword("password123");
            dto.setRole("USER");
            userSvc.SaveUser(dto);
            dto.setRole("ADMIN");
            String result = userSvc.UpdateRole(dto.getSite_id(), dto.getEmail(), dto.getRole());
            assertEquals("권한 부여 성공", result);
        }

        // 관리자 정보 수정 테스트
        @Test
        @Rollback(value = true)
        public void updateAdminTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("admin@e.com");
            dto.setSite_id(1);
            dto.setName("Admin User");
            dto.setNickname("Admin");
            dto.setPhone("01012345678");
            dto.setBusiness_num(12345678);
            dto.setReport_num(87654321);
            userSvc.SaveUser(dto); 

            // 수정할 데이터 설정
            dto.setName("Updated Admin");
            dto.setNickname("Updated Admin");
            dto.setPhone("01098765432");
            dto.setBusiness_num(98765432);
            dto.setReport_num(12345678);

            String result = userSvc.UpdateAdmin(dto);

            assertEquals("관리자 정보 수정 성공", result);
        }

        // 사이트 모든 이용자 출력 테스트
        @Test
        @Rollback(value = true)
        public void showAllUserTest() {
            UserDTO user1 = new UserDTO();
            user1.setEmail("user1@e.com");
            user1.setSite_id(1);
            user1.setName("User One");
            userSvc.SaveUser(user1);

            UserDTO user2 = new UserDTO();
            user2.setEmail("user2@e.com");
            user2.setSite_id(1);
            user2.setName("User Two");
            userSvc.SaveUser(user2);
            
            List<UserDTO> result = userSvc.showAllUser(1);

            assertNotNull(result);
            assertTrue(result.size() >= 2); // 최소 2명 존재
        }

        // 사용자 회원탈퇴 테스트
        @Test
        @Rollback(value = true)
        public void deleteUserTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("jioni@e.com");
            dto.setSite_id(1);
            dto.setName("Delete User");
            userSvc.SaveUser(dto);

            String result = userSvc.deleteUser(1, "jioni@e.com");

            assertEquals("회원 탈퇴", result);
        }

        // 사이트 이용자 조회 테스트
        @Test
        @Rollback(value = true)
        public void searchUserTest() {
            UserDTO dto = new UserDTO();
            dto.setEmail("thin@e.com");
            dto.setSite_id(1);
            dto.setName("thing");
            userSvc.SaveUser(dto);

            UserDTO result = userSvc.searchUser(1, "thing@e.com");
            
            assertNotNull(result);
            assertEquals("thing", result.getName());
        }
    }
                
    


    
