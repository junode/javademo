package com.example.autobuild.equityreport.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 计算单元数据对象
 * 对应模板中的CalculateUnitDo实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateUnitDo {
    
    /**
     * 节点代码
     */
    private String nodeCode;
    
    /**
     * 父节点代码
     */
    private String parentNodeCode;
    
    /**
     * 证券代码
     */
    private String securityCode;
    
    /**
     * 期初资产规模
     */
    private BigDecimal beginAssetSize;
    
    /**
     * 期末资产规模
     */
    private BigDecimal endAssetSize;
    
    
    /**
     * 收益率
     */
    private BigDecimal yield;
    
    /**
     * 免税后规模加权收益率
     */
    private BigDecimal afterTaxSizeWeightYield;
    
    /**
     * 免税后累计收益率
     */
    private BigDecimal afterTaxAccumYield;
    
    /**
     * 免税后CII收益额
     */
    private BigDecimal afterTaxCii;
    
    /**
     * TII收益额
     */
    private BigDecimal tii;
    
    /**
     * 规模加权收益率
     */
    private BigDecimal weightAssetSizeIncome;
    
    /**
     * CII收益额
     */
    private BigDecimal cii;
    
    /**
     * 免税后自定义超额收益
     */
    private BigDecimal afterTaxCustomSuperCii;
    
    /**
     * 免税后基准收益额
     */
    private BigDecimal afterTaxBenchmarkCii;

}
