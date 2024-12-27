package com.tagmaster.codetouch.mapper;

import com.tagmaster.codetouch.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {
    //생성
    @Insert("INSERT INTO product (site_id, name, category, price, image, description) VALUES (#{site_id}, #{name}, #{category}, #{price}, #{image}, #{description})")
    int insertProduct(ProductDTO dto);
    //수정 -> 의문) site와 product는 연결되어 있다만 조건으로 siteId를 같이 부여? or not?
    @Update("UPDATE product SET name=#{name}, category=#{category}, price=#{price}, image=#{image}, description=#{description} WHERE pd_id=#{pd_id}")
    int updateProduct(ProductDTO dto);
    //삭제
    @Delete("DELETE FROM product WHERE site_id=#{site_id} AND pd_id=#{pd_id}")
    int deleteProduct(DeleteProductDTO deleteProductDTO);
    //특정 상품에 대한 모든 정보 출력
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE site_id=#{site_id}" )
    List<ProductDTO> findAllById(int site_id);
    //특정 상품에 대한 이름과 가격 출력
    @Select("SELECT name, price FROM product WHERE pd_id=#{pd_id}")
    ProductDTO findProductById(int pd_id);
    //높은 가격순 상품 리스트 출력
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE site_id=#{site_id} ORDER BY price ASC")
    List<ProductDTO> findAllProductsByHighPrice(int site_id);
    //낮은 가격순 상품 리스트 출력
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE site_id=#{site_id} ORDER BY price DESC")
    List<ProductDTO> findAllProductsByLowPrice(int site_id);
    //특정 카테고리에 속해 있는 상품 정보 출력
    @Select("SELECT name, category, price, image, description FROM product WHERE category=#{category}")
    List<ProductDTO> findProductByCategory(String category);
    //상품 카테고리별 설정 출력
    @Select("SELECT pd_id, name, image, price, category, sale_state, stock, create_at FROM product WHERE category=#{category}")
    ProductDTO findProductSettingByCategory(String category);
    //상품 할인율 설정
    @Update("UPDATE product SET sale_name=#{sales_name}, sale_period=#{sales_period}, sale_percentage=#{sale_percentage} WHERE site_id=#{site_id} AND pd_id={#pd_id}")
    int updateProductSaleSetting(int site_id, int pd_id);
    //키워드로 상품 찾기
    @Select("SELECT site_id, name, category, price, image, description FROM product WHERE name LIKE CONCAT ('%', #{name}, '%')")
    List<ProductDTO> findProductByKeyword(String name);
    @Select("SELECT ph.site_id, " +
            "       ph.pd_id, " +
            "       ph.user_id, " +
            "       pd.name AS product_name, " +  // 상품 이름 추가
            "       u.email AS user_email " +    // 이메일 추가
            "FROM pay_history ph " +
            "JOIN product pd ON ph.pd_id = pd.pd_id " +
            "JOIN user u ON ph.user_id = u.user_id " +
            "WHERE ph.site_id=#{site_id} AND ph.pay_id = #{pay_id}")
    ReceiptDTO getPayHistoryDetails(int site_id, int pay_id);


    @Insert("INSERT INTO post (pd_id, user_id, site_id, content, image, rating, create_at) " +
            "VALUES (#{pd_id}, #{user_id}, #{site_id}, #{content}, #{image}, #{rating}, now())")
    int insertReview(ReviewDTO dto);

}
