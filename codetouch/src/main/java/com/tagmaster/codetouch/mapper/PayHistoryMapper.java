package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayHistoryMapper {
    // 생성
    @Insert("INSERT INTO pay_history ( cart_id, order_num, total_price) VALUES ( #{cart_id}, #{order_num}, #{total_price})")
    int insertPayHistory(PayHistoryDTO payHistoryDTO);
    // 수정
    @Update("UPDATE pay_history SET cart_id=#{cart_id}, order_num=#{order_num}, total_price=#{total_price} WHERE pay_id=#{pay_id}")
    PayHistoryDTO updatePayHistory(PayHistoryDTO payHistoryDTO);
    // 삭제
    @Delete("DELETE FROM pay_history WHERE pay_id=#{pay_id}")
    int deletePayHistory(int pay_id);
    // 결제 내역 뽑기
    @Select("SELECT cart_id, create_at, order_num, total_price, pay_id FROM pay_history WHERE cart_id=#{cart_id}")
    List<PayHistoryDTO> getPayHistoryByCartId(int cart_id);
    // 시간별 결제 내역 뽑기
    @Select("SELECT cart_id, create_at, order_num, total_price, pay_id FROM pay_history ORDER BY create_at DESC")
    List<PayHistoryDTO> getPayHistoryDESC();
}

