package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import lombok.Locked;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into User (site_id,email,password,name,nickname,phone,birth,gender,role,agree)" +
            "values(#{site_id},#{email},#{password},#{name},#{nickname},#{phone},#{birth},#{gender}," +
            "#{role},#{agree})")
    UserDTO insertUser(UserDTO dto);
    //사용자 생성

    @Update("update User set password=#{password},name=#{name},nickname=#{nickname}," +
            "phone=#{phone},address=#{address},agree=#{agree} where site_id=#{site_id} and email=#{email}")
    UserDTO updateUser(UserDTO dto);
    // 사용자 개인정보 수정

    @Update("update User set role=#{role} where site_id=#{site_id} and email=#{email}")
    UserDTO updateRole(int site_id, String email, String role);
    // 권한 수정

    @Update("update User set password=#{password},name=#{name},nickname=#{nickname}," +
            "phone=#{phone},agree=#{agree},business_num=#{business_num},report_num=#{report_num} where site_id=#{site_id} and email=#{email}")
    UserDTO updateAdmin(UserDTO dto);
    // 관리자 정보 수정 (사업자등록번호,통신판매번호 등)

    @Select("select email,name,nickname,phone,gender,birth,role,mileage " +
            "from user where site_id=#{site_id}")
    List<UserDTO> showAllUser(int site_id);
    // 사이트 모든 이용자 출력

    @Select("select email from user where site_id=#{site_id}")
    List<String> sendMail( int site_id);
    // 사이트 모든 이용자 메일발송


    @Delete("delete from user where site_id=#{site_id} and email=#{email}")
    String deleteUser(int site_id , String email);
    // 회원탈퇴

    @Select("select SQL_NO_CACHE email,name,nickname,phone,birth,gender,address,role,mileage from user where site_id=#{site_id} and email=#{email}")
    UserDTO searchUser(int site_id , String email);
    //사이트 이용자 조회
}
