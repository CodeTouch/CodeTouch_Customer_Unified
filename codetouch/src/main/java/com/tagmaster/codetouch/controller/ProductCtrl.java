package com.tagmaster.codetouch.controller;

import com.tagmaster.codetouch.domain.*;
import com.tagmaster.codetouch.exception.BadRequestException;
import com.tagmaster.codetouch.service.ProductSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/고객")
public class ProductCtrl {
    private final ProductSvc productSvc;

    @Autowired
    public ProductCtrl(ProductSvc productSvc) {
        this.productSvc = productSvc;
    }

    @ResponseBody
    @PostMapping("/상품/등록")
    public String saveProduct(@ModelAttribute ProductDTO dto) {
        try {
            return productSvc.saveProduct(dto);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @ResponseBody
    @PostMapping("/상품리스트/조회")
    public List<ProductDTO> readProduct(@ModelAttribute UseProductDTO useProductDTO) {
        try {
            return productSvc.readProducts(useProductDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    @ResponseBody
    @PostMapping("/상품/수정")
    public String updateProduct(@ModelAttribute UpdateProductDTO dto) {
        try {
            return productSvc.updateProduct(dto);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    @ResponseBody
    @PostMapping("/상품/삭제")
    public String deleteProduct(@ModelAttribute UseProductDTO useProductDTO) {
        try {
            return productSvc.deleteProduct(useProductDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    @ResponseBody
    @PostMapping("/상품/할인/설정")
    public String setSalesPercentageProduct(@ModelAttribute SalesDTO salesDTO) {
        try {
            return productSvc.setSales(salesDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }

    //카테고리로 나눠 설정맞추기
    @ResponseBody
    @PostMapping("/상품/카테고리")
    public ProductDTO readProductCategory(@ModelAttribute CategoryDTO categoryDTO) {
        try {
            return productSvc.readByCategory(categoryDTO);
        } catch (Exception e) {
            throw new BadRequestException("");
        }
    }
}
