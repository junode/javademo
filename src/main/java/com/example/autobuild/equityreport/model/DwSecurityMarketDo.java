package com.example.autobuild.equityreport.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 证券市场数据对象
 * 对应模板中的DwSecurityMarketDo实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DwSecurityMarketDo {
    
    /**
     * 沪深300 Beta
     */
    private BigDecimal hs300Beta;
    
    /**
     * 中证800 Beta
     */
    private BigDecimal zs800beta;
    
    /**
     * 估计价值
     */
    private BigDecimal estimateValue;
}
