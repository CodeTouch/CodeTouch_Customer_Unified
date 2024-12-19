package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.PayHistoryDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PayHistoryMapperTest {
    @Autowired
    private PayHistoryMapper payHistoryMapper;

    @Test
    @Rollback(value = false)
    public void insertPayHistoryTest() {
        PayHistoryDTO payHistoryDTO = new PayHistoryDTO();
        payHistoryDTO.setCart_id(1);
        payHistoryDTO.setOrder_num(122341);
        payHistoryDTO.setTotal_price(1000000);
        payHistoryMapper.insertPayHistory(payHistoryDTO);
    }

    @Test
    @Rollback(value = false)
    public void updatePayHistoryTest() {
        PayHistoryDTO payHistoryDTO = new PayHistoryDTO();
        payHistoryDTO.setPay_id(2);
        payHistoryDTO.setCart_id(1);
        payHistoryDTO.setOrder_num(555353);
        payHistoryDTO.setTotal_price(1000000);
        payHistoryMapper.updatePayHistory(payHistoryDTO);
    }
    @Test
    @Rollback(value = false)
    public void deletePayHistoryTest() {
        payHistoryMapper.deletePayHistory(3);
    }
    @Test
    @Rollback(value = false)
    public void getPayHistoryTest() {
        PayHistoryDTO payHistoryDTO = payHistoryMapper.getPayHistory(3);
        System.out.println(payHistoryDTO);
    }
    @Test
    @Rollback(value = false)
    public void getPayHistoryByCartIdTest() {
        PayHistoryDTO dto = payHistoryMapper.getPayHistoryByCartId(3);
        System.out.println(dto);
    }
    @Test
    @Rollback(value = false)
    public void getPayHistoryDESC(){
        List<PayHistoryDTO> dto = payHistoryMapper.getPayHistoryDESC();
        System.out.println(dto);
    }

}
