package com.example.autobuild.equityreport.model;

import java.math.BigDecimal;

/**
 * 简化版权益日报行数据模型
 * 包含权益日报的核心数据
 */
public class SimpleEquityReportRow {
    
    private String product;
    private String code;
    private String responsibility;
    private String productType;
    private String startDate;
    private BigDecimal returnTarget;
    private BigDecimal customReturnTarget;
    private String countryId;
    private BigDecimal beginSize;
    private BigDecimal endSize;
    private BigDecimal avgSize;
    private BigDecimal yield;
    private BigDecimal benchmarkYield;
    private BigDecimal excessYield;
    private String category;
    
    // 新增风险指标字段
    private BigDecimal volatility;
    private BigDecimal trackingError;
    private BigDecimal sharper;
    private BigDecimal infoRate;
    private BigDecimal sortino;
    private BigDecimal recentYearMaxDraw;
    private BigDecimal recentSuperMaxdraw;
    private BigDecimal calmar;
    private BigDecimal superCalmar;
    private BigDecimal ytdCalmar;
    private BigDecimal ytdSuperCalmar;
    
    // 构造函数
    public SimpleEquityReportRow() {}
    
    public SimpleEquityReportRow(String product, String code, String responsibility, 
                                String productType, String startDate, 
                                BigDecimal returnTarget, BigDecimal customReturnTarget, 
                                String countryId, BigDecimal beginSize, BigDecimal endSize, 
                                BigDecimal avgSize, BigDecimal yield, BigDecimal benchmarkYield, 
                                BigDecimal excessYield, String category) {
        this.product = product;
        this.code = code;
        this.responsibility = responsibility;
        this.productType = productType;
        this.startDate = startDate;
        this.returnTarget = returnTarget;
        this.customReturnTarget = customReturnTarget;
        this.countryId = countryId;
        this.beginSize = beginSize;
        this.endSize = endSize;
        this.avgSize = avgSize;
        this.yield = yield;
        this.benchmarkYield = benchmarkYield;
        this.excessYield = excessYield;
        this.category = category;
    }
    
    // Getter和Setter方法
    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }
    
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    
    public String getResponsibility() { return responsibility; }
    public void setResponsibility(String responsibility) { this.responsibility = responsibility; }
    
    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }
    
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    
    public BigDecimal getReturnTarget() { return returnTarget; }
    public void setReturnTarget(BigDecimal returnTarget) { this.returnTarget = returnTarget; }
    
    public BigDecimal getCustomReturnTarget() { return customReturnTarget; }
    public void setCustomReturnTarget(BigDecimal customReturnTarget) { this.customReturnTarget = customReturnTarget; }
    
    public String getCountryId() { return countryId; }
    public void setCountryId(String countryId) { this.countryId = countryId; }
    
    public BigDecimal getBeginSize() { return beginSize; }
    public void setBeginSize(BigDecimal beginSize) { this.beginSize = beginSize; }
    
    public BigDecimal getEndSize() { return endSize; }
    public void setEndSize(BigDecimal endSize) { this.endSize = endSize; }
    
    public BigDecimal getAvgSize() { return avgSize; }
    public void setAvgSize(BigDecimal avgSize) { this.avgSize = avgSize; }
    
    public BigDecimal getYield() { return yield; }
    public void setYield(BigDecimal yield) { this.yield = yield; }
    
    public BigDecimal getBenchmarkYield() { return benchmarkYield; }
    public void setBenchmarkYield(BigDecimal benchmarkYield) { this.benchmarkYield = benchmarkYield; }
    
    public BigDecimal getExcessYield() { return excessYield; }
    public void setExcessYield(BigDecimal excessYield) { this.excessYield = excessYield; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    // 新增风险指标字段的getter和setter方法
    public BigDecimal getVolatility() { return volatility; }
    public void setVolatility(BigDecimal volatility) { this.volatility = volatility; }
    
    public BigDecimal getTrackingError() { return trackingError; }
    public void setTrackingError(BigDecimal trackingError) { this.trackingError = trackingError; }
    
    public BigDecimal getSharper() { return sharper; }
    public void setSharper(BigDecimal sharper) { this.sharper = sharper; }
    
    public BigDecimal getInfoRate() { return infoRate; }
    public void setInfoRate(BigDecimal infoRate) { this.infoRate = infoRate; }
    
    public BigDecimal getSortino() { return sortino; }
    public void setSortino(BigDecimal sortino) { this.sortino = sortino; }
    
    public BigDecimal getRecentYearMaxDraw() { return recentYearMaxDraw; }
    public void setRecentYearMaxDraw(BigDecimal recentYearMaxDraw) { this.recentYearMaxDraw = recentYearMaxDraw; }
    
    public BigDecimal getRecentSuperMaxdraw() { return recentSuperMaxdraw; }
    public void setRecentSuperMaxdraw(BigDecimal recentSuperMaxdraw) { this.recentSuperMaxdraw = recentSuperMaxdraw; }
    
    public BigDecimal getCalmar() { return calmar; }
    public void setCalmar(BigDecimal calmar) { this.calmar = calmar; }
    
    public BigDecimal getSuperCalmar() { return superCalmar; }
    public void setSuperCalmar(BigDecimal superCalmar) { this.superCalmar = superCalmar; }
    
    public BigDecimal getYtdCalmar() { return ytdCalmar; }
    public void setYtdCalmar(BigDecimal ytdCalmar) { this.ytdCalmar = ytdCalmar; }
    
    public BigDecimal getYtdSuperCalmar() { return ytdSuperCalmar; }
    public void setYtdSuperCalmar(BigDecimal ytdSuperCalmar) { this.ytdSuperCalmar = ytdSuperCalmar; }
}
