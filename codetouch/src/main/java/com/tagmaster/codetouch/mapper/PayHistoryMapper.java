package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.domain.PayHistoryFullDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayHistoryMapper {
    // 생성
    @Insert("INSERT INTO pay_history (pd_id, merchant_id, site_id, user_id) " +
            "VALUES (#{pd_id}, #{merchant_id}, #{site_id}, #{user_id})")
    int insertPayHistory(PayHistoryDTO payHistoryDTO);
    // 수정
//    @Update("UPDATE pay_history SET pd_id=#{pd_id}, merchant_id=#{merchant_id}=} WHERE pay_id=#{pay_id}")
//    int updatePayHistory(PayHistoryDTO payHistoryDTO);
    // 삭제
    @Delete("DELETE FROM pay_history WHERE site_id=#{site_id} AND pay_id=#{pay_id}")
    int deletePayHistory(int site_id, int pay_id);
    // 결제 내역 뽑기
    @Select("SELECT p.name, p.price, ph.quantity, u.email, ph.merchant_id, ph.create_at FROM pay_history ph JOIN product p ON ph.pd_id = p.pd_id JOIN user u ON ph.user_id = u.user_id WHERE ph.site_id=#{site_id} AND ph.user_id=#{user_id} AND pay_id=#{pay_id}")
    PayHistoryDTO getPayHistory(int site_id, int user_id, int pay_id);
    @Select("SELECT p.name, p.price, ph.quantity, u.email, ph.merchant_id, ph.create_at FROM pay_history ph JOIN product p ON ph.pd_id = p.pd_id JOIN user u ON ph.user_id = u.user_id WHERE ph.site_id=#{site_id} AND ph.user_id=#{user_id}")
    List<PayHistoryDTO> getPayHistoryList(int site_id, int user_id);
    // 특정 고객의 모든 결제 내역 뽑기 <- join ? userid 사용으로 모든 결제 내역 뽑기 ?
    @Select("SELECT p.name, p.price, ph.quantity, u.email, ph.merchant_id, ph.create_at FROM pay_history ph JOIN product p ON ph.pd_id = p.pd_id JOIN user u ON ph.user_id = u.user_id WHERE ph.site_id=#{site_id} AND ph.user_id=#{user_id}")
    PayHistoryFullDTO getPayHistoryBySiteIdAndPdIdAndUserId(int site_id, int user_id);//site_id
    // 시간별 결제 내역 뽑기
    @Select("SELECT p.name, p.price, ph.quantity, u.email, ph.merchant_id, ph.create_at FROM pay_history ph JOIN product p ON ph.pd_id = p.pd_id JOIN user u ON ph.user_id = u.user_id WHERE ph.site_id=#{site_id} ORDER BY create_at DESC")
    List<PayHistoryDTO> getPayHistoryDESC(int site_id);

    //join으로 영수증 뽑아주기
//    @Select("""
//    SELECT u.name, ph.merchant_id, ph.create_at,
//           p.price, p.name, c.count,
//           SUM(c.count * p.price) A
//    FROM pay_history ph
//    JOIN cart c ON ph.pd_id = c.pd_id
//    JOIN product p ON c.pd_id = p.pd_id
//    JOIN user u ON c.user_id = u.user_id
//    WHERE ph.pd_id IN
//    <foreach item="cartId" collection="cartIds" open="(" separator="," close=")">
//                                    #{cartId}
//                                </foreach>
//    GROUP BY u.name, ph.merchant_id, ph.create_at, p.price, p.name, c.count
//""")
//    List<Map<String, Object>> getReceiptByCartId(String cartIds);
}

