package com.example.autobuild.equityreport.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权益日报行数据模型
 * 包含一行权益日报的所有数据
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EquityReportRow {
    
    /**
     * 产品名称
     */
    private String product;
    
    /**
     * 代码
     */
    private String code;
    
    /**
     * 责任人
     */
    private String responsibility;
    
    /**
     * 产品形式
     */
    private String productType;
    
    /**
     * 起期
     */
    private String startDate;
    
    /**
     * 收益目标
     */
    private BigDecimal returnTarget;
    
    /**
     * 自定义收益目标
     */
    private BigDecimal customReturnTarget;
    
    /**
     * 国家标识
     */
    private String countryId;
    
    /**
     * 年初规模
     */
    private BigDecimal beginSize;
    
    /**
     * 期末规模
     */
    private BigDecimal endSize;
    
    /**
     * 平均规模
     */
    private BigDecimal avgSize;
    
    /**
     * 平均规模(2周)
     */
    private BigDecimal avgSize2Weeks;
    
    /**
     * 平均规模(含0)
     */
    private BigDecimal avgSizeWithZero;
    
    /**
     * YTD平均
     */
    private BigDecimal ytdAvg;
    
    /**
     * 收益率
     */
    private BigDecimal yield;
    
    /**
     * 基准收益率
     */
    private BigDecimal benchmarkYield;
    
    /**
     * 超额收益率
     */
    private BigDecimal excessYield;
    
    /**
     * 自定义目标收益率
     */
    private BigDecimal customTargetYield;
    
    /**
     * 自定义超额收益率
     */
    private BigDecimal customExcessYield;
    
    /**
     * 规模加权收益率(免税后)
     */
    private BigDecimal sizeWeightYieldAfterTax;
    
    /**
     * 累计收益率(免税后)
     */
    private BigDecimal accumYieldAfterTax;
    
    /**
     * 收益额(百万,免税后)-CII
     */
    private BigDecimal incomeCiiAfterTax;
    
    /**
     * 收益额(百万)-TII
     */
    private BigDecimal incomeTii;
    
    /**
     * 超额收益(百万,免税后)
     */
    private BigDecimal excessIncomeAfterTax;
    
    /**
     * 自定义超额收益(百万,免税后)
     */
    private BigDecimal customExcessIncomeAfterTax;
    
    /**
     * 规模加权收益率
     */
    private BigDecimal sizeWeightYield;
    
    /**
     * 收益额(百万)
     */
    private BigDecimal income;
    
    /**
     * 沪深300
     */
    private BigDecimal hs300;
    
    /**
     * 中证800
     */
    private BigDecimal zs800;
    
    /**
     * 创业板
     */
    private BigDecimal cyb;
    
    /**
     * 中证红利
     */
    private BigDecimal zzhl;
    
    /**
     * 国内基准
     */
    private BigDecimal domesticBenchmark;
    
    /**
     * OCI基准
     */
    private BigDecimal ociBenchmark;
    
    /**
     * 海外基准
     */
    private BigDecimal overseasBenchmark;
    
    /**
     * 10d,99%
     */
    private BigDecimal tenDay99Percent;
    
    /**
     * 收益额(百万,免税后)
     */
    private BigDecimal incomeAfterTax;
    
    /**
     * 规模变化(亿)
     */
    private BigDecimal sizeChange;
    
    /**
     * 超额收益(百万,免税后)
     */
    private BigDecimal excessIncomeAfterTax2;
    
    /**
     * 收益率
     */
    private BigDecimal yield2;
    
    /**
     * 基准收益率
     */
    private BigDecimal benchmarkYield2;
    
    /**
     * 超额收益率
     */
    private BigDecimal excessYield2;
    
    /**
     * 自定义目标收益率
     */
    private BigDecimal customTargetYield2;
    
    /**
     * 自定义超额收益率
     */
    private BigDecimal customExcessYield2;
    
    /**
     * 收益额(百万,免税后)
     */
    private BigDecimal incomeAfterTax2;
    
    /**
     * 规模变化(亿)
     */
    private BigDecimal sizeChange2;
    
    /**
     * 超额收益(百万,免税后)
     */
    private BigDecimal excessIncomeAfterTax3;
    
    /**
     * 自定义超额收益(百万,免税后)
     */
    private BigDecimal customExcessIncomeAfterTax2;
    
    /**
     * 收益率
     */
    private BigDecimal yield3;
    
    /**
     * 基准收益率
     */
    private BigDecimal benchmarkYield3;
    
    /**
     * 超额收益率
     */
    private BigDecimal excessYield3;
    
    /**
     * 自定义目标收益率
     */
    private BigDecimal customTargetYield3;
    
    /**
     * 自定义超额收益率
     */
    private BigDecimal customExcessYield3;
    
    /**
     * 收益额(百万,免税后)
     */
    private BigDecimal incomeAfterTax3;
    
    /**
     * 规模变化(亿)
     */
    private BigDecimal sizeChange3;
    
    /**
     * 超额收益(百万,免税后)
     */
    private BigDecimal excessIncomeAfterTax4;
    
    /**
     * 自定义超额收益(百万,免税后)
     */
    private BigDecimal customExcessIncomeAfterTax3;
    
    /**
     * 收益率
     */
    private BigDecimal yield4;
    
    /**
     * 基准收益率
     */
    private BigDecimal benchmarkYield4;
    
    /**
     * 超额收益率
     */
    private BigDecimal excessYield4;
    
    /**
     * 自定义目标收益率
     */
    private BigDecimal customTargetYield4;
    
    /**
     * 自定义超额收益率
     */
    private BigDecimal customExcessYield4;
    
    /**
     * 年化波动率
     */
    private BigDecimal annualizedVolatility;
    
    /**
     * 跟踪误差
     */
    private BigDecimal trackingError;
    
    /**
     * 夏普比率
     */
    private BigDecimal sharpeRatio;
    
    /**
     * 信息比率
     */
    private BigDecimal informationRatio;
    
    /**
     * Sortino比率
     */
    private BigDecimal sortinoRatio;
    
    /**
     * 最近一年最大回撤
     */
    private BigDecimal maxDrawdown1Y;
    
    /**
     * 最近一年超额最大回撤
     */
    private BigDecimal excessMaxDrawdown1Y;
    
    /**
     * 近一年Calmar比率
     */
    private BigDecimal calmarRatio1Y;
    
    /**
     * 近一年超额Calmar比率
     */
    private BigDecimal excessCalmarRatio1Y;
    
    /**
     * 今年以来Calmar比率
     */
    private BigDecimal calmarRatioYTD;
    
    /**
     * 今年以来超额Calmar比率
     */
    private BigDecimal excessCalmarRatioYTD;
    
    /**
     * 分类
     */
    private String category;
}
