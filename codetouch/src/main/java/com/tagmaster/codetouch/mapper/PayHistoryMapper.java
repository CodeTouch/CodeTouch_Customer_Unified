package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayHistoryMapper {
    // 생성
    @Insert("INSERT INTO pay_history (pay_id, cart_id, order_num, total_price) VALUES (#{pay_id}, #{cart_id}, #{order_num}, #{total_price})")
    void insertPayHistory(PayHistoryDTO payHistoryDTO);
    // 수정
    @Update("UPDATE pay_history SET cart_id=#{cart_id}, order_num=#{order_num}, total_price=#{total_price} WHERE pay_id=#{pay_id}")
    PayHistoryDTO updatePayHistory(PayHistoryDTO payHistoryDTO);
    // 삭제
    @Delete("DELETE FROM pay_history WHERE pay_id=#{pay_id}")
    int deletePayHistory(PayHistoryDTO payHistoryDTO);
    //배송 번호에 따른 결제 내역 삭제
    @Delete("DELETE FROM pay_history WHERE order_num=#{order_num}")
    int deletePayHistoryByOrderNum(PayHistoryDTO payHistoryDTO);
    // 결제 내역 뽑기
    @Select("SELECT pay_history.cart_id, pay_history.create_at, pay_history.order_num, pay_history.total_price, pay_history.pay_id FROM pay_history WHERE cart_id=#{cart_id}")
    List<PayHistoryDTO> getPayHistoryByCartId(int cart_id);
    //생성 날짜에 따른 배송 번호 뽑기
    @Select("SELECT pay_history.order_num FROM pay_history WHERE create_at=#{create_at}")
    List<Integer> getPayHistoryOrderNum();

}

