package com.example.autobuild.equityreport.service;

import com.example.autobuild.equityreport.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 权益日报数据服务
 * 生成权益日报的动态数据
 */
public class EquityReportDataService {
    
    private final Random random = new Random();
    
    /**
     * 生成权益日报数据
     * 
     * @return 权益日报行数据列表
     */
    public List<EquityReportRow> generateEquityReportData() {
        List<EquityReportRow> reportData = new ArrayList<>();
        
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
    private List<EquityReportRow> generateCoreEquityData() {
        List<EquityReportRow> data = new ArrayList<>();
        
        // 核心配置仓权益标题行
        data.add(createTitleRow("核心配置仓权益"));
        
        // 港股通_OCI长期股投
        data.add(createProductRow("港股通_OCI长期股投", "HK001", "张经理", "港股通", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "CN"));
        
        // 人民币_产险OCI
        data.add(createProductRow("人民币_产险OCI", "CN001", "李经理", "人民币", 
                "2024-01-01", new BigDecimal("0.06"), new BigDecimal("0.08"), "CN"));
        
        // 平安资管_OCI高股息-小计
        data.add(createSubtotalRow("平安资管_OCI高股息-小计"));
        
        // QDII基金产品
        data.add(createProductRow("天虹高端制造混合(QDII)A", "QD001", "王经理", "QDII", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.15"), "US"));
        data.add(createProductRow("长城全球新能源车股票发起式(QDII)A", "QD002", "赵经理", "QDII", 
                "2024-01-01", new BigDecimal("0.15"), new BigDecimal("0.18"), "US"));
        data.add(createProductRow("长城全球新能源汽车（QDII-LOF）", "QD003", "孙经理", "QDII", 
                "2024-01-01", new BigDecimal("0.18"), new BigDecimal("0.20"), "US"));
        data.add(createProductRow("富兰克林国海全球科技互联", "QD004", "周经理", "QDII", 
                "2024-01-01", new BigDecimal("0.20"), new BigDecimal("0.22"), "US"));
        
        // 平安资管_QDII海外基金-小计
        data.add(createSubtotalRow("平安资管_QDII海外基金-小计"));
        
        // 香港资管产品
        data.add(createProductRow("ABC TEMPI", "HK002", "吴经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.10"), new BigDecimal("0.12"), "HK"));
        data.add(createProductRow("SCHRODER ISF2", "HK003", "郑经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.12"), new BigDecimal("0.14"), "HK"));
        data.add(createProductRow("FTSE JAPAN ETF AT UNITED1", "HK004", "冯经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.08"), new BigDecimal("0.10"), "HK"));
        data.add(createProductRow("US EF RA ETF-USD INC AT UNITED", "HK005", "陈经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.15"), new BigDecimal("0.17"), "HK"));
        data.add(createProductRow("WELLINGTONG GLOBAL RESEARCH", "HK006", "褚经理", "香港资管", 
                "2024-01-01", new BigDecimal("0.13"), new BigDecimal("0.15"), "HK"));
        
        // 香港资管_QDII海外基金-小计
        data.add(createSubtotalRow("香港资管_QDII海外基金-小计"));
        
        // 核心配置仓权益-合计
        data.add(createTotalRow("核心配置仓权益-合计"));
        
