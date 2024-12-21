package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.ProductDTO;
import com.tagmaster.codetouch.mapper.ProductMapper;
import com.tagmaster.codetouch.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSvc {
    ProductMapper productMapper;

    @Autowired
    public ProductSvc(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    // 상품 추가
    public String saveProduct(ProductDTO dto) {
        try {
            // 상품 설명을 JSON 형식으로 변환하여 저장
            if (dto.getDescription() != null) {
                String descriptionJson = Util.objectToJson(dto.getDescription());
                dto.setDescription(descriptionJson);
            }
            productMapper.insertProduct(dto);
            return "저장 성공";
        } catch (Exception e) {
            return "저장 실패 " + e.getMessage();
        }
    }

    // 상품 정보 수정
    public String updateProduct(ProductDTO dto) {
        try {
            // 상품 설명 JSON 처리
            if (dto.getDescription() != null) {
                String descriptionJson = Util.objectToJson(dto.getDescription());
                dto.setDescription(descriptionJson);
            }
            productMapper.updateProduct(dto);
            return "수정 성공";
        } catch (Exception e) {
            return "수정 실패 " + e.getMessage();
        }
    }

    // 상품 삭제 // 수정해라
    public String deleteProduct(int product_id) {
        try {

            int result = productMapper.deleteProduct(product_id);
            return result > 0 ? "삭제 성공" : "삭제 실패";
        } catch (Exception e) {
            return "삭제 실패 " + e.getMessage();
        }
    }

    // 상품 조회 (상품ID 기준)
    public ProductDTO findProductById(int productId) {
        try {
            return productMapper.findProductById(productId);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return null;
        }
    }

    // 키워드로 상품 검색
    public List<ProductDTO> findByKeyword(String keyword) {
        try {
            return productMapper.findProductByKeyword(keyword);
        } catch (Exception e) {
            System.err.println("조회 실패: " + e.getMessage());
            return null;
        }
    }
}
