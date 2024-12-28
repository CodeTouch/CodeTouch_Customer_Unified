package com.tagmaster.codetouch.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SalesAnalysisDTO {
    // 총 매출 / 기간별 / 상품별 / 연령별 / 성별
    private int site_id;
    private int total_sales;
    //private int

}
