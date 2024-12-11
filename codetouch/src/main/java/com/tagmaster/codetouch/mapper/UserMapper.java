package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import lombok.Locked;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into User (email,password,name,nickname,phone,birth,gender,role,agree)" +
            "values(#{email},#{password},#{name},#{nickname},#{phone},#{birth},#{gender}," +
            "#{role},#{agree}")
    void insertUser(UserDTO dto);
    //사용자 생성

    @Update("update User set password=#{password},name=#{name},nickname=#{nickname}," +
            "phone=#{phone},role=#{role},agree=#{agree})")
    void updateUser(UserDTO dto);
    // 사용자 개인정보 수정

    @Update("update User set role=#{role} where user_id=#{userId}")
    void updateRole(UserDTO dto);
    // 권한 수정

    @Update("update User set password=#{password},name=#{name},nickname=#{nickname}," +
            "phone=#{phone},agree=#{agree},business_num=#{business_num},report_num=#{report_num})")
    void updateAdmin(UserDTO dto);
    // 관리자 정보 수정 (사업자등록번호,통신판매번호 등)

    @Select("select user.email,user.name,user.nickname,user.phone,user.gender,user.birth,user.role,user.mileage " +
            "from user where site_id=#{site_id}")
    List<UserDTO> showAllUser(int siteId);
    // 사이트 모든 이용자 출력

    @Select("select user.email from user where site_id=#{siteId}")
    UserDTO sendMail(@Param("siteId") int siteId);
    // 사이트 모든 이용자 메일발송

    @Select("select * from user where user_id=#{userId}")
    UserDTO findByUserId(@Param("userId")int UserId);
    // id로 객체 찾기

}
