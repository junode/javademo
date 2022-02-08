package com.junode.design_pattern_test.domain;

import com.junode.design_pattern_test.domain.api.Lucky;
import com.junode.design_pattern_test.domain.api.User;
import com.junode.design_pattern_test.entity.LuckyDraw;
import com.junode.design_pattern_test.entity.LuckyUser;
import org.springframework.beans.BeanUtils;

/**
 * 采用工厂模式，将内层的if...else...包装起来
 */
public class Factory {
    public static User getUserInstance(LuckyUser user) {
        if (user.getVipLevel().equals(0)) {
            final CommonVip commonVip = new CommonVip();
            BeanUtils.copyProperties(user, commonVip);
            return commonVip;
        } else if (user.getVipLevel().equals(1)) {
            final GrodVip gropVip = new GrodVip();
            BeanUtils.copyProperties(user, gropVip);
            return gropVip;
        } else if (user.getVipLevel().equals(2)) {
            final DiamondVip diamondVip = new DiamondVip();
            BeanUtils.copyProperties(user, diamondVip);
            return diamondVip;
        }
        return null;
    }

    public static Lucky getLuckyInstance(LuckyDraw luckDraw) {
        if (luckDraw.getLuckType().equals(0)) { // 订单抽奖
            final OrderLucky orderLucky = new OrderLucky();
            BeanUtils.copyProperties(luckDraw,orderLucky);
            return orderLucky;
        } else if (luckDraw.getLuckType().equals(1)) { // 积分抽奖
            final IntegerLucky integerLucky = new IntegerLucky();
            BeanUtils.copyProperties(luckDraw,integerLucky);
            return integerLucky;
        } else if(luckDraw.getLuckType().equals(2)){ // 免费抽奖
            final FreeLucky freeLucky = new FreeLucky();
            BeanUtils.copyProperties(luckDraw,freeLucky);
            return freeLucky;
        }
        return null;
    }

}
