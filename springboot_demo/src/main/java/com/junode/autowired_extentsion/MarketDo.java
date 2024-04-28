package com.junode.autowired_extentsion;

/**
 * @author junode
 * @version 1.0.0
 * @Description 持仓对象
 * @createTime 2024年04月11日 23:44:00
 */
public class MarketDo {
    private String positionDate;
    private String securityId;
    private String securityMarketNo;
    private Double floatRate;

    public String getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(String positionDate) {
        this.positionDate = positionDate;
    }

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

    public Double getFloatRate() {
        return floatRate;
    }

    public void setFloatRate(Double floatRate) {
        this.floatRate = floatRate;
    }
}
