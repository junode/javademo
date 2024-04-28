package com.junode.autowired_extentsion;

/**
 * @author junode
 * @version 1.0.0
 * @Description 持仓对象
 * @createTime 2024年04月11日 23:44:00
 */
public class PositionDo {
    private String positionDate;
    private String securityId;
    private String securityMarketNo;
    private String positionKey;
    private String holdingUnits;
    private Double estimateValue;
    private String securityName;
    private Double floatRate;

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecurityMarketNo() {
        return securityMarketNo;
    }

    public void setSecurityMarketNo(String securityMarketNo) {
        this.securityMarketNo = securityMarketNo;
    }

    public String getPositionKey() {
        return positionKey;
    }

    public void setPositionKey(String positionKey) {
        this.positionKey = positionKey;
    }

    public String getHoldingUnits() {
        return holdingUnits;
    }

    public void setHoldingUnits(String holdingUnits) {
        this.holdingUnits = holdingUnits;
    }

    public Double getEstimateValue() {
        return estimateValue;
    }

    public void setEstimateValue(Double estimateValue) {
        this.estimateValue = estimateValue;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(String positionDate) {
        this.positionDate = positionDate;
    }

    public Double getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(Double floatRate) {
        this.floatRate = floatRate;
    }
}
