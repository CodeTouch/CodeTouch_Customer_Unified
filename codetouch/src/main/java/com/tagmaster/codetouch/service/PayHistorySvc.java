package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import com.tagmaster.codetouch.mapper.PayHistoryMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayHistorySvc {
    PayHistoryMapper payHistoryMapper;

    @Autowired
    public PayHistorySvc(PayHistoryMapper payHistoryMapper) {
        this.payHistoryMapper = payHistoryMapper;
    }

    // 결제 내역 저장
    public String savePay(PayHistoryDTO dto) {
        try {
            payHistoryMapper.insertPayHistory(dto);
            return "결제 내역 저장 성공";
        } catch (Exception e) {
            return "결제 내역 저장 실패: " + e.getMessage();
        }
    }

    // 결제 내역 수정
    public String updatePay(PayHistoryDTO dto) {
        try {
            PayHistoryDTO updated = payHistoryMapper.updatePayHistory(dto);
            return updated != null ? "결제 내역 수정 성공" : "결제 내역 수정 실패";
        } catch (Exception e) {
            return "결제 내역 수정 실패: " + e.getMessage();
        }
    }

    // 결제 내역 삭제
    public String deletePay(int payId) {
        try {
            int result = payHistoryMapper.deletePayHistory(new PayHistoryDTO() {{
                setPay_id(payId);
            }});
            return result > 0 ? "결제 내역 삭제 성공" : "결제 내역 삭제 실패";
        } catch (Exception e) {
            return "결제 내역 삭제 실패: " + e.getMessage();
        }
    }

    // 특정 장바구니의 결제 내역 가져오기
    public List<PayHistoryDTO> getPayByCartId(int cartId) {
        try {
            return payHistoryMapper.getPayHistoryByCartId(cartId);
        } catch (Exception e) {
            throw new RuntimeException("결제 내역 조회 실패: " + e.getMessage(), e);
        }
    }

    // 시간 순서로 정렬된 결제 내역 가져오기
    public List<PayHistoryDTO> getAllPayByTime() {
        try {
            return payHistoryMapper.getPayHistoryDESC();
        } catch (Exception e) {
            throw new RuntimeException("결제 내역 조회 실패: " + e.getMessage(), e);
        }
    }
}
