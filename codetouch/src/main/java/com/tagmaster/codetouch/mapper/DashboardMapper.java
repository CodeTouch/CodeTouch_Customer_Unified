package com.tagmaster.codetouch.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface DashboardMapper {

    // 기간별 매출 데이터를 가져오는 메서드
    // - 결과: 특정 사이트에서 결제된 매출을 월별(`%Y-%m` 형식)로 그룹화하여 합계를 계산
    // - 파라미터: siteId (분석 대상 사이트 ID)
    // - 반환값: List<Map<String, Object>> 형식으로 각 월(period)과 매출(sales)을 반환
    @Select("SELECT DATE_FORMAT(ph.create_at, '%Y-%m') AS period, SUM(p.price * ph.quantity) AS sales " +
            "FROM pay_history ph " +
            "JOIN product p " +
            "ON ph.pd_id = p.pd_id " +
            "WHERE ph.site_id = #{site_id} " +
            "GROUP BY period")
    Map<String, Integer> getSalesByPeriod(int site_id);

    // 인기 상품 데이터를 가져오는 메서드
    // - 결과: 특정 사이트에서 결제된 상품을 판매 수량 기준으로 내림차순 정렬하여 상위 10개를 반환
    // - 파라미터: siteId (분석 대상 사이트 ID)
    // - 반환값: List<Map<String, Object>> 형식으로 상품 ID(pd_id)와 판매 수량(sales_count)을 반환
    @Select("SELECT ph.pd_id, p.image, COUNT(*) AS sales_count " +
            "FROM pay_history ph " +
            "JOIN product p " +
            "ON ph.pd_id=p.pd_id " +
            "WHERE ph.site_id = #{siteId} " +
            "GROUP BY pd_id " +
            "ORDER BY sales_count DESC " +
            "LIMIT 10")
    Map<String, Integer> getPopularProducts(int site_id);

}
