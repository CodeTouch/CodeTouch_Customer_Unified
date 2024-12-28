package com.tagmaster.codetouch.service;

import com.tagmaster.codetouch.domain.DashboardDTO;
import com.tagmaster.codetouch.mapper.DashboardMapper;
import com.tagmaster.codetouch.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final DashboardMapper dashboardMapper; // 매출 및 상품 통계 정보를 가져오는 Mapper
    private final UserMapper userMapper; // 사용자 관련 정보를 가져오는 Mapper

    // 의존성 주입을 통해 DashboardMapper와 UserMapper 초기화
    @Autowired
    public DashboardService(DashboardMapper dashboardMapper, UserMapper userMapper) {
        this.dashboardMapper = dashboardMapper;
        this.userMapper = userMapper;
    }

    // 대시보드 통계 정보를 반환하는 메서드
    // - 입력: siteId (분석 대상 사이트의 ID)
    // - 출력: DashboardStatisticsDTO (대시보드에서 표시할 통계 데이터)

    public DashboardDTO getDashboardStatistics(int siteId) {
        DashboardDTO stats = new DashboardDTO(); // 통계 데이터를 담을 DTO 객체 생성

        // 기간별 매출 데이터 조회 및 설정
        stats.setSalesByPeriod(dashboardMapper.getSalesByPeriod(siteId));

        // 인기 상품 데이터 조회 및 설정
        stats.setPopularProducts(dashboardMapper.getPopularProducts(siteId));

        // 총 매출 계산
        // - 기간별 매출 데이터를 기준으로 모든 매출 합계를 계산하여 `totalSales`에 저장
        stats.setTotalSales(stats.getSalesByPeriod().values().stream().mapToInt(Integer::intValue).sum());

        return stats; // 최종 통계 데이터를 반환
    }
}

