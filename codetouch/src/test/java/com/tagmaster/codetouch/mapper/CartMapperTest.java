package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.CartDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Map;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartMapperTest {
    @Autowired
    private CartMapper cartMapper;
    @Test
    @Rollback(false)
    public void insertCartTest() {
        CartDTO dto = new CartDTO();
        dto.setUser_id(2);
        dto.setPd_id(3);
        dto.setCount(0);
        cartMapper.InsertCart(dto);
    }
    @Test
    @Rollback(false)
    public void updateCartTest() {
        CartDTO dto = new CartDTO();
        dto.setCart_id(2);
        dto.setUser_id(1);
        dto.setPd_id(4);
        dto.setCount(4);
        cartMapper.UpdateCart(dto);
    }

    @Test
    @Rollback(false)
    public void deleteCartTest() {
        cartMapper.DeleteCart(1);
    }

    @Test
    @Rollback(false)
    public void findCartByIdTest() {
        CartDTO dto = cartMapper.FindCartById(1);
        System.out.println(dto);
    }

    @Test
    @Rollback(false)
    public void findProductByIdTest(){
        List<Integer> dtoList = cartMapper.FindProductById(2);
        System.out.println(dtoList);
    }

    @Test
    @Rollback(false)
    public void findProductsByCartIdTest(){
        List<CartDTO> dtoList = cartMapper.FindProductsByCartId(2);
        System.out.println(dtoList);
    }

    @Test
    @Rollback(false)
    public void findProductsGroupByCartIdTest() {
        List<Map<String, Object>> productList = cartMapper.FindProductsGroupByCartId(2);
        System.out.println(productList);
    }
}
