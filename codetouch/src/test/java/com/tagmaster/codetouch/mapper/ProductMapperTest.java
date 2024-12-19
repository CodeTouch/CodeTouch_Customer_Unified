package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.ProductDTO;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductMapperTest {
    @Autowired
    private ProductMapper productMapper;

    @Test
    @Rollback(value = false)
    public void insertProductTest(){
        ProductDTO dto = new ProductDTO();
        dto.setName("test");
        dto.setSite_id(2);
        dto.setCategory("청순룩");
        dto.setPrice(20000);
        dto.setImage("img.png");
        dto.setStock(40);
        dto.setDescription("봄 아우터");
        productMapper.insertProduct(dto);
        System.out.println(dto);
    }
    @Test
    @Rollback(value = false)
    public void updateProductTest(){
        ProductDTO dto = new ProductDTO();
        dto.setPd_id(2);
        dto.setName("어쩔티비");
        dto.setCategory("겨울룩");
        dto.setPrice(16000);
        dto.setImage("img1.png");
        dto.setStock(40);
        dto.setDescription("이잉 졸령");
        productMapper.updateProduct(dto);
        System.out.println(dto);
    }

    @Test
    @Rollback
    public void deleteProductTest(){
        productMapper.deleteProduct(2);
    }

    @Test
    @Rollback(value = false)
    public void findAllProductsTest(){
        List<ProductDTO> dtoList = productMapper.findAllById(1);
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void findProductByIdTest(){
        ProductDTO dto = productMapper.findProductById(1);
        System.out.println(dto);
    }

    @Test
    @Rollback(value = false)
    public void findAllProductsByHighPriceTest(){
        List<ProductDTO> dtoList = productMapper.findAllProductsByHighPrice(2);
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void findAllProductsByLowPriceTest(){
        List<ProductDTO> dtoList = productMapper.findAllProductsByLowPrice(2);
        System.out.println(dtoList);
    }
    @Test
    @Rollback(value = false)
    public void findAllProductsByCategoryTest(){
        List<ProductDTO> dtoList = productMapper.findProductByCategory("청순룩");
        System.out.println(dtoList);
    }

}
