package com.junode.design.pattern.domain;

import com.junode.design.pattern.domain.anno.MyAnnotation;
import com.junode.design.pattern.domain.api.Lucky;
import com.junode.design.pattern.entity.LuckyDraw;

/**
 * 订单抽奖
 */
@MyAnnotation("0")
public class OrderLucky  extends LuckyDraw implements Lucky {
    /*public static Integer getType() {
        return 0;
    }*/
}
