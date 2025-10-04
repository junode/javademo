package com.example.autobuild.equityreport.service;

import com.example.autobuild.equityreport.model.SimpleEquityReportRow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 简化版权益日报数据服务
 * 生成权益日报的动态数据
 */
public class SimpleEquityReportDataService {
    
    private final Random random = new Random();
    
    /**
     * 生成权益日报数据
     * 
     * @return 权益日报行数据列表
     */
    public List<SimpleEquityReportRow> generateEquityReportData() {
        List<SimpleEquityReportRow> reportData = new ArrayList<>();
        
        // 生成核心配置仓权益数据
        reportData.addAll(generateCoreEquityData());
        
        // 生成相对ALPHA仓权益数据
        reportData.addAll(generateAlphaEquityData());
        
        // 生成敏捷择时仓权益数据
        reportData.addAll(generateAgileEquityData());
        
        return reportData;
    }
    
    /**
     * 生成核心配置仓权益数据
     */
    private List<SimpleEquityReportRow> generateCoreEquityData() {
        List<SimpleEquityReportRow> data = new ArrayList<>();
        
        // 核心配置仓权益占位符行
        data.add(createPlaceholderRow("核心配置仓权益"));
        
        // OCI高股息子项
        List<SimpleEquityReportRow> ociItems = new ArrayList<>();
        
        // 港股通_OCI长期股投
        SimpleEquityReportRow hkOciItem = createProductRow("港股通_OCI长期股投", "HK001", "张经理", "港股通", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN");
        data.add(hkOciItem);
        ociItems.add(hkOciItem);
        
        // 人民币_产险OCI
        SimpleEquityReportRow cnyOciItem = createProductRow("人民币_产险OCI", "CN001", "李经理", "人民币", 
                "2024-01-01", new BigDecimal("0.06"), new BigDecimal("0.08"), "CN");
        data.add(cnyOciItem);
        ociItems.add(cnyOciItem);
        
        // 平安资管_OCI高股息-小计（计算上面两个子项的汇总）
        data.add(createSubtotalRowWithData("平安资管_OCI高股息-小计", ociItems));
        
        // QDII基金子项
        List<SimpleEquityReportRow> qdiiItems = new ArrayList<>();
        
        // QDII基金产品
        SimpleEquityReportRow qdii1 = createProductRow("天虹高端制造混合(QDII)A", "QD001", "王经理", "QDII", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.15"), "US");
        data.add(qdii1);
        qdiiItems.add(qdii1);
        
        SimpleEquityReportRow qdii2 = createProductRow("长城全球新能源车股票发起式(QDII)A", "QD002", "赵经理", "QDII", 
                "2024-01-01", new BigDecimal("0.15"), new BigDecimal("0.18"), "US");
        data.add(qdii2);
        qdiiItems.add(qdii2);
        
        SimpleEquityReportRow qdii3 = createProductRow("长城全球新能源汽车（QDII-LOF）", "QD003", "孙经理", "QDII", 
                "2024-01-01", new BigDecimal("0.18"), new BigDecimal("0.20"), "US");
        data.add(qdii3);
        qdiiItems.add(qdii3);
        
        SimpleEquityReportRow qdii4 = createProductRow("富兰克林国海全球科技互联", "QD004", "周经理", "QDII", 
                "2024-01-01", new BigDecimal("0.20"), new BigDecimal("0.22"), "US");
        data.add(qdii4);
        qdiiItems.add(qdii4);
        
        // 平安资管_QDII海外基金-小计（计算上面4个QDII基金的汇总）
        data.add(createSubtotalRowWithData("平安资管_QDII海外基金-小计", qdiiItems));
        
        // 香港资管子项
        List<SimpleEquityReportRow> hkItems = new ArrayList<>();
        
        // 香港资管产品
        SimpleEquityReportRow hk1 = createProductRow("ABC TEMPI", "HK002", "吴经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.10"), new BigDecimal("0.12"), "HK");
        data.add(hk1);
        hkItems.add(hk1);
        
        SimpleEquityReportRow hk2 = createProductRow("SCHRODER ISF2", "HK003", "郑经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.14"), "HK");
        data.add(hk2);
        hkItems.add(hk2);
        
        SimpleEquityReportRow hk3 = createProductRow("FTSE JAPAN ETF AT UNITED1", "HK004", "冯经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "HK");
        data.add(hk3);
        hkItems.add(hk3);
        
        SimpleEquityReportRow hk4 = createProductRow("US EF RA ETF-USD INC AT UNITED", "HK005", "陈经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.15"), new BigDecimal("0.17"), "HK");
        data.add(hk4);
        hkItems.add(hk4);
        
        SimpleEquityReportRow hk5 = createProductRow("WELLINGTONG GLOBAL RESEARCH", "HK006", "褚经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.13"), new BigDecimal("0.15"), "HK");
        data.add(hk5);
        hkItems.add(hk5);
        
