package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.ProductDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    //생성
    @Insert("INSERT INTO product (site_id, name, category, price, image, description) VALUES (#{site_id}, #{name}, #{category}, #{price}, #{image}, #{description})")
    void insertProduct(ProductDTO dto);
    //수정
    @Update("UPDATE product SET name=#{name}, category=#{category}, price=#{price}, image=#{image}, description=#{description} WHERE pd_id=#{pd_id}")
    void updateProduct(ProductDTO dto);
    //삭제
    @Delete("DELETE FROM product WHERE pd_id=#{pd_id}")
    void deleteProduct(ProductDTO dto);
    //특정 상품에 대한 모든 정보 출력
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE pd_id=#{pd_id}" )
    List<ProductDTO> findAllById(int product_id);
    //특정 상품에 대한 이름과 가격 출력
    @Select("SELECT name, price FROM product WHERE pd_id=#{pd_id}")
    ProductDTO findProductById(int product_id);
    //특정 카테고리에 속해 있는 상품 정보 출력
    @Select("SELECT name, price, price, image, description FROM product WHERE category=#{category}")
    ProductDTO findProductByCategory(int category);
    //키워드로 상품 찾기
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE name LIKE CONCAT ('%', #{keyword}, '%')")
    List<ProductDTO> findProductByKeyword(String keyword);
}
