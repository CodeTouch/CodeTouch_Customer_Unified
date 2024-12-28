package com.tagmaster.codetouch.domain;

import lombok.Data;
import java.util.Map;
@Data
public class DashboardDTO {
        private int totalSales;
        private Map<String, Integer> popularProducts;
        private Map<String, Integer> salesByPeriod;
        private Map<String, Integer> visitorByPeriod;
}
