package com.tagmaster.codetouch.controller;
import com.tagmaster.codetouch.domain.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional // 테스트 종료 후 데이터 롤백
public class UserCtrlTest {
    @Autowired
    private TestRestTemplate restTemplate;

    // 사용자 생성 테스트
    @Test
    public void saveUserTest() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setSite_id(1);
        userDTO.setEmail("testuser@example.com");
        userDTO.setPassword("password123");
        userDTO.setName("Test User");
        userDTO.setNickname("Tester");
        userDTO.setPhone("01012345678");
        userDTO.setBirth(LocalDate.of(1990, 1, 1));
        userDTO.setGender(1);
        userDTO.setRole("USER");
        userDTO.setAgree(1);

        // When
        ResponseEntity<String> response = restTemplate.postForEntity("/user", userDTO, String.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("저장성공", response.getBody());
    }

    // 사용자 정보 수정 테스트
    @Test
    public void updateUserTest() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setSite_id(1);
        userDTO.setEmail("testuser@example.com");
        userDTO.setName("Updated User");
        userDTO.setNickname("Updated Tester");
        userDTO.setPhone("01099999999");

        // When
        ResponseEntity<String> response = restTemplate.postForEntity("/user/update", userDTO, String.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("정보 수정 성공", response.getBody());
    }

    // 권한 수정 테스트
    @Test
    public void updateRoleTest() {
        // Given
        UserDTO userDTO = new UserDTO();
        userDTO.setSite_id(1);
        userDTO.setEmail("testuser@example.com");
        userDTO.setRole("ADMIN");

        // When
        ResponseEntity<String> response = restTemplate.postForEntity("/user/updateRole", userDTO, String.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("권한 부여 성공", response.getBody());
    }

    // 사이트 모든 이용자 출력 테스트
    @Test
    public void getAllUsersTest() {
        // Given
        int siteId = 1;

        // When
        ResponseEntity<List> response = restTemplate.getForEntity("/user/" + siteId, List.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertTrue(response.getBody().size() > 0);
    }

    // 회원 탈퇴 테스트
    @Test
    public void deleteUserTest() {
        // Given
        int siteId = 1;
        String email = "testuser@example.com";

        // When
        ResponseEntity<String> response = restTemplate.postForEntity("/user/delete?siteId=" + siteId + "&email=" + email, null, String.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("회원 탈퇴", response.getBody());
    }

    // 사이트 이용자 조회 테스트
    @Test
    public void getUserTest() {
        // Given
        int siteId = 1;
        String email = "testuser@example.com";

        // When
        ResponseEntity<UserDTO> response = restTemplate.getForEntity("/user/" + siteId + "/" + email, UserDTO.class);

        // Then
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(email, response.getBody().getEmail());
    }
}


