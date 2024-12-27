package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PayHistoryMapper {
    // 생성
    @Insert("INSERT INTO pay_history ( pd_id, merchant_id) VALUES ( #{pd_id}, #{order_num}, })")
    int insertPayHistory(PayHistoryDTO payHistoryDTO);
    // 수정
    @Update("UPDATE pay_history SET pd_id=#{pd_id}, merchant_id=#{merchant_id}=} WHERE pay_id=#{pay_id}")
    int updatePayHistory(PayHistoryDTO payHistoryDTO);
    // 삭제
    @Delete("DELETE FROM pay_history WHERE pay_id=#{pay_id}")
    int deletePayHistory(int pay_id);
    // 결제 내역 뽑기
    @Select("SELECT pd_id, merchant_id, pay_id FROM pay_history WHERE pay_id=#{pay_id}")
    PayHistoryDTO getPayHistory(int pay_id);
    // 특정 고객의 모든 결제 내역 뽑기 <- join ? userid 사용으로 모든 결제 내역 뽑기 ?
    @Select("SELECT pd_id, site_id, merchant_id, pay_id, create_at FROM pay_history WHERE site_id=#{site_id} AND pd_id=#{pd_id} AND user_id=#{user_id}")
    PayHistoryDTO getPayHistoryBySiteIdAndPdIdAndUserId(int site_id, int pd_id, int user_id);//site_id
    // 시간별 결제 내역 뽑기
    @Select("SELECT pd_id, merchant_id, pay_id FROM pay_history WHERE pd_id=#{pd_id} ORDER BY create_at DESC")
    List<PayHistoryDTO> getPayHistoryDESC();

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

