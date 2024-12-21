package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.CartDTO;
import com.tagmaster.codetouch.mapper.CartMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartSvc {
    CartMapper cartMapper;

    @Autowired
    public CartSvc(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    // 장바구니 추가
    public String saveCart(CartDTO dto) {
        try {
            cartMapper.InsertCart(dto);
            return "저장 성공";
        } catch (Exception e) {
            return "저장 실패 " + e.getMessage();
        }
    }

    // 장바구니 업데이트
    public String updateCart(CartDTO dto) {
        try {
            cartMapper.UpdateCart(dto);
            return "수정 성공";
        } catch (Exception e) {
            return "수정 실패 " + e.getMessage();
        }
    }

    // 장바구니 삭제
    public String deleteCart(int cartId) {
        try {
            int result = cartMapper.DeleteCart(cartId);
            return result > 0 ? "삭제 성공" : "삭제 실패";
        } catch (Exception e) {
            return "삭제 실패 " + e.getMessage();
        }
    }

    // 특정 장바구니 조회
    public CartDTO getCartById(int cartId) {
        try {
            return cartMapper.FindCartById(cartId);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return null;
        }
    }

    // 특정 장바구니의 상품 그룹 조회
    public List<Map<String, Object>> getProduct(int cartId) {
        try {
            return cartMapper.FindProductsGroupById(cartId);
        } catch (Exception e) {
            System.err.println("그룹 조회 실패: " + e.getMessage());
            return null;
        }
    }
}
