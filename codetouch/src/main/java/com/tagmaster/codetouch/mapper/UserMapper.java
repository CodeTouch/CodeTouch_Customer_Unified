package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into User (id,password,email,name,phone,birth,gender,address,role,mileage,business_num,report_num)values(#{id},#{password},#{email},#{name},#{phone},#{birth},#{gender},#{address},#{role},#{mileage},#{business_num)")
    void insertUser(UserDTO dto);

    @Select("select * from users where user_id=#{UserId}")
    UserDTO getAllUser(int UserId);





}
