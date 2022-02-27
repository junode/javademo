package com.junode.design.pattern.entity;

/**
 * 幸运类型
 */
public class LuckyDraw {
    // 幸运类型：0-订单抽奖；1-积分抽奖；2-免费抽奖
    private Integer luckType;

    public Integer getLuckType() {
        return luckType;
    }

    public void setLuckType(Integer luckType) {
        this.luckType = luckType;
    }
}