        // 香港资管_QDII海外基金-小计（计算上面5个香港资管产品的汇总）
        data.add(createSubtotalRowWithData("香港资管_QDII海外基金-小计", hkItems));
        
        // 核心配置仓权益-合计
        data.add(createTotalRow("核心配置仓权益-合计"));
        
        return data;
    }
    
    /**
     * 生成相对ALPHA仓权益数据
     */
    private List<SimpleEquityReportRow> generateAlphaEquityData() {
        List<SimpleEquityReportRow> data = new ArrayList<>();
        
        // 相对ALPHA仓权益占位符行
        data.add(createPlaceholderRow("相对ALPHA仓权益"));
        
        // 鑫享32号
        data.add(createProductRow("鑫享32号", "XX001", "钱经理", "鑫享", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN"));
        
        // 平安资管_FOF_红利+800-小计
        data.add(createSubtotalRow("平安资管_FOF_红利+800-小计"));
        
        // 平安证券产品
        data.add(createProductRow("平安证券-平安产品指增1A号", "PS001", "卫经理", "平安证券", 
                "2024-01-01", new BigDecimal("0.10"), new BigDecimal("0.12"), "CN"));
        
        // 平安证券30_红利+800-小计
        data.add(createSubtotalRow("平安证券30_红利+800-小计"));
        
        // 平安基金产品
        data.add(createProductRow("平安基金", "PF001", "蒋经理", "平安基金", 
                "2024-01-01", new BigDecimal("0.09"), new BigDecimal("0.11"), "CN"));
        
        // 平安基金_主动FOF_红利+800-小计
        data.add(createSubtotalRow("平安基金_主动FOF_红利+800-小计"));
        
        // 其他基金产品
        data.add(createProductRow("景顺长城新兴产业混合C", "JC001", "沈经理", "景顺长城", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.14"), "CN"));
        data.add(createProductRow("东方红中证优势成长指数发起C", "DF001", "韩经理", "东方红", 
                "2024-01-01", new BigDecimal("0.11"), new BigDecimal("0.13"), "CN"));
        data.add(createProductRow("中欧价值汇报混合C", "ZO001", "杨经理", "中欧", 
                "2024-01-01", new BigDecimal("0.10"), new BigDecimal("0.12"), "CN"));
        
        // 华泰保兴
        data.add(createProductRow("华泰保兴", "HT001", "朱经理", "华泰保兴", 
                "2024-01-01", new BigDecimal("0.09"), new BigDecimal("0.11"), "CN"));
        
        // 产险_直投FOF_红利+800-小计
        data.add(createSubtotalRow("产险_直投FOF_红利+800-小计"));
        
        // PL长期投资
        data.add(createProductRow("PL长期投资", "PL001", "秦经理", "PL", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN"));
        
        // 产险_PL长期投资_800-小计
        data.add(createSubtotalRow("产险_PL长期投资_800-小计"));
        
        // 相对ALPHA仓权益-合计
        data.add(createTotalRow("相对ALPHA仓权益-合计"));
        
        return data;
    }
    
    /**
     * 生成敏捷择时仓权益数据
     */
    private List<SimpleEquityReportRow> generateAgileEquityData() {
        List<SimpleEquityReportRow> data = new ArrayList<>();
        
        // 敏捷择时仓权益占位符行
        data.add(createPlaceholderRow("敏捷择时仓权益"));
        
        // 平安港股通红利精选
        data.add(createProductRow("平安港股通红利精选", "PG001", "尤经理", "港股通", 
                "2024-01-01", new BigDecimal("0.10"), new BigDecimal("0.12"), "HK"));
        
        // 产品_直投FOF_红利-小计
        data.add(createSubtotalRow("产品_直投FOF_红利-小计"));
        
        // 太平洋卓越灵活配置FOF
        data.add(createProductRow("太平洋卓越灵活配置FOF", "TP001", "许经理", "太平洋", 
                "2024-01-01", new BigDecimal("0.11"), new BigDecimal("0.13"), "CN"));
        
        // 华宝港股通恒生中国
        data.add(createProductRow("华宝港股通恒生中国", "HB001", "何经理", "华宝", 
                "2024-01-01", new BigDecimal("0.09"), new BigDecimal("0.11"), "HK"));
        
        // 产险_直投FOF_800-小计
        data.add(createSubtotalRow("产险_直投FOF_800-小计"));
        
        // ETF产品
        data.add(createProductRow("广发中证香港创新药(QDII-ETF)", "GF001", "吕经理", "ETF", 
                "2024-01-01", new BigDecimal("0.15"), new BigDecimal("0.17"), "HK"));
        data.add(createProductRow("华宝创业板人工智能ETF", "HB002", "施经理", "ETF", 
                "2024-01-01", new BigDecimal("0.18"), new BigDecimal("0.20"), "CN"));
        data.add(createProductRow("黄金股ETF", "HJ001", "张经理", "ETF", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.14"), "CN"));
        data.add(createProductRow("富国中证消费电子", "FG001", "孔经理", "ETF", 
                "2024-01-01", new BigDecimal("0.16"), new BigDecimal("0.18"), "CN"));
        
        // 香港医药
        data.add(createProductRow("香港医药", "HK007", "曹经理", "香港医药", 
                "2024-01-01", new BigDecimal("0.13"), new BigDecimal("0.15"), "HK"));
        
        // 大成恒生科技ETF(QDII)
        data.add(createProductRow("大成恒生科技ETF(QDII)", "DC001", "严经理", "QDII", 
                "2024-01-01", new BigDecimal("0.20"), new BigDecimal("0.22"), "HK"));
        
        // 股指期货
        data.add(createProductRow("股指期货300_多", "IF001", "华经理", "期货", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN"));
        data.add(createProductRow("股指期货300_空_已实现", "IF002", "金经理", "期货", 
                "2024-01-01", new BigDecimal("-0.05"), new BigDecimal("-0.03"), "CN"));
        
        // 华夏国正消费电子主题ETF
        data.add(createProductRow("华夏国正消费电子主题ETF", "HX001", "魏经理", "ETF", 
                "2024-01-01", new BigDecimal("0.17"), new BigDecimal("0.19"), "CN"));
        
        // 平安资管_成长ETF_800-小计
        data.add(createSubtotalRow("平安资管_成长ETF_800-小计"));
        
        // 华宝标普港股通低波ETF
        data.add(createProductRow("华宝标普港股通低波ETF", "HB003", "陶经理", "ETF", 
                "2024-01-01", new BigDecimal("0.07"), new BigDecimal("0.09"), "HK"));
        
        // 红利低波
        data.add(createProductRow("红利低波", "HL001", "姜经理", "红利", 
                "2024-01-01", new BigDecimal("0.06"), new BigDecimal("0.08"), "CN"));
        
        // 央企ETF
        data.add(createProductRow("央企ETF", "YQ001", "戚经理", "ETF", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN"));
        
        // 平安资管_价值ETF_红利-小计
        data.add(createSubtotalRow("平安资管_价值ETF_红利-小计"));
        
        // 敏捷择时仓权益-合计
        data.add(createTotalRow("敏捷择时仓权益-合计"));
        
        // 权益合计
        data.add(createTotalRow("权益合计"));
        
        return data;
    }
    
    /**
     * 创建占位符行（分类标题行）
     */
    private SimpleEquityReportRow createPlaceholderRow(String title) {
        SimpleEquityReportRow row = new SimpleEquityReportRow();
        row.setProduct(title);
        // 占位符行只有产品名称，其他字段为空
        return row;
    }
    
    /**
     * 创建产品行
     */
    private SimpleEquityReportRow createProductRow(String product, String code, String responsibility, 
                                           String productType, String startDate, 
                                           BigDecimal returnTarget, BigDecimal customReturnTarget, 
                                           String countryId) {
        SimpleEquityReportRow row = new SimpleEquityReportRow();
        row.setProduct(product);
        row.setCode(code);
        row.setResponsibility(responsibility);
        row.setProductType(productType);
        row.setStartDate(startDate);
        row.setReturnTarget(returnTarget);
        row.setCustomReturnTarget(customReturnTarget);
        row.setCountryId(countryId);
        
        // 生成随机数据
        generateRandomData(row);
        
        return row;
    }
    
    /**
     * 创建小计行
     */
    private SimpleEquityReportRow createSubtotalRow(String product) {
        SimpleEquityReportRow row = new SimpleEquityReportRow();
        row.setProduct(product);
        // 小计行只有产品名称，其他字段为空（实际应用中需要计算汇总数据）
        return row;
    }
    
    /**
     * 创建小计行（带汇总数据）
     */
    private SimpleEquityReportRow createSubtotalRowWithData(String product, List<SimpleEquityReportRow> subItems) {
        SimpleEquityReportRow row = new SimpleEquityReportRow();
        row.setProduct(product);
        
        if (subItems != null && !subItems.isEmpty()) {
            // 计算汇总数据
            BigDecimal totalBeginSize = BigDecimal.ZERO;
            BigDecimal totalEndSize = BigDecimal.ZERO;
            BigDecimal totalAvgSize = BigDecimal.ZERO;
            BigDecimal totalIncome = BigDecimal.ZERO;
            
            for (SimpleEquityReportRow item : subItems) {
                if (item.getBeginSize() != null) {
                    totalBeginSize = totalBeginSize.add(item.getBeginSize());
                }
                if (item.getEndSize() != null) {
                    totalEndSize = totalEndSize.add(item.getEndSize());
                }
                if (item.getAvgSize() != null) {
                    totalAvgSize = totalAvgSize.add(item.getAvgSize());
                }
                if (item.getYield() != null && item.getAvgSize() != null) {
                    BigDecimal itemIncome = item.getAvgSize().multiply(item.getYield()).multiply(new BigDecimal("100"));
                    totalIncome = totalIncome.add(itemIncome);
                }
            }
            
            // 设置汇总数据
            row.setBeginSize(totalBeginSize);
            row.setEndSize(totalEndSize);
            row.setAvgSize(totalAvgSize);
            
            // 计算加权平均收益率
            if (totalAvgSize.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal weightedYield = totalIncome.divide(totalAvgSize.multiply(new BigDecimal("100")), 4, BigDecimal.ROUND_HALF_UP);
                row.setYield(weightedYield);
            }
        }
        
        return row;
    }
    
    /**
     * 创建合计行
     */
    private SimpleEquityReportRow createTotalRow(String product) {
        SimpleEquityReportRow row = new SimpleEquityReportRow();
        row.setProduct(product);
        return row;
    }
    
    /**
     * 生成随机数据
     */
    private void generateRandomData(SimpleEquityReportRow row) {
        // 生成规模数据（亿）
        BigDecimal beginSize = new BigDecimal(random.nextInt(100) + 10).setScale(2);
        BigDecimal endSize = new BigDecimal(random.nextInt(100) + 10).setScale(2);
        BigDecimal avgSize = beginSize.add(endSize).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
        
        row.setBeginSize(beginSize);
        row.setEndSize(endSize);
        row.setAvgSize(avgSize);
        
        // 生成收益率数据
        BigDecimal yield = new BigDecimal(random.nextDouble() * 0.2 - 0.1).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal benchmarkYield = new BigDecimal(random.nextDouble() * 0.15 - 0.05).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal excessYield = yield.subtract(benchmarkYield);
        
        row.setYield(yield);
        row.setBenchmarkYield(benchmarkYield);
        row.setExcessYield(excessYield);
        
        // 设置分类
        String productName = row.getProduct();
        if (productName != null) {
            if (productName.contains("QDII")) {
                row.setCategory("QDII");
            } else if (productName.contains("ETF")) {
                row.setCategory("ETF");
            } else if (productName.contains("港股")) {
                row.setCategory("港股");
            } else {
                row.setCategory("A股");
            }
        }
        
        // 设置风险指标
        setRiskIndicators(row);
    }
    
    /**
     * 设置风险指标字段
     */
    private void setRiskIndicators(SimpleEquityReportRow row) {
        // 生成随机但合理的风险指标值
        row.setVolatility(new BigDecimal(0.10 + random.nextDouble() * 0.20)); // 0.10-0.30
        row.setTrackingError(new BigDecimal(0.02 + random.nextDouble() * 0.08)); // 0.02-0.10
        row.setSharper(new BigDecimal(0.5 + random.nextDouble() * 1.5)); // 0.5-2.0
        row.setInfoRate(new BigDecimal(-0.5 + random.nextDouble() * 1.5)); // -0.5-1.0
        row.setSortino(new BigDecimal(0.3 + random.nextDouble() * 1.2)); // 0.3-1.5
        row.setRecentYearMaxDraw(new BigDecimal(-0.20 + random.nextDouble() * 0.10)); // -0.20 to -0.10
        row.setRecentSuperMaxdraw(new BigDecimal(-0.15 + random.nextDouble() * 0.08)); // -0.15 to -0.07
        row.setCalmar(new BigDecimal(0.5 + random.nextDouble() * 2.0)); // 0.5-2.5
        row.setSuperCalmar(new BigDecimal(0.3 + random.nextDouble() * 1.8)); // 0.3-2.1
        row.setYtdCalmar(new BigDecimal(0.4 + random.nextDouble() * 1.6)); // 0.4-2.0
        row.setYtdSuperCalmar(new BigDecimal(0.2 + random.nextDouble() * 1.4)); // 0.2-1.6
    }
}
