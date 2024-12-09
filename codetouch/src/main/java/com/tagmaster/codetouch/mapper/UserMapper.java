package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Insert("insert into User (email,password,name,nickname,phone,birth,gender,role,agree)" +
            "values(#{email},#{password},#{name},#{nickname},#{phone},#{birth},#{gender}," +
            "#{role},#{agree}")
    void insertUser(UserDTO dto);
    //사용자 생성

    @Select("select * from user where user_id=#{UserId}")
    UserDTO findByUserId(int UserId);

    @Update("update User set email=#{email},password=#{password},name=#{name},nickname=#{nickname}," +
            "phone=#{phone},birth=#{birth},gender=#{gender},role=#{role},agree=#{agree})")
    UserDTO updateUser(int UserId);


}
