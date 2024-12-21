package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.CartDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CartMapper {
    //장바구니 생성
    @Insert("INSERT INTO cart (user_id, pd_id, count) VALUES (#{user_id}, #{pd_id}, #{count})")
    int InsertCart(CartDTO dto);
    //장바구니 수정
    @Update("UPDATE cart SET user_id=#{user_id}, pd_id=#{pd_id}, count=#{count} WHERE cart_id=#{cart_id}")
    int UpdateCart(CartDTO dto);
    //장바구니 삭제
    @Delete("DELETE FROM cart WHERE user_id=#{user_id}")
    int DeleteCart(int user_id);
    //장바구니 조회
    @Select("SELECT user_id, pd_id, count FROM cart WHERE user_id=#{user_id}")
    CartDTO FindCartById(int user_id);
    //카트 아이디로 상품 아이디 찾기
    @Select("SELECT pd_id FROM cart WHERE user_id=#{user_id}")
    List<Integer> FindProductById(int user_id);
    //특정 카트의 상품과 수량 뽑기
    @Select("SELECT pd_id, count FROM cart WHERE user_id = #{user_id}")
    List<CartDTO> FindProductsById(int user_id);
    //Count -> category / pd_id 별로 count 해주기
    @Select("SELECT user_id, pd_id, count AS product_count FROM cart WHERE user_id=#{user_id} GROUP BY pd_id")
    List<Map<String, Object>> FindProductsGroupById(int user_id);



    //유저 아이디로 장바구니 조회
    //@Select("SELECT pd_id, count FROM cart WHERE user_id=#{user_id}")
    //List<CartDTO> SelectCartByUserId(int user_id);
}
