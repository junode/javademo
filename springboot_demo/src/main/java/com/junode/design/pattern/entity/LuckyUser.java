package com.junode.design.pattern.entity;

/**
 * 幸运用户实体类
 */
public class LuckyUser {

    // 客户id
    private Integer id;

    // 客户名称
    private String name;

    // 手机号码
    private String phone;

    // vip等级：0普通会员；1：黄金会员；2：钻石会员
    private Integer vipLevel;

    // 最近一次下单的钱
    private Integer orderMoney;

    // 当前积分
    private Integer integral;

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Integer orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }
}
