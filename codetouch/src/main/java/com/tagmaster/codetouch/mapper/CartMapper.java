package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.CartDTO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CartMapper {
    //장바구니 생성
    @Insert("INSERT INTO cart (user_id, pd_id, count) VALUES (#{user_id}, #{pd_id}, #{count})")
    void InsertCart(CartDTO dto);
    //장바구니 수정
    @Update("UPDATE cart SET user_id=#{user_id}, pd_id=#{pd_id}, count=#{count}")
    void UpdateCart(CartDTO dto);
    //장바구니 삭제
    @Delete("DELETE cart WHERE cart_id=#{cart_id}")
    int DeleteCart(CartDTO dto);
    //장바구니 조회
    @Select("SELECT cart.user_id, cart.pd_id, cart.count FROM cart WHERE cart_id=#{cart_id}")
    void SelectCart(CartDTO dto);
    //유저 아이디로 장바구니 조회
    @Select("SELECT cart.pd_id, cart.count FROM cart WHERE user_id=#{user_id}")
    void SelectCartByUserId(CartDTO dto);
}