        return data;
    }
    
    /**
     * 生成相对ALPHA仓权益数据
     */
    private List<EquityReportRow> generateAlphaEquityData() {
        List<EquityReportRow> data = new ArrayList<>();
        
        // 相对ALPHA仓权益标题行
        data.add(createTitleRow("相对ALPHA仓权益"));
        
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
    private List<EquityReportRow> generateAgileEquityData() {
        List<EquityReportRow> data = new ArrayList<>();
        
        // 敏捷择时仓权益标题行
        data.add(createTitleRow("敏捷择时仓权益"));
        
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
     * 创建标题行
     */
    private EquityReportRow createTitleRow(String title) {
        EquityReportRow row = new EquityReportRow();
        row.setProduct(title);
        return row;
    }
    
    /**
     * 创建产品行
     */
    private EquityReportRow createProductRow(String product, String code, String responsibility, 
                                           String productType, String startDate, 
                                           BigDecimal returnTarget, BigDecimal customReturnTarget, 
                                           String countryId) {
        EquityReportRow row = new EquityReportRow();
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
    private EquityReportRow createSubtotalRow(String product) {
        EquityReportRow row = new EquityReportRow();
        row.setProduct(product);
        return row;
    }
    
    /**
     * 创建合计行
     */
    private EquityReportRow createTotalRow(String product) {
        EquityReportRow row = new EquityReportRow();
        row.setProduct(product);
        return row;
    }
    
    /**
     * 生成随机数据
     */
    private void generateRandomData(EquityReportRow row) {
        // 生成规模数据（亿）
        BigDecimal beginSize = new BigDecimal(random.nextInt(100) + 10).setScale(2);
        BigDecimal endSize = new BigDecimal(random.nextInt(100) + 10).setScale(2);
        BigDecimal avgSize = beginSize.add(endSize).divide(new BigDecimal("2"), 2, BigDecimal.ROUND_HALF_UP);
        
        row.setBeginSize(beginSize);
        row.setEndSize(endSize);
        row.setAvgSize(avgSize);
        row.setAvgSize2Weeks(avgSize.multiply(new BigDecimal("0.95")).setScale(2, BigDecimal.ROUND_HALF_UP));
        row.setAvgSizeWithZero(avgSize.multiply(new BigDecimal("1.05")).setScale(2, BigDecimal.ROUND_HALF_UP));
        row.setYtdAvg(avgSize.multiply(new BigDecimal("1.02")).setScale(2, BigDecimal.ROUND_HALF_UP));
        
        // 生成收益率数据
        BigDecimal yield = new BigDecimal(random.nextDouble() * 0.2 - 0.1).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal benchmarkYield = new BigDecimal(random.nextDouble() * 0.15 - 0.05).setScale(4, BigDecimal.ROUND_HALF_UP);
        BigDecimal excessYield = yield.subtract(benchmarkYield);
        
        row.setYield(yield);
        row.setBenchmarkYield(benchmarkYield);
        row.setExcessYield(excessYield);
        row.setCustomTargetYield(row.getReturnTarget());
        row.setCustomExcessYield(yield.subtract(row.getReturnTarget()));
        
        // 生成收益额数据（百万）
        BigDecimal income = avgSize.multiply(yield).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        row.setIncome(income);
        row.setIncomeAfterTax(income.multiply(new BigDecimal("0.95")).setScale(2, BigDecimal.ROUND_HALF_UP));
        row.setIncomeCiiAfterTax(income.multiply(new BigDecimal("0.90")).setScale(2, BigDecimal.ROUND_HALF_UP));
        row.setIncomeTii(income.multiply(new BigDecimal("0.85")).setScale(2, BigDecimal.ROUND_HALF_UP));
        
        // 生成超额收益数据
        BigDecimal excessIncome = avgSize.multiply(excessYield).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP);
        row.setExcessIncomeAfterTax(excessIncome.multiply(new BigDecimal("0.95")).setScale(2, BigDecimal.ROUND_HALF_UP));
        row.setCustomExcessIncomeAfterTax(avgSize.multiply(row.getCustomExcessYield()).multiply(new BigDecimal("100")).setScale(2, BigDecimal.ROUND_HALF_UP));
        
        // 生成Beta数据
        row.setHs300(new BigDecimal(random.nextDouble() * 2 - 1).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setZs800(new BigDecimal(random.nextDouble() * 2 - 1).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setCyb(new BigDecimal(random.nextDouble() * 2 - 1).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setZzhl(new BigDecimal(random.nextDouble() * 2 - 1).setScale(4, BigDecimal.ROUND_HALF_UP));
        
        // 生成风险指标
        row.setAnnualizedVolatility(new BigDecimal(random.nextDouble() * 0.3 + 0.1).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setTrackingError(new BigDecimal(random.nextDouble() * 0.1 + 0.02).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setSharpeRatio(new BigDecimal(random.nextDouble() * 2 - 0.5).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setInformationRatio(new BigDecimal(random.nextDouble() * 1.5 - 0.3).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setSortinoRatio(new BigDecimal(random.nextDouble() * 2.5 - 0.2).setScale(4, BigDecimal.ROUND_HALF_UP));
        
        // 生成回撤数据
        row.setMaxDrawdown1Y(new BigDecimal(random.nextDouble() * 0.2 + 0.05).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setExcessMaxDrawdown1Y(new BigDecimal(random.nextDouble() * 0.15 + 0.03).setScale(4, BigDecimal.ROUND_HALF_UP));
        
        // 生成Calmar比率
        row.setCalmarRatio1Y(yield.divide(row.getMaxDrawdown1Y(), 4, BigDecimal.ROUND_HALF_UP));
        row.setExcessCalmarRatio1Y(excessYield.divide(row.getExcessMaxDrawdown1Y(), 4, BigDecimal.ROUND_HALF_UP));
        row.setCalmarRatioYTD(row.getCalmarRatio1Y().multiply(new BigDecimal("1.1")).setScale(4, BigDecimal.ROUND_HALF_UP));
        row.setExcessCalmarRatioYTD(row.getExcessCalmarRatio1Y().multiply(new BigDecimal("1.1")).setScale(4, BigDecimal.ROUND_HALF_UP));
        
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
    }
    
    /**
     * 生成示例实体数据
     */
    public CalculateUnitDo generateCalculateUnitDo() {
        CalculateUnitDo unit = new CalculateUnitDo();
        unit.setNodeCode("NODE001");
        unit.setSecurityCode("SEC001");
        unit.setBeginAssetSize(new BigDecimal("100.50"));
        unit.setEndAssetSize(new BigDecimal("105.20"));
        unit.setYield(new BigDecimal("0.0468"));
        unit.setAfterTaxSizeWeightYield(new BigDecimal("0.0445"));
        unit.setAfterTaxAccumYield(new BigDecimal("0.0468"));
        unit.setAfterTaxCii(new BigDecimal("4.80"));
        unit.setTii(new BigDecimal("5.20"));
        unit.setWeightAssetSizeIncome(new BigDecimal("0.0468"));
        unit.setCii(new BigDecimal("5.20"));
        unit.setAfterTaxCustomSuperCii(new BigDecimal("0.80"));
        unit.setAfterTaxBenchmarkCii(new BigDecimal("3.50"));
        
        // 风险指标已从模板中移除，不再设置
        
        return unit;
    }
    
    public AssetDo generateAssetDo() {
        AssetDo asset = new AssetDo();
        // 简化后的模板不再使用AssetDo的任何属性
        return asset;
    }
    
    public DwMarketBenchDo generateDwMarketBenchDo() {
        DwMarketBenchDo bench = new DwMarketBenchDo();
        bench.setBenchYield(new BigDecimal("0.035"));
        bench.setCustomYeild(new BigDecimal("0.08"));
        return bench;
    }
    
    public DwSecurityMarketDo generateDwSecurityMarketDo() {
        DwSecurityMarketDo market = new DwSecurityMarketDo();
        market.setHs300Beta(new BigDecimal("1.2"));
        market.setZs800beta(new BigDecimal("1.1"));
        market.setEstimateValue(new BigDecimal("105.20"));
        return market;
    }
}
