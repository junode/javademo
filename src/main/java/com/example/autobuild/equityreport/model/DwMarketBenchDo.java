package com.example.autobuild.equityreport.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 市场基准数据对象
 * 对应模板中的DwMarketBenchDo实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DwMarketBenchDo {
    
    /**
     * 基准收益率
     */
    private BigDecimal benchYield;
    
    /**
     * 自定义收益率
     */
    private BigDecimal customYeild;
}
